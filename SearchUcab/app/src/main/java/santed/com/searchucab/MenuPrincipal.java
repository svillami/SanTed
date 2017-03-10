package santed.com.searchucab;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.util.Random;

import samples.MainActivity;
import samples.SampleCamActivity;
import samples.SampleCamFragment;

/**
 * Clase que manipulara el activity principal de la Aplicacion
 */
public class MenuPrincipal extends AppCompatActivity implements Buscador.EventListener{

    //Atributos de la clase
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    public static final String OPCION = "OPCION";
    /**
     * Metodo sobre escrito que ejecuta todas las instrucciones pertinentes al momento de que se
     * cargue la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le setteamos el layout que tendra
        setContentView(R.layout.activity_menu_principal);

        //Titulo de la Actividad
        //this.setTitle("Prestamos");

        //Instanciamos el nuevo buscador
        //createDisplay(new Buscador());
        createDisplay(new Buscador(), "fragmentoBuscador");
      //  createDisplay(new Buscador_escrito(), "fragmentoAgregar");

        //Obtenemos la barra de acciones superior
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        //Le indicamos que tendra estas opciones personalizadas y activamos el menu
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*Instanciamos el layout de la barra lateral y el navigation view que tendra las opciones
        laterales */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Creamos un listener para cuando se seleccione alguna opcion lateral
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            /**
             * Metodo sobreescrito que ejecutara las acciones pertienentes al hacerle click a un
             * item del menu lateral
             * @param menuItem opcion particular del menu lateral
             * @return exito luego de ejecutar las instrucciones deseadas
             */
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Se pone obscuro el boton seleccionado
                menuItem.setChecked(true);

                //Cerramos (movemos a un lado) el drawer layout que es la barra lateral
                mDrawerLayout.closeDrawers();

                /*Obtenemos el ID de la opcion particular seleccionada para saber que opcion
                nos referimos */
                switch (menuItem.getItemId()) {

                    case R.id.buscador:

                        //Opcion para listar el buscador del lugar que se desea encontrar
                        Context context = getApplicationContext();
                        CharSequence text = "Opcion Buscador";
                        int duration = Toast.LENGTH_SHORT;
                        //Toast toast = Toast.makeText(context, text, duration);
                        //toast.show();
                        updateDisplay(new Buscador());
                    //    updateDisplay(new Buscador_escrito());
                        break;

                    case R.id.RA3D:

                        //Opcion para cargar la Realidad aumentada en 3D
                         context = getApplicationContext();
                         text = "RA3D";
                         duration = Toast.LENGTH_SHORT;
                        Toast  toast = Toast.makeText(context, text, duration);
                       /* updateDisplay(new Buscador_escrito());
                        toast.show();

                        updateDisplay(new Buscador_escrito());*/

                        Intent p = new Intent(MenuPrincipal.this, SampleCamActivity.class);
                        //startActivity(p);
                        p.putExtra(OPCION,text);
                        startActivity(p);

                        break;

                    case R.id.guia:

                        //Opcion que levanta el buscador y por ende la RA
                        updateDisplay(new guia_de_uso());
                        break;

                    case R.id.tips:

                        ////Opción que muestra la guia de uso
                        createSimpleDialog();

                        break;

                    case R.id.busquedanfc:

                        ////Opción que ejecuta la busqueda por NFC cargando una actividad nueva
                        Intent i = new Intent(MenuPrincipal.this, LecturaNfc.class);
                        startActivity(i);

                        break;

                    case R.id.salir:

                        ////Opción que cierra la aplicacion
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Metodo sobreescrito que se encarga de ejecutar alguna accion si se ha apretado el boton de
     * back (atras)
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Creamos un nuevo intent principal que no necesita recibir data
        Intent intent = new Intent(Intent.ACTION_MAIN);

        //Decimos que se desplegara la actividad principal que se usa cuando inicia el celular
        intent.addCategory(Intent.CATEGORY_HOME);

        /*Decimos que la actividad de tipo home (la inical al prender el celular) se coloque de primera
        y limpie de la cola todas las demas actividades que esten en frente */
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***

        //Comenzamos la actividad (Menu principal) y cerramos la aplicaicon
        startActivity(intent);
        finish();
        System.exit(0);
    }

    /**
     * Metodo que permite crear una instancia nueva de un fragmento para su posterior manipulacion
     * @param fragmentoAgregar El fragmento que se desea agregar
     */
    public void createDisplay (Fragment fragmentoAgregar, String fragmentoTag)
    //public void createDisplay (Fragment fragmentoAgregar)
    {
        //Obtenemos el manager de fragmentos
        FragmentManager managerFragmentos = getFragmentManager();

        //A partir de aqui se comienza a crear el fragmento
        FragmentTransaction transaccion = managerFragmentos.beginTransaction();

        /*Agregamos el fragmento nuevo a la transaccion con su tag pertinente en el contenedor
        frame_container del menu principal */
       // transaccion.add(R.id.frame_container, fragmentoAgregar, "fragmentoBuscador");
        transaccion.add(R.id.frame_container, fragmentoAgregar, fragmentoTag);

        //Ejecutamos la transaccion
        transaccion.commit();

    }

    /**
     * Metodo que se encarga de reemplazar el fragmento existente en la vista con un fragmento nuevo
     * @param fragment Fragmento nuevo con el que se desea reemplazar
     */
    private void updateDisplay(Fragment fragment) {

        //Llamamos a la clase que se encarga de administrar fragmentos
        FragmentManager fragmentManager = getFragmentManager();

        /*Iniciamos una transaccion, decimos reemplazar todos los fragmentos existentes
         en el contenedor indicado (el que existe en el activity principal) y se le agrega
         un tag para poder encontrarlo y hacerle posteriores cambios*/
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, "fragmentoBuscador").commit();

     //   fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    /**
     * Metodo sobreescrito para inflar el activity con el menu personalizado en el RES MENU
     * @param menu Menu con las opciones personalizadas
     * @return exito luego de que se infle el menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Metodo sobreescrito que se encarga de escuchar cualquier opcion seleccionada
     * en uno de los items del menu superior
     * @param item Opcion particular seleccionada del menu superior
     * @return Exito luego de realizar la operacion determinada de acuerdo al item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Obtenemos el ID del item para saber la posicion
        int id = item.getItemId();

        //De acuerdo la posicion significara una operacion personalizada
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:

                //Llamada para la información
                createSimpleDialogInfo();
                return true;
            case R.id.exit:

                //Llamada para salir de la app
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que creara un dialogo simple para los tips personalizados
     * @return El dialogo de tipo Alert con un tip random
     */
    public AlertDialog createSimpleDialog()
    {

        //Instanciamos el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Le agregamos el icono del lado superior izquierdo con un titulo
        builder.setIcon(R.drawable.question_mark)
                .setTitle("¿Sabías que?");

        //Elegimos un tip aleatorio en el rango de los que existen
        Random r = new Random();
        int tip = r.nextInt(31 - 1)+1;

        //De acuerdo al numero elegido se desplegara el alert con un mensaje el cual sera el tip
        switch (tip)
        {
            case 1:
                builder.setMessage(R.string.tip1);
                break;
            case 2:
                builder.setMessage(R.string.tip2);
                break;
            case 3:
                builder.setMessage(R.string.tip3);
                break;
            case 4:
                builder.setMessage(R.string.tip4);
                break;
            case 5:
                builder.setMessage(R.string.tip5);
                break;
            case 6:
                builder.setMessage(R.string.tip6);
                break;
            case 7:
                builder.setMessage(R.string.tip7);
                break;
            case 8:
                builder.setMessage(R.string.tip8);
                break;
            case 9:
                builder.setMessage(R.string.tip9);
                break;
            case 10:
                builder.setMessage(R.string.tip10);
                break;
            case 11:
                builder.setMessage(R.string.tip11);
                break;
            case 12:
                builder.setMessage(R.string.tip12);
                break;
            case 13:
                builder.setMessage(R.string.tip13);
                break;
            case 14:
                builder.setMessage(R.string.tip14);
                break;
            case 15:
                builder.setMessage(R.string.tip15);
                break;
            case 16:
                builder.setMessage(R.string.tip16);
                break;
            case 17:
                builder.setMessage(R.string.tip17);
                break;
            case 18:
                builder.setMessage(R.string.tip18);
                break;
            case 19:
                builder.setMessage(R.string.tip19);
                break;
            case 20:
                builder.setMessage(R.string.tip20);
                break;
            case 21:
                builder.setMessage(R.string.tip21);
                break;
            case 22:
                builder.setMessage(R.string.tip22);
                break;
            case 23:
                builder.setMessage(R.string.tip23);
                break;
            case 24:
                builder.setMessage(R.string.tip24);
                break;
            case 25:
                builder.setMessage(R.string.tip25);
                break;
            case 26:
                builder.setMessage(R.string.tip26);
                break;
            case 27:
                builder.setMessage(R.string.tip27);
                break;
            case 28:
                builder.setMessage(R.string.tip28);
                break;
            case 29:
                builder.setMessage(R.string.tip29);
                break;
            case 30:
                builder.setMessage(R.string.tip30);
                break;


        }

        //Alinea el texto
        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);

        //"Versión " + "1.0" + "\n" +

        //Se crea el dialogo despues de mostrado
        return builder.create();
    }

    /**
     * Metodo que creara un dialogo simple para mostrar una informacion resumida (about) de la
     * aplicacion
     * @return El dialogo de tipo Alert con la informacion resumida de la App
     */
    public AlertDialog createSimpleDialogInfo() {

        //Instanciamos el constructor de dialogos de alerta en este activity
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Agregamos el icono en la parte superior derecha, titulo central y la informaicon
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("Search UCAB")
                .setMessage(R.string.informacion);

        //Alinea el texto
        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);

        //El constructor crea el dialogo ya mostrado
        return builder.create();
    }

    /**
     * Metodo implementado de la interface EventListener que le indicara a la activity
     * que la lista (recyclerView) se ha alterado y por lo tanto debe actualizarse para
     * mostrar los datos nuevos
     */
    @Override
    public void onNotifyDataSetChanged() {

        //Buscamos el fragmento que aloja los datos
        Fragment fragment = getFragmentManager().findFragmentByTag("fragmentoBuscador");

        // you might need to change visibility of
        // `mWrappedAdapter` in the fragment that defines
        // it or create a getter for it so that you can access it here

        /*Casteamos el fragmento a tipo buscador y decimos a la activity que de este fragmento
        es donde hubieron datos cambiados*/
        ((Buscador) fragment).getAdaptador().notifyDataSetChanged();

    }
}
