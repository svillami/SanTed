package santed.com.searchucab;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Teddy J Sears on 01/01/2017.
 * Clase que se encarga de la interaccion con el webserver
 * El primer parametro del AsyncTask indicara de que tipo son los parametros que se envian
 * El segundo paramaetro indica el tipo de las unidades de progreso (no se usara)
 * El tercer parametro indica cual sera el tipo de resultado que nos dara el el trabajo que
 * se hizo en el background
 * @// TODO: 05/01/2017 Falta en el onpostexcecute completar el switch para llenar la data de acuerdo a las clases
 * @version 2.1.0
 */
public class CargarDatosAsincrono extends AsyncTask<String, Integer, String>
{

    //Atributos de la clase
    private int nivel;
    private ArrayList data;
    private Context contexto;
    private ProgressDialog pdLoading;
    private int profundidad;

    /**
     * Constructor de la clase para obtener el nivel
     * @param nivel El nivel que indicara que vamos a buscar y por defecto la altura sera de 1
     */
    public CargarDatosAsincrono(int nivel, Context contexto)
    {
        this.nivel = nivel;
        this.contexto = contexto;
        this.pdLoading = new ProgressDialog(contexto);
        this.profundidad = 1;
    }

     /**
     * Getter para obtener el valor del nivel
     * @return El nivel al que estamos buscando
     */
    public int getNivel()
    {
        return nivel;
    }

    /**
     * Setter para asignar el valor nuevo del nivel
     * @param nivel El nivel al que buscaremos ahora
     */
    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    /**
     * Getter para obtener la data
     * @return La data que se ha traido de la base de datos
     */
    public ArrayList getData() {
        return data;
    }

    /**
     * Setter para establecer la data
     * @param data La data nueva que se habra consultado
     */
    public void setData(ArrayList data) {
        this.data = data;
    }

    @Override
    protected void onPreExecute()
    {

        //Instanciamos el tipo de data a almacenar dependiendo de los niveles
        switch (nivel)
        {
            case 0:
                data = new ArrayList<Salud>();
                break;

            case 1:
                data = new ArrayList<Local>();
                break;

            case 2:
                data = new ArrayList<Deporte>();
                break;

            case 3:
                data = new ArrayList<Banco>();
                break;

            case 4:
                data = new ArrayList<Dependencia>();
                break;

            case 5:
                data = new ArrayList<Dependencia>();
                break;

            case 6:
                data = new ArrayList<Laboratorio>();
                break;

            case 7:
                data = new ArrayList<Facultad>();
                break;

            case 8:
                data = new ArrayList<Escuela>();
                break;

            case 9:

                switch (profundidad)
                {
                    case 1:
                        data = new ArrayList<Area>();
                        break;

                    /*Todos lo que tendra esa area seran entidades se
                     debio haber hecho esta instanciacion de una vez en vez de todos los cases (
                     usar entidad en vez del switch*/
                    case 2:

                        data = new ArrayList<Entidad>();
                        break;
                }

                break;

            case 10:
                data = new ArrayList<DataBuscador>();
                break;
        }


        //this method will be running on UI thread
        pdLoading.setMessage("\tBuscando...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }


    /**
     * Getter para el atributo profundidad
     * @return El nivel de profundidad al que estaremos buscando
     */
    public int getProfundidad() {
        return this.profundidad;
    }

    /**
     * Setter para establecer el nivel de profundiad actual
     * @param profundidad La profundidad a la que nos referiremos
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * Metodo para realizar en el background (fuera del thread UI) las operaciones de consulta ytodo lo que no necesite interfaz en general.
     * @param params los parametros que se enviaran para que sean utilizados en el background
     *               (puede ser URL, String, lo que sea, se hizo String para manejar las excepciones
     *               de URL)
     * @return string que indica si fallo o no la transaccion
     */
    @Override
    protected String doInBackground(String... params)
    {
        //Informacion que me traera la consulta al webservice
        StringBuilder resultadoConsulta = new StringBuilder();

        //Indicara un numero del tipo de respuesta que se recibio
        int respuestaConsulta;

        //El abrir una coneccion puede fallar asi que lanzamos una excepcion de tipo IO
        try {

            //Obtenemos la URL a la cual voy a ir
            URL url = new URL(params [0]);

            //Abrimos coneccion
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();

            /* De aqui en adelante el codigo para el metodo post*/
            /////////////////////////////////////////////////////////////
            //Creamos un objeto JSON que sera el que se va a enviar
            JSONObject dataEnviar = new JSONObject();

            /*Si el nivel es 9 significa que estamos desglozando desde areas
            10 significa que estamos usando el buscador escrito */
            if (this.nivel == 10 || (this.nivel == 9 && this.profundidad > 1))
            {
                //Le agregamos los datos
                dataEnviar.put("searchQuery", params[1]);
            }

            //Setteamos la conecion para que sea de tipo post
            conection.setRequestMethod(Utility.METODO_POST);
            conection.setConnectTimeout(Utility.CONEXION_TIMEOUT);
            conection.setReadTimeout(Utility.LECTURA_TIMEOUT);
            conection.setDoInput(true);
            conection.setDoOutput(true);

            Log.d("MAGALLANES","golazooooooooooooooooooooooooooooooo");

            //Obtenemos la salida de la coneccion
            OutputStream StreamSalida = conection.getOutputStream();

            //Creamos un buffer escritor
            BufferedWriter escritor = new BufferedWriter(
                    new OutputStreamWriter(StreamSalida, "UTF-8"));

            //Escribimos los datos del JSON convirtiendoso en string
            escritor.write(obtenerStringJSONEnviar(dataEnviar));

            escritor.flush();
            escritor.close();
            StreamSalida.close();
            ////////////////////////////////////////////////////////////

            //Se guarda el numero del tipo de respuesta que se recibio
            respuestaConsulta = conection.getResponseCode();

            //Si hubo respuesta
            if(respuestaConsulta == HttpURLConnection.HTTP_OK)
            {
                //Obtenemos los datos
                InputStream recibiendoRespuesta = new BufferedInputStream(conection.getInputStream());

                //lo insertamos en un bufferReader
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(recibiendoRespuesta));

                //Mientras haya lineas de respuesta es decir varios JSON hasta que no se acabe
                String linea;
                while((linea = bfReader.readLine()) != null)
                {

                    resultadoConsulta.append(linea);
                }

                //Cerramos el lector
                bfReader.close();
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }

        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return resultadoConsulta.toString();
    }

    /**
     * Metodo que se encarga de convertir en string el JSON a enviar
     * @param parametros el JSON con los parametros a enviar
     * @return El JSON convertido en string
     */
    public String obtenerStringJSONEnviar(JSONObject parametros) throws UnsupportedEncodingException, JSONException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = parametros.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = parametros.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    /**
     * Metodo para regresar al UI Thread cuando ya se acabo la transaccion, es decir, realizar
     * operaciones que si necesiten de la interfaz grafica
     * @param resultado toda la informacion que se trajo de la consulta
     */
    @Override
    protected void onPostExecute(String resultado)
    {
        try
        {
            JSONArray arregloJSON = new JSONArray(resultado);

            //Si la consulta arrojo datos
            if (arregloJSON.length() > 0)
            {
                DataBuscador nuevoBuscador;

                //Obtenemos cada uno de los datos arrojados por el JSON
                for (int aux = 0; aux < arregloJSON.length(); aux ++)
                {
                    //Sacamos el objeto y cargamos el adaptador
                    JSONObject objetoJSON = arregloJSON.getJSONObject(aux);

                    /*Dependiendo del nivel que nos encontremos instanciaremos las clases
                    correspondientes y lo añadimos a la lista*/
                    switch (nivel)
                    {
                        //Servicios de salud
                        case 0:

                            //Creamos el area con sus datos basicos
                           /* nuevaArea = new Area(objetoJSON.getString("nombre"),
                                    objetoJSON.getString("descripcion"));

                            //Creamos el piso con su numero y salones
                            nuevoPiso = new Piso(objetoJSON.getInt("nombre"));
                            nuevoPiso.AgregarSalon(objetoJSON.getString("salon"));

                            //Agregamos el piso al Area
                            nuevaArea.AgregarPiso(nuevoPiso);*/

                            Salud nuevaSalud = new Salud(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            data.add(nuevaSalud);
                            break;

                        //Servicios de comida
                        case 1:

                           Local nuevoLocal = new Local(objetoJSON.getString("nombre")
                                   ,objetoJSON.getString("especialidad"));

                            data.add(nuevoLocal);
                            break;

                        //Servicio de deporte
                        case 2:

                            Deporte nuevoDeporte = new Deporte(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            data.add(nuevoDeporte);
                            break;

                        //Servicios Bancarios
                        case 3:

                            Banco nuevoBanco = new Banco(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            nuevoBanco.setAltitud(Float.parseFloat(objetoJSON.getString("longitud")));

                            nuevoBanco.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));

                            data.add(nuevoBanco);
                            break;

                        //Servicios administrativos
                        case 4:

                            Dependencia nuevaDependencia = new Dependencia(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"), true);

                            data.add(nuevaDependencia);
                            break;

                        //Servicios al cliente
                        case 5:

                            Dependencia nuevaDependenciaCliente = new Dependencia(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"), false);

                            data.add(nuevaDependenciaCliente);
                            break;

                        case 6:

                            Laboratorio nuevoLaboratorio = new Laboratorio(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            data.add(nuevoLaboratorio);
                            break;

                        //Facultades
                        case 7:

                            Facultad nuevaFacultad = new Facultad(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            data.add(nuevaFacultad);
                            break;

                        //Escuelas
                        case 8:

                            Escuela nuevaEscuela = new Escuela(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));

                            data.add(nuevaEscuela);
                            break;

                        //Areas y su desgloze
                        case 9:

                            //Profundidad a la que estaremos buscando
                            switch (profundidad)
                            {
                                //Areas en General
                                case 1:

                                    Area nuevaArea = new Area(objetoJSON.getString("nombre"),
                                            objetoJSON.getString("descripcion"));

                                    nuevaArea.setId(objetoJSON.getInt("identificacion"));

                                    data.add(nuevaArea);
                                    break;

                                //Todos los elementos de esa area
                                case 2:


                                    break;
                            }

                            break;

                        //Search escrito
                        case 10:

                            nuevoBuscador = new DataBuscador();
                            nuevoBuscador.codigo = objetoJSON.getInt("Lb_id");
                            nuevoBuscador.titulo = objetoJSON.getString("Lb_titulo");
                            nuevoBuscador.nombreautor = objetoJSON.getString("Lb_nombre_autor");
                            nuevoBuscador.editorial = objetoJSON.getString("Lb_editorial");
                            data.add(nuevoBuscador);
                            break;
                    }

                    /*
                    if(nivel == 1)
                    {

                        Area nuevaArea = new Area
                                (objetoJSON.getString("nombre")
                                        ,objetoJSON.getString("descripcion"));
                        data.add(nuevaArea);
                    }*/
                }
            }

            Buscador.EventListener eventListener= (Buscador.EventListener)contexto;

            //this method will be running on UI thread
            pdLoading.dismiss();

            eventListener.onNotifyDataSetChanged();
        }

        //Si ha ocurrido un error al crear o manipular el JSON
        catch (JSONException e)
        {
            e.printStackTrace();

        }
    }
}
