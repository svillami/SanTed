package santed.com.searchucab;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase que sera el adaptador del buscador para listrar las diferentes areas que se quieran buscar
 */
public class Adaptador_buscador extends RecyclerView.Adapter<Adaptador_buscador.ViewHolder> implements OnClickListener
{

    private ArrayList<?> data;
    private Context contexto;
    private OnClickListener listener;
    private int nivel;

    public Adaptador_buscador(Context contexto, ArrayList<?> data, int nivel)
    {
        this.data = data;
        this.contexto = contexto;
        this.nivel = nivel;
    }


    @Override
    public Adaptador_buscador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View Buscador = inflater.inflate(R.layout.lugar_row, parent, false);

        //Creamos el listener que escuchara el click del usuario al seleccionar un elemento
        Buscador.setOnClickListener(this);

        ViewHolder viewHolder = new ViewHolder(Buscador);

        return viewHolder;

     //   return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Adaptador_buscador.ViewHolder holder, int position)
    {
        holder.Imagen_Lugar.setImageResource(R.drawable.buscar);

        String nombre = null;
        String descripcion = null;
        String lugar = null;

        //Aqui casteamos para saber a que no estamos refiriendo
        switch(nivel)
        {
            case -1:

                nombre = (String) data.get(position);
                descripcion = "";
                lugar = "";
                break;

            /*Sino es el primer nivel significa que cada una de las opciones
            tendra subniveles a la misma altura (arbol)*/
            case 1:

                Area area = (Area) data.get(position);
                nombre = area.getNombre();
                descripcion = area.getDescripcion();
                lugar = "N/A";
                break;

            case 2:

                Banco banco = (Banco) data.get(position);
                nombre = banco.getNombre();
                descripcion = banco.getDescripcion();
                lugar = "N/A";
                break;
        }

        /*
        if (nivel == -1)
        {
           nombre = (String) data.get(position);
            descripcion = "";
            lugar = "";

        }

        /*Sino es el primer nivel significa que cada una de las opciones
         tendra subniveles a la misma altura (arbol)
        else if (nivel == 1)
            {

                Area area = (Area) data.get(position);
                nombre = area.getNombre();
                descripcion = area.getDescripcion();
                lugar = "N/A";
            }
            else
            {
                Banco banco = (Banco) data.get(position);
                nombre = banco.getNombre();
                descripcion = banco.getDescripcion();
                lugar = "N/A";
            }

        */

        holder.Informacion_Principal.setText(nombre);
        holder.Informacion_Secundaria.setText(descripcion);
        holder.Informacion_Terciaria.setText(lugar);

    }

    /**
     * Setter para configurar el listener del adaptador
     * @param listener el listener que estara pendiente de cuando se haga click en una opcion
     */
    public void setOnclickListener (OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v)
    {
        //Si el listener no es nulo le pasamos la vista
        if (this.listener != null)
        {
            listener.onClick(v);
        }
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

    /**
     * Metodo que se encargara de vaciar la lista
     */
    public void LimpiarData ()
    {
        //Obtenemos el tamano del arreglo para determinar si la lista contiene elementos
        int tamano = this.data.size();

        //Si la lista realmente contiene elementos vaciaremos el RecyclerView
        if (tamano > 0)
        {
            //Eliminamos uno por uno
            for (int i = 0; i < tamano; i++)
            {
                this.data.remove(0);
            }

            //Notificamos que se han eliminado elementos
            this.notifyItemRangeRemoved(0, tamano);
        }
    }

}
