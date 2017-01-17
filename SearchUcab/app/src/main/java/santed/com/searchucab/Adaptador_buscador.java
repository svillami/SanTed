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
    private int profundidad;

    public Adaptador_buscador(Context contexto, ArrayList<?> data, int nivel)
    {
        this.data = data;
        this.contexto = contexto;
        this.nivel = nivel;
        this.profundidad = 1;
    }

    /**
     * Getter para la profundidad que buscaremos
     * @return La profundidad que nos encontramos
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * Setter para la variable profundidad
     * @param profundidad La profundidad a la que iremos
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
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
        /*Sino es el primer nivel significa que cada una de las opciones
        tendra subniveles a la misma altura (arbol)*/
        switch(nivel)
        {
            case -1:

                nombre = (String) data.get(position);
                descripcion = "";
                lugar = "";
                break;

            //Servicios de salud
            case 0:

                Salud salud = (Salud) data.get(position);
                nombre = salud.getNombre();
                descripcion = salud.getDescripcion();
                lugar = "N/A";
                break;

            //Servicios de comida
            case 1:

                Local local = (Local) data.get(position);
                nombre = local.getNombre();
                descripcion = local.getEspecialidad();
                lugar = "N/A";
                break;

            //Servicios de deporte
            case 2:
                Deporte deporte = (Deporte) data.get(position);
                nombre = deporte.getNombre();
                descripcion = deporte.getDescripcion();
                lugar = "N/A";
                break;

            //Servicios Bancarios
            case 3:

                Banco banco = (Banco) data.get(position);
                nombre = banco.getNombre();
                descripcion = banco.getDescripcion();
                lugar = "N/A";
                break;

            //Servicios administrativos
            case 4:

                Dependencia dependencia = (Dependencia) data.get(position);
                nombre = dependencia.getNombre();
                descripcion = dependencia.getDescripcion();
                lugar = "N/A";
                break;

            //Servicios al cliente
            case 5:

                Dependencia dependenciaCliente = (Dependencia) data.get(position);
                nombre = dependenciaCliente.getNombre();
                descripcion = dependenciaCliente.getDescripcion();
                lugar = "N/A";
                break;

            //Laboratorios
            case 6:
                Laboratorio laboratorio = (Laboratorio) data.get(position);
                nombre = laboratorio.getNombre();
                descripcion = laboratorio.getDescripcion();
                lugar = "N/A";
                break;

            //Facultades
            case 7:

                Facultad facultad = (Facultad) data.get(position);
                nombre = facultad.getNombre();
                descripcion = facultad.getDescripcion();
                lugar = "N/A";
                break;

            //Facultades
            case 8:

                Escuela escuela = (Escuela) data.get(position);
                nombre = escuela.getNombre();
                descripcion = escuela.getDescripcion();
                lugar = "N/A";
                break;

            //Desgloze areas
            case 9:

                switch (profundidad)
                {
                    case 1:

                        Area area = (Area) data.get(position);
                        nombre = area.getNombre();
                        descripcion = area.getDescripcion();
                        lugar = "N/A";
                        break;
                }
                break;

            //En caso de venir del buscador search
            case 10:

                DataBuscador dataSearch = (DataBuscador) data.get(position);
                nombre = dataSearch.titulo;
                descripcion = dataSearch.editorial;
                lugar = dataSearch.nombreautor;
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
