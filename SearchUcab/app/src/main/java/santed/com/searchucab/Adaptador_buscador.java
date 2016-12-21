package santed.com.searchucab;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_buscador extends RecyclerView.Adapter<Adaptador_buscador.ViewHolder>
{

    private ArrayList<Area> data;
    private Context contexto;

    public Adaptador_buscador(Context contexto, ArrayList<Area> data)
    {
        this.data = data;
        this.contexto = contexto;
    }

    @Override
    public Adaptador_buscador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View Buscador = inflater.inflate(R.layout.lugar_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(Buscador);

        return viewHolder;

     //   return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Adaptador_buscador.ViewHolder holder, int position)
    {
        holder.Imagen_Lugar.setImageResource(R.drawable.buscar);
        holder.Informacion_Principal.setText(data.get(position).getNombre());
        holder.Informacion_Secundaria.setText(data.get(position).getDescripcion());
        holder.Informacion_Terciaria.setText("N/A");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView Imagen_Lugar;
        TextView Informacion_Principal, Informacion_Secundaria, Informacion_Terciaria;


        public ViewHolder(View itemView)
        {
            super(itemView);
            this.Imagen_Lugar = (ImageView) itemView.findViewById(R.id.foto_lugar);
            this.Informacion_Principal = (TextView) itemView.findViewById(R.id.primario);
            this.Informacion_Secundaria = (TextView) itemView.findViewById(R.id.secundario);
            this.Informacion_Terciaria = (TextView) itemView.findViewById(R.id.terciario);
        }
    }
}
