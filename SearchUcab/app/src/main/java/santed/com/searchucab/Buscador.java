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

import java.util.ArrayList;

public class Buscador extends Fragment {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lugar);
    }*/

    private ArrayList<Area> data;
    private RecyclerView rvBuscador;
    private Adaptador_buscador adaptador;

    public Buscador()
    {

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
        data = new ArrayList<>();
        data.add(new Area("feria", "lugar para comer"));
        data.add(new Area("laboratorio", "Hogar de las ingenierias"));
        data.add(new Area("biblioteca", "Espacio de estudio"));
        data.add(new Area("aulas", "Hogar de las demas carreras"));
        data.add(new Area("cincuentenario", "Edificio de clases reciente"));
        data.add(new Area("aula magna", "Auditorio principal"));
    }

    public void CargarAdaptador()
    {

        adaptador = new Adaptador_buscador(getActivity(), data);
        rvBuscador.setAdapter(adaptador);
    }
}
