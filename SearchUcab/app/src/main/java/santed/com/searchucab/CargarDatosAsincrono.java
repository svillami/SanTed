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
    private int tipoEntidad;
    private RecibirRespuesta recibirRespuesta;

    //Interfaz para recibir la respuesta para el nivel por areas
    public interface RecibirRespuesta
    {
        void RecibiendoRespuesta(ArrayList respuesta);
    }

    public RecibirRespuesta getRecibirRespuesta() {
        return recibirRespuesta;
    }

    public void setRecibirRespuesta(RecibirRespuesta recibirRespuesta) {
        this.recibirRespuesta = recibirRespuesta;
    }

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
        this.recibirRespuesta = null;
    }

    /**
     * Getter para obtener el tipo de Entidad
     * @return El tipo de Entidad los cuales estamos buscando
     */
    public int getTipoEntidad() {
        return tipoEntidad;
    }


    /**
     * Setter para indicar el Tipo de Entidad que estamos buscando
     * @param tipoEntidad El tipo de entidad con el que trabajaremos
     */
    public void setTipoEntidad(int tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
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

                    /*Todos lo que tendra esa areas seran entidades se
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

                //Nos ayudara a manipular las areas agregandoles piso o no de acuerdo a si el ID cambia
                int IDaux = -1;
                boolean PrimeraVez = true;

                Area nuevaArea = null;

                //Obtenemos cada uno de los datos arrojados por el JSON
                for (int aux = 0; aux < arregloJSON.length(); aux ++)
                {
                    //Sacamos el objeto y cargamos el adaptador
                    JSONObject objetoJSON = arregloJSON.getJSONObject(aux);

                    /*Dependiendo del nivel que nos encontremos instanciaremos las clases
                    correspondientes y lo añadimos a la lista*/
                    switch (nivel)
                    {
                        //Servicios de serviciosdesalud
                        case 0:

                            //Creamos el areas con sus datos basicos
                           /* nuevaArea = new Area(objetoJSON.getString("nombre"),
                                    objetoJSON.getString("descripcion"));

                            //Creamos el piso con su numero y salones
                            nuevoPiso = new Piso(objetoJSON.getInt("nombre"));
                            nuevoPiso.AgregarSalon(objetoJSON.getString("salon"));

                            //Agregamos el piso al Area
                            nuevaArea.AgregarPiso(nuevoPiso);*/

                            Salud nuevaSalud = new Salud(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevaSalud.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevaSalud.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevaSalud.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevaSalud.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevaSalud.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevaSalud);
                            break;

                        //Servicios de serviciosdecomida
                        case 1:

                           Local nuevoLocal = new Local(objetoJSON.getString("nombre")
                                   ,objetoJSON.getString("especialidad"));
                            nuevoLocal.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevoLocal.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevoLocal.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevoLocal.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevoLocal.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevoLocal);
                            break;

                        //Servicio de serviciosdedeporte
                        case 2:

                            Deporte nuevoDeporte = new Deporte(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevoDeporte.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevoDeporte.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevoDeporte.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevoDeporte.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevoDeporte.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevoDeporte);
                            break;

                        //Servicios Bancarios
                        case 3:

                            Banco nuevoBanco = new Banco(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevoBanco.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevoBanco.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevoBanco.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevoBanco.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevoBanco.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevoBanco);
                            break;

                        //Servicios administrativos
                        case 4:

                            Dependencia nuevaDependencia = new Dependencia(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"), true);
                            nuevaDependencia.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevaDependencia.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevaDependencia.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevaDependencia.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevaDependencia.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevaDependencia);
                            break;

                        //Servicios al serviciosalcliente
                        case 5:

                            Dependencia nuevaDependenciaCliente = new Dependencia(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"), false);
                            nuevaDependenciaCliente.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevaDependenciaCliente.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevaDependenciaCliente.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevaDependenciaCliente.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevaDependenciaCliente.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevaDependenciaCliente);
                            break;

                        case 6:

                            Laboratorio nuevoLaboratorio = new Laboratorio(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevoLaboratorio.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevoLaboratorio.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevoLaboratorio.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevoLaboratorio.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevoLaboratorio.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevoLaboratorio);
                            break;

                        //Facultades
                        case 7:

                            Facultad nuevaFacultad = new Facultad(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevaFacultad.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevaFacultad.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevaFacultad.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevaFacultad.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevaFacultad.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevaFacultad);
                            break;

                        //Escuelas
                        case 8:

                            Escuela nuevaEscuela = new Escuela(objetoJSON.getString("nombre")
                                    ,objetoJSON.getString("descripcion"));
                            nuevaEscuela.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                            nuevaEscuela.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                            nuevaEscuela.setTextosInformacion(objetoJSON.getString("nombreArea"));
                            nuevaEscuela.setTextosInformacion(objetoJSON.getString("piso"));
                            nuevaEscuela.setTextosInformacion(objetoJSON.getString("informacion"));

                            data.add(nuevaEscuela);
                            break;

                        //Areas y su desgloze
                        case 9:

                            //Profundidad a la que estaremos buscando
                            switch (profundidad)
                            {
                                //Areas en General
                                case 1:

                                    //Si son diferentes significa que ya no es la misma areas y no tenemos que agregarle mas pisos
                                    if (IDaux != objetoJSON.getInt("identificacion"))
                                    {
                                        //Evitar que se inserte vacio la primera vez
                                        if(!PrimeraVez)
                                        {
                                            data.add(nuevaArea);
                                        }
                                        else
                                        {
                                            PrimeraVez = false;
                                        }

                                        //Creo la nueva areas y setteo el ID
                                        nuevaArea = new Area(objetoJSON.getString("nombre"),
                                                objetoJSON.getString("descripcion"));
                                        nuevaArea.setId(objetoJSON.getInt("identificacion"));

                                        //Creamos un piso nuevo para esa areas y se la agregamos
                                        Piso nuevoPiso = new Piso(objetoJSON.getString("piso"));
                                        nuevoPiso.setId(objetoJSON.getInt("identificacionPiso"));

                                        //Le agregamos un nuevo salon si no viene nulo en el JSON
                                        if (objetoJSON.getString("salon") != null && !objetoJSON.getString("salon").equals("NULL"))
                                        {
                                            nuevoPiso.AgregarSalon(objetoJSON.getString("salon"));

                                            Log.d("PISO","AGREGO PISOS ARRIBA!!!!!");
                                        }

                                        //Agregamos el piso al areas
                                        nuevaArea.AgregarPiso(nuevoPiso);

                                        //Cambiamos el auxiliar
                                        IDaux = objetoJSON.getInt("identificacion");

                                        Log.d("AUXILIAR", String.valueOf(IDaux));
                                    }
                                    else
                                    {
                                        //Creamos un piso nuevo para esa areas y se la agregamos
                                        Piso nuevoPiso = new Piso(objetoJSON.getString("piso"));
                                        nuevoPiso.setId(objetoJSON.getInt("identificacionPiso"));

                                        //Le agregamos un nuevo salon si no viene nulo en el JSON
                                        if (objetoJSON.getString("salon") != null && !objetoJSON.getString("salon").equals("NULL"))
                                        {
                                            nuevoPiso.AgregarSalon(objetoJSON.getString("salon"));

                                            Log.d("PISO","AGREGO PISOS ABAJO!!!!!");

                                        }

                                        //Agregamos el piso al areas
                                        nuevaArea.AgregarPiso(nuevoPiso);

                                    }

                                    break;

                                //Todos los elementos de esa areas obtenemos sus datos de acuerdo al que sea
                                case 2:

                                    switch (this.tipoEntidad)
                                    {

                                        case 1:

                                            Auditorio nuevoAuditorio = new Auditorio(objetoJSON.getString("nombre"),
                                                objetoJSON.getString("descripcion"));
                                            nuevoAuditorio.setId(objetoJSON.getInt("identificacion"));
                                            nuevoAuditorio.setTextosInformacion("vacio");
                                            nuevoAuditorio.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoAuditorio.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoAuditorio.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoAuditorio.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoAuditorio);
                                        break;

                                        case 2:

                                            Banco nuevoBanco2 = new Banco(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevoBanco2.setId(objetoJSON.getInt("identificacion"));
                                            nuevoBanco2.setTextosInformacion("vacio");
                                            nuevoBanco2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoBanco2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoBanco2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoBanco2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoBanco2);
                                            break;

                                        case 3:

                                            Dependencia nuevaDependencia2 = new Dependencia(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"),
                                                    objetoJSON.getString("tipo").equals("admin"));
                                            nuevaDependencia2.setId(objetoJSON.getInt("identificacion"));
                                            nuevaDependencia2.setTextosInformacion("vacio");
                                            nuevaDependencia2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevaDependencia2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevaDependencia2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevaDependencia2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevaDependencia2);
                                            break;

                                        case 4:

                                            Deporte nuevoDeporte2 = new Deporte(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevoDeporte2.setId(objetoJSON.getInt("identificacion"));
                                            nuevoDeporte2.setTextosInformacion("vacio");
                                            nuevoDeporte2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoDeporte2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoDeporte2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoDeporte2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoDeporte2);
                                            break;

                                        case 5:

                                            Escuela nuevaEscuela2 = new Escuela(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevaEscuela2.setId(objetoJSON.getInt("identificacion"));
                                            nuevaEscuela2.setTextosInformacion("vacio");
                                            nuevaEscuela2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevaEscuela2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevaEscuela2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevaEscuela2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevaEscuela2);
                                            break;

                                        case 6:

                                            Facultad nuevaFacultad2 = new Facultad(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevaFacultad2.setId(objetoJSON.getInt("identificacion"));
                                            nuevaFacultad2.setTextosInformacion("vacio");
                                            nuevaFacultad2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevaFacultad2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevaFacultad2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevaFacultad2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevaFacultad2);
                                            break;

                                        case 7:

                                            Laboratorio nuevoLaboratorio2 = new Laboratorio(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevoLaboratorio2.setId(objetoJSON.getInt("identificacion"));
                                            nuevoLaboratorio2.setTextosInformacion("vacio");
                                            nuevoLaboratorio2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoLaboratorio2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoLaboratorio2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoLaboratorio2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoLaboratorio2);
                                            break;

                                        case 8:

                                            Local nuevoLocal2 = new Local(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("especialidad"));
                                            nuevoLocal2.setId(objetoJSON.getInt("identificacion"));
                                            nuevoLocal2.setTextosInformacion("vacio");
                                            nuevoLocal2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoLocal2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoLocal2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoLocal2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoLocal2);
                                            break;

                                        case 9:

                                            Monumento nuevoMonumento2 = new Monumento(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevoMonumento2.setId(objetoJSON.getInt("identificacion"));
                                            nuevoMonumento2.setTextosInformacion("vacio");
                                            nuevoMonumento2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevoMonumento2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevoMonumento2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevoMonumento2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevoMonumento2);
                                            break;

                                        case 10:

                                            Salud nuevaSalud2 = new Salud(objetoJSON.getString("nombre")
                                                    ,objetoJSON.getString("descripcion"));
                                            nuevaSalud2.setId(objetoJSON.getInt("identificacion"));
                                            nuevaSalud2.setTextosInformacion("vacio");
                                            nuevaSalud2.setTextosInformacion(objetoJSON.getString("piso"));
                                            nuevaSalud2.setLongitud(Float.parseFloat(objetoJSON.getString("longitud")));
                                            nuevaSalud2.setLatitud(Float.parseFloat(objetoJSON.getString("latitud")));
                                            nuevaSalud2.setTextosInformacion(objetoJSON.getString("Informacion"));

                                            data.add(nuevaSalud2);
                                            break;
                                    }

                                    //Le damos la respuesta
                                    this.recibirRespuesta.RecibiendoRespuesta(data);

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
