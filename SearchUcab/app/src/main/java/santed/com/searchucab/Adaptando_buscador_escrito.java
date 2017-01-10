package santed.com.searchucab;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @deprecated Se utilizara el adaptador_buscador para incoporar los datos a la interfaz
 */
public class Adaptando_buscador_escrito extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    private LayoutInflater inflater;
    List<DataBuscador> data= Collections.emptyList();
    DataBuscador current;

    public static final String codidint = "codidint";

    // create constructor to initialize context and data sent from MainActivity
    public Adaptando_buscador_escrito(Context context, List<DataBuscador> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }


    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_adaptando_buscador_escrito, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataBuscador current=data.get(position);
        //myHolder.textPrice.setText(current.price);
        myHolder.textTitulo.setText(current.titulo);
        myHolder.textNombreautor.setText("Autor: " + current.nombreautor);
        myHolder.textEditorial.setText("Editorial: " + current.editorial);
        myHolder.textCodigo.setText("C" +current.codigo);
        myHolder.textCodigo.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textTitulo;
        TextView textNombreautor;
        TextView textEditorial;
        TextView textCodigo;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            //textPrice = (TextView) itemView.findViewById(R.id.textPrice);
            textTitulo= (TextView) itemView.findViewById(R.id.primario);
            textNombreautor = (TextView) itemView.findViewById(R.id.secundario);
            textEditorial = (TextView) itemView.findViewById(R.id.terciario);
            textCodigo = (TextView) itemView.findViewById(R.id.cuarto);
            itemView.setOnClickListener(this);
        }


        // Da un click a cualquiera de los items
        @Override
        public void onClick(View v) {

            // CodId guarda el id del libro pulsado en la lista del buscador, se pasa de String a Integer por def en BD
            String CodId= textCodigo.getText().toString();
            CodId = CodId.substring(1);
            int CodIdInt = Integer.parseInt(CodId);

            //Levanta la RA
            //Intent intent = new Intent(context, SampleCamActivity.class);
            //context.startActivity(intent);

        }


    }


}