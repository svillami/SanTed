package santed.com.searchucab;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @// FIXME: 28/12/2016 ELIMINAR EL CONSTRUCTOR VIEJO DE LA CLASE AREA Y BANCO
 */
public class Buscador extends Fragment {

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

    /**
     * Constructor que inicializa con el nivel de areas mas genericas que se buscan
     */
    public Buscador()
    {
        this.nivel = 0;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View vista;
        vista = inflater.inflate(R.layout.fragment_lugar, container,false);

        rvBuscador = (RecyclerView) vista.findViewById(R.id.lista_lugar);

        LinearLayoutManager llManager = new LinearLayoutManager(getActivity());

        rvBuscador.setLayoutManager(llManager);
        rvBuscador.setHasFixedSize(true);

       // CargarDatos();

        //Ejecutamos el cargador Asincrono
        cargarDatos = new CargarDatosAsincrono(this.nivel);

        try {
            URL url = new URL("http://192.168.0.103/serverucabdroid/index.php");

            cargarDatos.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



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


    public void CargarAdaptador()
    {

        //Linea nueva obtenemos la data
        data = this.cargarDatos.getData();

        adaptador = new Adaptador_buscador(getActivity(), data, nivel);

        //Setteamos el listener
        adaptador.setOnclickListener(new View.OnClickListener()
        {

            //Obtenemos el elemento seleccionado
            @Override
            public void onClick(View v)
            {
                //Lo del string y el par de lineas de casteo abajo se eliminaran junto con el toast ya que son pruebas
                String nombre = null;
               if (nivel == 0)
               {
                   Area areaElegida = (Area) data.get(rvBuscador.getChildAdapterPosition(v));
                   nombre = areaElegida.getNombre();

                   /*Cambiamos el nivel para indicar que la lista debe llenarse con los siguientes
                    subelementos del area seleccionada*/
                   nivel = 1;
                   cargarDatos.setNivel(nivel);
               }
               // String nombre = data.get(rvBuscador.getChildAdapterPosition(v)).getNombre();

                Toast toast = Toast.makeText(getActivity(), nombre , Toast.LENGTH_SHORT);
                toast.show();

                //Limpiamos la lista
                adaptador.LimpiarData();

                //Realizamos la consulta de nuevo
                cargarDatos.execute();

               // CargarDatos();
                CargarAdaptador();

            }
        });

        //Setteamos el Adaptador
        rvBuscador.setAdapter(adaptador);
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

