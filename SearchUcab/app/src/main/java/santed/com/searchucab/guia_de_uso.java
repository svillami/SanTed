package santed.com.searchucab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

public class guia_de_uso extends Fragment {

    private ArrayList opcion_lista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guia_de_uso, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }


    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        
        opcion_lista = new ArrayList<>();
        opcion_lista.add("¿Cómo usar el Buscador Lista?");
        opcion_lista.add("¿Cómo usar el Buscador Escrito?");
        opcion_lista.add("Usos de la Realidad Aumentada 3D");
        opcion_lista.add("¿Cómo usar la búsqueda NFC?");
        opcion_lista.add("¿Qué son los Tips?");
        opcion_lista.add("Tipos de Etiquetas para lectura");
        opcion_lista.add("¿Por qué aparecen estos mensajes?");

        RecyclerView.Adapter adapter = new DataAdapter(opcion_lista);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    //Toast.makeText(getActivity(), opcion_lista.get(position).toString(), Toast.LENGTH_SHORT).show();

                    if (opcion_lista.get(position).toString() == "Usos de la Realidad Aumentada 3D") {
                        createSimpleDialog("Realidad Aumentada 3D");
                    } else if (opcion_lista.get(position).toString() == "¿Cómo usar la busqueda NFC?"){
                        createSimpleDialog("¿Cómo usar la búsqueda NFC?");
                            } else if (opcion_lista.get(position).toString() == "¿Qué son los Tips?"){
                        createSimpleDialog("¿Qué son los Tips?");
                                    } else if (opcion_lista.get(position).toString() == "¿Cómo usar el Buscador Lista?"){
                        createSimpleDialog("¿Cómo usar el Buscador Lista?");
                                            } else if (opcion_lista.get(position).toString() == "¿Cómo usar el Buscador Escrito?"){
                        createSimpleDialog("¿Cómo usar el Buscador Escrito?");
                    } else if (opcion_lista.get(position).toString() == "Tipos de Etiquetas para lectura"){
                        createSimpleDialog("Tipos de Etiquetas para lectura");
                    } else if (opcion_lista.get(position).toString() == "¿Por qué aparecen estos mensajes?"){
                        createSimpleDialog("¿Por qué aparecen estos mensajes?");
                    }
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    // Dialogos por cada opción
    public AlertDialog createSimpleDialog(String result) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dark_Dialog);

        builder.setTitle(result);


        // Se implememta la opcion de los tips
        if (result.equals("¿Qué son los Tips?")) {

            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_tips, null);

            builder.setView(v);

            builder.show();
            //return builder.create();

            // Se implememta la opcion de como usar el buscado lista
        } else if (result.equals("¿Cómo usar el Buscador Lista?")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_buscador_lista, null);

            builder.setView(v);

            builder.show();
            //return builder.create();

            // Se implememta la opcion de como usar el buscado escrito
        } else if (result.equals("¿Cómo usar el Buscador Escrito?")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_buscador_escrito, null);

            builder.setView(v);

            builder.show();
            //return builder.create();

            // Se implememta la opcion de como usar la busqueda NFC
        } else if (result.equals("¿Cómo usar la búsqueda NFC?")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_busqueda_nfc, null);

            builder.setView(v);

            builder.show();
            //return builder.create();

            // Se implememta la opcion de la Realidad Aumentada 3D
        } else if (result.equals("Realidad Aumentada 3D")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_ar_3d, null);

            builder.setView(v);

            builder.show();
            //return builder.create();
        } else if (result.equals("Tipos de Etiquetas para lectura")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_etiquetas, null);

            builder.setView(v);

            builder.show();
            //return builder.create();
        } else if (result.equals("¿Por qué aparecen estos mensajes?")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_avisos, null);

            builder.setView(v);

            builder.show();
            //return builder.create();
        }


        return builder.create();

    }

}
