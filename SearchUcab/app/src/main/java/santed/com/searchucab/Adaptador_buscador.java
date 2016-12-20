package santed.com.searchucab;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_buscador extends RecyclerView.Adapter<Adaptador_buscador.ViewHolder>
{

    private ArrayList<Area> data;

    public Adaptador_buscador(ArrayList<Area> data)
    {
        this.data = data;
    }

    @Override
    public Adaptador_buscador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Adaptador_buscador.ViewHolder holder, int position) {
        holder.informacion.setText(data.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView informacion;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.informacion = (TextView) itemView.findViewById(R.id.textFishName);
        }
    }
}
