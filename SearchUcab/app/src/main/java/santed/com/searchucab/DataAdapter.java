package santed.com.searchucab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sandravillamizar on 19/12/16.
 */

// Clase usada para la guia de uso

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<String> opcion_lista;

    public DataAdapter(ArrayList<String> countries) {
        this.opcion_lista = countries;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.opcion.setText(opcion_lista.get(i));
    }

    @Override
    public int getItemCount() {
        return opcion_lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView opcion;
        public ViewHolder(View view) {
            super(view);

            opcion = (TextView)view.findViewById(R.id.opcion);
        }
    }
}
