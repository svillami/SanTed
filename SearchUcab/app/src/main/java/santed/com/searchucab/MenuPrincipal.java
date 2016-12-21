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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.util.Random;


public class MenuPrincipal extends AppCompatActivity {

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
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog);

        builder.setIcon(R.drawable.foto_principal)
                .setTitle("Tip Rápido");

        Random r = new Random();
        int tip = r.nextInt(4 - 1)+1;

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
        }

        builder.show();

        //"Versión " + "1.0" + "\n" +
        return builder.create();
    }

    public AlertDialog createSimpleDialogInfo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog);

        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("Search UCAB")
                .setMessage(R.string.informacion);
        builder.show();
        return builder.create();
    }

}
