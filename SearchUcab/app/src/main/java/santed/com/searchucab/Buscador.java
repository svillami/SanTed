package santed.com.searchucab;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        CargarDatos();
        CargarAdaptador();



        return vista;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

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


    }

    public void CargarAdaptador()
    {

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
               }
               // String nombre = data.get(rvBuscador.getChildAdapterPosition(v)).getNombre();

                Toast toast = Toast.makeText(getActivity(), nombre , Toast.LENGTH_SHORT);
                toast.show();

                //Limpiamos la lista
                adaptador.LimpiarData();

                CargarDatos();
                CargarAdaptador();

            }
        });

        rvBuscador.setAdapter(adaptador);
    }
}
