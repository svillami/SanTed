package santed.com.searchucab;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import samples.MainActivity;
import samples.SampleCamActivity;

/**
 * @// FIXME: 28/12/2016 ELIMINAR EL CONSTRUCTOR VIEJO DE LA CLASE AREA Y BANCO
 * @// TODO: 09/01/2017 Programar el onbackpressed para regresar a la lista principal 
 */
public class Buscador extends Fragment implements SearchView.OnQueryTextListener
{

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lugar);
    }*/

    //private ArrayList<Area>;
    private ArrayList data;
    private RecyclerView rvBuscador;
    private Adaptador_buscador adaptador;
    private int nivel;
    private CargarDatosAsincrono cargarDatos;
    private int profundidad;
    public static final String OPCION2 = "OPCION2";

    public Adaptador_buscador getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(Adaptador_buscador adaptador) {
        this.adaptador = adaptador;
    }

    /**
     * Constructor que inicializa con el nivel de areas mas genericas que se buscan
     */
    public Buscador()
    {
        this.nivel = -1;
    }

    /**
     * Metodo que se ejecuta cuando la actividad se crea
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // What i have added is this
        setHasOptionsMenu(true);
    }

    /**
     * SMetodo que se encargara de incorporar la logica del boton superior y el searchbar para
     * buscar por escrito
     * @param menu El menu superior que se va a incorporar
     * @param inflater El inflater que llenara el menu superior
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_main, menu); // No la quita ya que quiero aderir el buscador al actionbar
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MenuPrincipal) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);
        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Utils.LogDebug("Clicked: ");
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                //Utils.LogDebug("Closed: ");
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                //Utils.LogDebug("Openeed: ");
                return true;  // Return true to expand action view
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }



    /**
     * Metodo que se ejecutara cuando el usuario haga click en el buscador escrito para buscar
     * (Search)
     * @param query Palabra o frase que se quiere buscar en la base de datos (string a enviar)
     * @return Exito si no hubo ningun error
     */
    @Override
    public boolean onQueryTextSubmit(String query)
    {
        //Instanciamos un AsyncTask para la consulta escrita
        this.cargarDatos = new CargarDatosAsincrono(10, getActivity());
        this.nivel = 10;

        //Limpiamos la lista que tiene la informacion vieja
        adaptador.LimpiarData();

        //Ejecutamos la consulta
        this.cargarDatos.execute(Utility.BUSCADOR_ESCRITO, query);

        //Linea nueva obtenemos la data
        data = this.cargarDatos.getData();

        //Instanciamos el adaptador
        adaptador = new Adaptador_buscador(getActivity(), data, nivel);

        //Setteamos el Adaptador
        rvBuscador.setAdapter(adaptador);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        return false;
    }


    //Define an interface that will notify your activity of data set change
    public interface EventListener {
        void onNotifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Creamos una vista que tendra el fragmento lugar inflada con el contenedor
        View vista;
        vista = inflater.inflate(R.layout.fragment_lugar, container, false);

        //Buscamos el RecyclerView que contendra la lista
        rvBuscador = (RecyclerView) vista.findViewById(R.id.lista_lugar);

        //Seteamos el manager que administrara el buscador en esa vista
        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());

        rvBuscador.setLayoutManager(llManager);
        rvBuscador.setHasFixedSize(true);

       // CargarDatos();

        //Instanciamos el cargador asincrono
        cargarDatos = new CargarDatosAsincrono(this.nivel, getActivity());

        /*por defecto la profundidad siempre comenzara en uno, esto servira para ir buscando
        poco a poco en la ultima opcion por areas*/
        this.profundidad = 1;

        /*
        //Sinos encontramos en el primer nivel cargaremos la pagina en esta clase
        if (this.nivel != -1)
        {
            //Le suministramos la URL del webservice y ejecutamos el hilo
            String url = "https://santedsearch.000webhostapp.com/pruebaphp.php";
            cargarDatos.execute(url);
        }*/

        //Cargamos el adaptador y los datos en la vista
        CargarAdaptador();

        return vista;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*
    public void CargarDatos()
    {
        //Esto tambien se eliminara porque se traera de la bd, es solo para prueba
        if (nivel == 0)
        {
            data = new ArrayList<Area>();
            data.add(new Area("feria", "lugar para comer"));
            data.add(new Area("laboratorio", "Hogar de las ingenierias"));
            data.add(new Area("biblioteca", "Espacio de estudio"));
            data.add(new Area("aulas", "Hogar de las demas carreras"));
            data.add(new Area("cincuentenario", "Edificio de clases reciente"));
            data.add(new Area("aula magna", "Auditorio principal"));
        }
        else
        {
            data = new ArrayList<Banco>();
            data.add(new Banco("Mercantil", "Banco Azul"));
            data.add(new Banco("BOD", "Banco Verde"));
            data.add(new Banco("Banco del Tesoro", "Banco Rojo"));
            data.add(new Banco("Bancaribe", "Banco Pirate"));

        }


        Thread hiloDatos = new Thread()
        {
            @Override
            public void run() {

                final String resultado = "hola";


                runOnUiThread

            }

        }

    }*/

    /**
     * Metodo que se encarga de llenar el menu principal solamente cuando se inicializa por primera
     * vez el buscador
     */
    public void MenuPrincipal ()
    {
        //Creamos un Arreglo de String con las opciones del menu principal
        data = new ArrayList<String>();
        data.add("Servicios de Salud");
        data.add("Servicios de Comida");
        data.add("Servicios de Deporte");
        data.add("Servicios Bancarios");
        data.add("Servicios Administrativos");
        data.add("Servicios al Cliente");
        data.add("Laboratorios");
        data.add("Facultades");
        data.add("Escuelas");
        data.add("Areas");
    }

    public void CargarAdaptador()
    {

        /*Si estamos en le primer nivel de datos no necesitamos buscar nada en la BD, entonces
        llenaremos el menu principal directamente aqui */
        if(this.nivel == -1)
        {
            //Llenamos el primer nivel de datos
            MenuPrincipal();

        }
       else
        {
            //Obtenemos la data proveniente del Asynctask
            data = this.cargarDatos.getData();

            //Instanciamos de nuevo el AsyncTask para poder usarlo de nuevo
            this.cargarDatos = new CargarDatosAsincrono(this.nivel, getActivity());
        }

        //Instanciamos el adaptador
        adaptador = new Adaptador_buscador(getActivity(), data, nivel);

        //Setteamos el listener
        adaptador.setOnclickListener(new View.OnClickListener() {
            /*String que tendra el URL del webservice a consultar y auxiliar para obtener
             informacion de las areas */
            String url;

            //Obtenemos el elemento seleccionado
            @Override
            public void onClick(View v) {
                //Lo del string y el par de lineas de casteo abajo se eliminaran junto con el toast ya que son pruebas
                String nombre = null;

                /*Si es el primer nivel (-1) se trata del menu principal, sino significara
                que es una opcion del menu y cada opcion tiene subniveles a la misma altura (arbol)*/
                //En todos los niveles se activara la AR excepto en el -1 y en el 10
                switch (nivel) {
                    case -1:

                        nombre = (String) data.get(rvBuscador.getChildAdapterPosition(v));
                        Log.d("SUPERGOL", Integer.toString(rvBuscador.getChildAdapterPosition(v)));

                        /*Cambiamos el nivel para indicar que la lista debe llenarse
                        con los siguientes
                        subelementos del area seleccionada
                        y el tipo para saber cual opcion fue la seleccionada*/
                        nivel = rvBuscador.getChildAdapterPosition(v);
                        cargarDatos.setNivel(nivel);

                        /*Le suministramos la URL del webservice dependiendo de la opcion
                          y ejecutamos el hilo*/
                        switch (nivel) {
                            //Servicios de Salud
                            case 0:
                                url = Utility.WEBSERVICE_SALUD;
                                break;

                            //Servicios de Comida
                            case 1:
                                url = Utility.WEBSERVICE_LOCALES;
                                break;

                            //Servicios de deporte
                            case 2:
                                url = Utility.WEBSERVICE_DEPORTES;
                                break;

                            //Servicios bancarios
                            case 3:

                                url = Utility.WEBSERVICE_BANCO;
                                break;

                            //Servicios administrativos
                            case 4:

                                url = Utility.WEBSERVICE_ADMINISTRATIVOS;
                                break;

                            //Servicios administrativos
                            case 5:

                                url = Utility.WEBSERVICE_CLIENTES;
                                break;

                            //Laboratorios
                            case 6:
                                url = Utility.WEBSERVICE_LABORATORIOS;
                                break;

                            //Facultades
                            case 7:

                                url = Utility.WEBSERVICE_FACULTAD;
                                break;

                            //Escuelas
                            case 8:

                                url = Utility.WEBSERVICE_ESCUELAS;
                                break;

                            //Area primera
                            case 9:

                                url = Utility.WEBSERVICE_AREAS;
                                break;

                        }

                        Toast toast = Toast.makeText(getActivity(), nombre, Toast.LENGTH_SHORT);
                        toast.show();

                        //Limpiamos la lista
                        adaptador.LimpiarData();

                        //Realizamos la consulta de nuevo
                        cargarDatos.execute(url);

                        // CargarDatos();
                        CargarAdaptador();
                        break;

                    //Servicios de Salud
                    case 0:

                        //  areaElegida = (Area) data.get(rvBuscador.getChildAdapterPosition(v));
                        // nombre = areaElegida.getNombre();
                        Salud salud = (Salud) data.get(rvBuscador.getChildAdapterPosition(v));
                        /*Cambiamos el nivel para indicar que la lista debe llenarse
                        con los siguientes subelementos del area seleccionada*/

                        // cargarDatos.setNivel(nivel);
                        break;

                    //Servicios de Comida
                    case 1:

                        Local locales = (Local) data.get(rvBuscador.getChildAdapterPosition(v));
                        //nombre = areaElegida.getNombre();

                        /*Cambiamos el nivel para indicar que la lista debe llenarse
                        con los siguientes subelementos del area seleccionada*/
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Servicios de deporte
                    case 2:

                        Deporte deportes = (Deporte) data.get(rvBuscador.getChildAdapterPosition(v));

                        break;

                    //Servicios Bancarios
                    case 3:

                        Banco bancoElegido = (Banco) data.get(rvBuscador.getChildAdapterPosition(v));

                        verificarGPS(bancoElegido);

                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Servicios Administrativos
                    case 4:

                        Dependencia dependenciaElegida = (Dependencia)
                                data.get(rvBuscador.getChildAdapterPosition(v));
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Servicios al Cliente
                    case 5:

                        Dependencia dependenciaElegidaCliente = (Dependencia)
                                data.get(rvBuscador.getChildAdapterPosition(v));
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Laboratorios
                    case 6:

                        Laboratorio laboratorioElegido = (Laboratorio) data.get(rvBuscador.getChildAdapterPosition(v));
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Facultades
                    case 7:

                        Facultad facultad = (Facultad) data.get(rvBuscador.getChildAdapterPosition(v));
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Facultades
                    case 8:

                        Escuela escuela = (Escuela) data.get(rvBuscador.getChildAdapterPosition(v));
                        //nivel = 2;
                        //cargarDatos.setNivel(nivel);
                        break;

                    //Areas principales
                    case 9:

                        //Limpiamos la lista
                        adaptador.LimpiarData();

                        /*De acuerdo a la profundidad que vayamos haremos una consulta
                        diferente */
                        //Dependiendo de la profundiad castearemos objetos distintos
                        switch (profundidad) {
                            //Areas en general
                            case 1:

                                profundidad = 2;
                                adaptador.setProfundidad(1);

                                //Realizamos la consulta de nuevo
                                cargarDatos.execute(url);
                                break;

                            case 2:

                                //Obtenemos el area elegida, la url a consultar y aumentamos la profundidad
                                Area areaElegida = (Area) data.get(rvBuscador.getChildAdapterPosition(v));

                                //Iteraremos por cada piso para obtener lugares que contiene
                                for (int aux = 0; aux < areaElegida.getListaPiso().size(); aux++) {
                                    //Obtendremos cada lugar del piso en que nos enontramos
                                    for (int aux2 = 1; aux2 <= 10; aux2++) {
                                         /*Instanciamos de nuevo el AsyncTask
                                        para poder usarlo de nuevo en cada piso */
                                        cargarDatos = new CargarDatosAsincrono(nivel, getActivity());
                                        cargarDatos.setProfundidad(2);

                                        url = Utility.getWebservice(aux2);

                                        //Realizamos la consulta de nuevo
                                        cargarDatos.execute(url, Integer.toString(areaElegida.getId()),
                                                Integer.toString(areaElegida.getListaPiso().get(aux).getId()));


                                    }

                                }

                                //profundidad = 3;
                                // cargarDatos.setProfundidad(2);
                                break;
                        }

                        // CargarDatos();
                        CargarAdaptador();

                        break;

                    //Search escrito
                    case 10:

                        DataBuscador dataSearch = (DataBuscador) data.get(rvBuscador.getChildAdapterPosition(v));
                        break;
                }

                /*
                if (nivel == -1)
                {
                    nombre = (String) data.get(rvBuscador.getChildAdapterPosition(v));
                    Log.d("SUPERGOL",Integer.toString(rvBuscador.getChildAdapterPosition(v)));

                    /*Cambiamos el nivel para indicar que la lista debe llenarse
                    con los siguientes
                    subelementos del area seleccionada
                    y el tipo para saber cual opcion fue la seleccionada

                    nivel = rvBuscador.getChildAdapterPosition(v);
                    cargarDatos.setNivel(nivel);

                    //Le suministramos la URL del webservice y ejecutamos el hilo
                    url = "https://santedsearch.000webhostapp.com/pruebaphp.php";
                }
                else if (nivel == 1)
                    {
                        Area areaElegida = (Area) data.get(rvBuscador.getChildAdapterPosition(v));
                        nombre = areaElegida.getNombre();

                        /*Cambiamos el nivel para indicar que la lista debe llenarse
                        con los siguientes subelementos del area seleccionada
                        nivel = 2;
                        cargarDatos.setNivel(nivel);
                    }*/


                // String nombre = data.get(rvBuscador.getChildAdapterPosition(v)).getNombre();
            }
        });

        //Setteamos el Adaptador
        rvBuscador.setAdapter(adaptador);


    }

    // Verificando que el GPS este encendido
    public  void verificarGPS (Entidad entidad){

     final   LocationManager manager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(getActivity(), "Enciende el GPS en la configuración, para disfrutar de la AR", Toast.LENGTH_LONG).show();

        } else {
            Intent o = new Intent(Buscador.this.getActivity(), SampleCamActivity.class);
            o.putExtra("clase",entidad);
            o.putExtra(OPCION2, "Buscador");
            startActivity(o);
        }
    }


}








/*
package com.example.sandra.primerapruebara;
        import android.app.Fragment; 
        import android.support.annotation.Nullable; 
        import android.support.v4.view.MenuItemCompat;
         import android.os.Bundle;
         import android.app.ProgressDialog;
         import android.net.Uri;
         import android.os.AsyncTask; 
        import android.support.v7.widget.LinearLayoutManager;
         import android.support.v7.widget.RecyclerView;
         import android.support.v7.widget.SearchView;
         import android.view.LayoutInflater;
         import android.view.Menu;
         import android.view.MenuInflater; 
        import android.view.MenuItem;
         import android.view.View;
         import android.view.ViewGroup; 
        import android.widget.Toast; 
        import org.json.JSONArray; 
        import org.json.JSONException;
         import org.json.JSONObject; 
        import java.io.BufferedReader;
         import java.io.BufferedWriter;
         import java.io.IOException; 
        import java.io.InputStream;
         import java.io.InputStreamReader; 
        import java.io.OutputStream; 
        import java.io.OutputStreamWriter; 
        import java.net.HttpURLConnection; 
        import java.net.MalformedURLException;
         import java.net.URL;
         import java.util.ArrayList; 
        import java.util.List;

           public class Buscador extends Fragment implements SearchView.OnQueryTextListener
{  
// CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds 
public static final int CONNECTION_TIMEOUT = 10000; 
    public static final int READ_TIMEOUT = 15000; 
    private RecyclerView mRVFish; 
    private AdaptandoBuscador mAdapter;    
    public Buscador() { 
        // Required empty public constructor     }  
        @Override 
        public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState)
        { 
            // Inflate the layout for this fragment 
            return inflater.inflate(R.layout.activity_buscador, container, false); 
        }  

        @Override 
        public void onResume()
        { 
            super.onResume(); 
            getActivity().setTitle("Busqueda de Libros"); 
        }   

        @Override 
        public void onActivityCreated(@Nullable Bundle savedInstanceState)
        {         super.onActivityCreated(savedInstanceState); 
            // What i have added is this 
            setHasOptionsMenu(true);     }   
        @Override 
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
        {          inflater.inflate(R.menu.search_main, menu);
            // No la quita ya que quiero aderir el buscador al actionbar 
            MenuItem item = menu.findItem(R.id.action_search); 
            SearchView sv = new SearchView(((MenuPrincipal) getActivity()).getSupportActionBar().getThemedContext()); 
            MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM); 
            MenuItemCompat.setActionView(item, sv); 
            sv.setOnQueryTextListener(this); 
            sv.setIconifiedByDefault(false); 
            sv.setOnSearchClickListener(new View.OnClickListener() { 
                @Override             public void onClick(View view)
                {                 //Utils.LogDebug("Clicked: "); 
                       }         });  
            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() { 
                @Override 
                public boolean onMenuItemActionCollapse(MenuItem item)
                { 
                    // Do something when collapsed 
                    // Utils.LogDebug("Closed: "); 
                            return true;
                    // Return true to collapse action view 
                             }  
                @Override 
                public boolean onMenuItemActionExpand(MenuItem item)
                {                 // Do something when expanded 
                     Utils.LogDebug("Openeed: "); 
                    return true;  // Return true to expand action view 
                }         });  

            super.onCreateOptionsMenu(menu,inflater); 
        }      @Override 

        public boolean onQueryTextSubmit(String query)
        { 
            //Utils.LogDebug("Submitted: "+query); 
                new AsyncFetch(query).execute(); 
            return true; 
        }  

        @Override     public boolean onQueryTextChange(String newText)
        {         //Utils.LogDebug("Changed: "+newText);         return false; 
             }   
            // Creanto clase AsyncFetch 

         public class AsyncFetch extends AsyncTask<String, String, String>
         {          ProgressDialog pdLoading = new ProgressDialog(getActivity()); 
             HttpURLConnection conn; 
             URL url = null; 
             String searchQuery;  

             public AsyncFetch(String searchQuery){ 
                 this.searchQuery=searchQuery; 
             }
                       @Override 
             protected void onPreExecute() { 
                 super.onPreExecute();  
                 //this method will be running on UI thread 
                          pdLoading.setMessage("\tBuscando..."); 
                 pdLoading.setCancelable(false); 
                 pdLoading.show();          }  

             @Override 
             protected String doInBackground(String... params) { 
                 try {  
                     // Enter URL address where your php file resides 
                                 url = new URL("http://192.168.0.102/serverucabdroid/phpfinal/Buscadorprestamos.php");  
                 } catch (MalformedURLException e) { 
                     // TODO Auto-generated catch block 
                              e.printStackTrace(); 
                     return e.toString(); 
                 }             try {  
                    // Setup HttpURLConnection class to send and receive data from php and mysql 
                             conn = (HttpURLConnection) url.openConnection(); 
                     conn.setReadTimeout(READ_TIMEOUT); 
                     conn.setConnectTimeout(CONNECTION_TIMEOUT); 
                     conn.setRequestMethod("POST");  
                     // setDoInput and setDoOutput to true as we send and recieve data 
                                  conn.setDoInput(true); 
                     conn.setDoOutput(true);  
                     // add parameter to our above url 
                               Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery); 
                     String query = builder.build().getEncodedQuery();  
                     OutputStream os = conn.getOutputStream(); 
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); 
                     writer.write(query); 
                     writer.flush(); 
                     writer.close(); 
                     os.close(); 
                     conn.connect();  
                 } catch (IOException e1) { 
                     // TODO Auto-generated catch block 
                               e1.printStackTrace(); 
                     return e1.toString(); 
                 }              try {  
                     int response_code = conn.getResponseCode();  
                     // Check if successful connection made 
                             if (response_code == HttpURLConnection.HTTP_OK) {  
                                 // Read data sent from server 
                                               InputStream input = conn.getInputStream(); 
                                 BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
                                 StringBuilder result = new StringBuilder(); 
                                 String line;  
                                 while ((line = reader.readLine()) != null) { 
                                     result.append(line); 
                                 }  
                                 // Pass data to onPostExecute method 
                                             return (result.toString());  
                             } else { 
                                 return("Error en Conexión"); 
                             }  
                 } catch (IOException e) { 
                     e.printStackTrace(); 
                     return e.toString(); 
                 } finally { 
                     conn.disconnect(); 
                 }           }  
             @Override 
             protected void onPostExecute(String result) {  
                 //this method will be running on UI thread 
                       pdLoading.dismiss(); 
                 List<DataBuscador> data=new ArrayList<>();  
                 pdLoading.dismiss(); 
                 if(result.equals("no rows")) { 
                     Toast.makeText(getActivity(), "No hay resultados para la busqueda", Toast.LENGTH_LONG).show();
                 }else{  
                     try {  
                         JSONArray jArray = new JSONArray(result);  
                         // Extract data from json and store into ArrayList as class objects 
                                           for (int i = 0; i < jArray.length(); i++) { 
                                               JSONObject json_data = jArray.getJSONObject(i); 
                                               DataBuscador fishData = new DataBuscador(); 
                                               fishData.codigo = json_data.getInt("Lb_id"); 
                                               fishData.titulo = json_data.getString("Lb_titulo"); 
                                               fishData.nombreautor = json_data.getString("Lb_nombre_autor"); 
                                               fishData.editorial = json_data.getString("Lb_editorial");  
                                               data.add(fishData); 
                                           }  
                         // Setup and Handover data to recyclerview 
                                     mRVFish = (RecyclerView) getActivity().findViewById(R.id.listaBuscador); 
                         mAdapter = new AdaptandoBuscador(getActivity(), data); 
                         mRVFish.setAdapter(mAdapter); 
                         mRVFish.setLayoutManager(new LinearLayoutManager(getActivity()));  
                         // Incluyendo Separador entre los elementos de la lista 
                                        RecyclerView.ItemDecoration itemDecoration = new 
                         DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST); 
                         mRVFish.addItemDecoration(itemDecoration);  
                     } catch (JSONException e) { 
                         // You to understand what actually error is and handle it appropriately 
                         Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show(); 
                         Toast.makeText(getActivity(), result.toString(), Toast.LENGTH_LONG).show(); 
                     }  
                 }  
             }  
         } 
    } */

