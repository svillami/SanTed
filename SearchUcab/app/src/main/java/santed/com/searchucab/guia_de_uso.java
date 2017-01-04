package santed.com.searchucab;

import android.content.Intent;
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
import android.widget.Toast;
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
        opcion_lista.add("¿COMO USAR EL BUSCADOR?");
        opcion_lista.add("Usos de la Realidad Aumentada 3D");
        opcion_lista.add("¿Cómo usar la busqueda NFC?");
        opcion_lista.add("¿Qué son los Tips?");

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
                        createSimpleDialog("¿Cómo usar la busqueda NFC?");
                            } else if (opcion_lista.get(position).toString() == "¿Qué son los Tips?"){
                        createSimpleDialog("¿Qué son los Tips?");
                                    } else if (opcion_lista.get(position).toString() == "¿Cómo usar el Buscador?"){
                        createSimpleDialog("¿Cómo usar el Buscador?");
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

        } else if (result.equals("¿Cómo usar el Buscador?")) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v = inflater.inflate(R.layout.dialog_signin, null);

            builder.setView(v);

            builder.show();
            //return builder.create();
        }
        return builder.create();

    }

}
