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


public class MenuPrincipal extends AppCompatActivity implements Buscador.EventListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //Titulo de la Actividad
        //this.setTitle("Prestamos");

        //Instanciamos el nuevo buscador
        createDisplay(new Buscador());

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.buscador:
                        //Opcion para listar los prestamos y mostrar información

                        Context context = getApplicationContext();
                        CharSequence text = "Opcion Buscador";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        updateDisplay(new Buscador());
                        break;

                    case R.id.RA3D:
                        //Opcion para listar los prestamos y poder ejecuat la devolucion
                         context = getApplicationContext();
                         text = "RA3D";
                         duration = Toast.LENGTH_SHORT;
                         toast = Toast.makeText(context, text, duration);
                        toast.show();

                        updateDisplay(new Buscador_escrito());

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
                        ////Opción que muestra la guia de uso
                        Intent i = new Intent(MenuPrincipal.this, LecturaNfc.class);
                        startActivity(i);

                        break;

                    case R.id.salir:
                        ////Opción que muestra la guia de uso
                        finish();
                        break;


                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    /**
     * Metodo que permite crear una instancia nueva de un fragmento para su posterior manipulacion
     * @param fragmentoAgregar El fragmento que se desea agregar
     */
    public void createDisplay (Fragment fragmentoAgregar)
    {
        //Obtenemos el manager de fragmentos
        FragmentManager managerFragmentos = getFragmentManager();

        //A partir de aqui se comienza a crear el fragmento
        FragmentTransaction transaccion = managerFragmentos.beginTransaction();

        //Agregamos el fragmento nuevo a la transaccion con su tag pertinente
        transaccion.add(R.id.frame_container, fragmentoAgregar, "fragmentoBuscador");

        //Ejecutamos la transaccion
        transaccion.commit();

    }

    private void updateDisplay(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.question_mark)
                .setTitle("¿Sabías que?");

        Random r = new Random();
        int tip = r.nextInt(31 - 1)+1;
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
        return builder.create();
    }

    public AlertDialog createSimpleDialogInfo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("Search UCAB")
                .setMessage(R.string.informacion);
        //Alinea el texto
        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);

        return builder.create();
    }

    @Override
    public void onNotifyDataSetChanged() {
        Fragment fragment = getFragmentManager().findFragmentByTag("fragmentoBuscador");

        // you might need to change visibility of `mWrappedAdapter` in the fragment that defines it or create a getter for it so that you can access it here
        ((Buscador) fragment).getAdaptador().notifyDataSetChanged();

    }
}
