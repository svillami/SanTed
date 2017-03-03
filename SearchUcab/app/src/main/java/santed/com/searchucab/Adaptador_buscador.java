package santed.com.searchucab;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        String nombre = null;
        String descripcion = null;
        String lugar = null;
        int idLugar = 0;

        //Aqui casteamos para saber a que no estamos refiriendo
        /*Sino es el primer nivel significa que cada una de las opciones
        tendra subniveles a la misma altura (arbol)*/
        switch(nivel)
        {
            //Menu Principal
            case -1:

                nombre = (String) data.get(position);
                descripcion = "";
                lugar = "";
                //idLugar = Utility.obtenerIDimagen(nombre);

                break;

            //Servicios de serviciosdesalud
            case 0:

                Salud salud = (Salud) data.get(position);
                nombre = salud.getNombre();
                descripcion = salud.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (salud.getTextosInformacion().get(0), salud.getTextosInformacion().get(1));
                //idLugar = Utility.obtenerIDimagen(nombre);

                break;

            //Servicios de serviciosdecomida
            case 1:

                Local local = (Local) data.get(position);
                nombre = local.getNombre();
                descripcion = local.getEspecialidad();
                lugar = Utility.obtenerUbicacion
                        (local.getTextosInformacion().get(0), local.getTextosInformacion().get(1));
                break;

            //Servicios de serviciosdedeporte
            case 2:
                Deporte deporte = (Deporte) data.get(position);
                nombre = deporte.getNombre();
                descripcion = deporte.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (deporte.getTextosInformacion().get(0),
                                deporte.getTextosInformacion().get(1));;
                break;

            //Servicios Bancarios
            case 3:

                Banco banco = (Banco) data.get(position);
                nombre = banco.getNombre();
                descripcion = banco.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (banco.getTextosInformacion().get(0), banco.getTextosInformacion().get(1));
                break;

            //Servicios administrativos
            case 4:

                Dependencia dependencia = (Dependencia) data.get(position);
                nombre = dependencia.getNombre();
                descripcion = dependencia.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (dependencia.getTextosInformacion().get(0),
                                dependencia.getTextosInformacion().get(1));
                break;

            //Servicios al serviciosalcliente
            case 5:

                Dependencia dependenciaCliente = (Dependencia) data.get(position);
                nombre = dependenciaCliente.getNombre();
                descripcion = dependenciaCliente.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (dependenciaCliente.getTextosInformacion().get(0),
                                dependenciaCliente.getTextosInformacion().get(1));;
                break;

            //Laboratorios
            case 6:
                Laboratorio laboratorio = (Laboratorio) data.get(position);
                nombre = laboratorio.getNombre();
                descripcion = laboratorio.getDescripcion();
                lugar =  Utility.obtenerUbicacion
                        (laboratorio.getTextosInformacion().get(0), laboratorio.getTextosInformacion().get(1));
                break;

            //Facultades
            case 7:

                Facultad facultad = (Facultad) data.get(position);
                nombre = facultad.getNombre();
                descripcion = facultad.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (facultad.getTextosInformacion().get(0), facultad.getTextosInformacion().get(1));
                break;

            //Escuela
            case 8:

                Escuela escuela = (Escuela) data.get(position);
                nombre = escuela.getNombre();
                descripcion = escuela.getDescripcion();
                lugar = Utility.obtenerUbicacion
                        (escuela.getTextosInformacion().get(0), escuela.getTextosInformacion().get(1));
                break;

            //Desgloze areas
            case 9:

                switch (profundidad)
                {
                    case 1:

                        Area area = (Area) data.get(position);
                        nombre = area.getNombre();
                        descripcion = area.getDescripcion();
                        lugar = "";
                        break;

                    case 2:

                        //Equivalente al IS en C#
                        if (data.get(position) instanceof Auditorio)
                        {
                            Auditorio auditorio = (Auditorio) data.get(position);
                            nombre = auditorio.getNombre();
                            descripcion = auditorio.getDescripcion();
                            lugar = Utility.obtenerPiso(auditorio.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Banco)
                        {
                            Banco banco2 = (Banco) data.get(position);
                            nombre = banco2.getNombre();
                            descripcion = banco2.getDescripcion();
                            lugar = Utility.obtenerPiso(banco2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Dependencia)
                        {
                            Dependencia dependencia2 = (Dependencia) data.get(position);
                            nombre = dependencia2.getNombre();
                            descripcion = dependencia2.getDescripcion();
                            lugar = Utility.obtenerPiso(dependencia2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Deporte)
                        {
                            Deporte deporte2 = (Deporte) data.get(position);
                            nombre = deporte2.getNombre();
                            descripcion = deporte2.getDescripcion();
                            lugar = Utility.obtenerPiso(deporte2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Escuela)
                        {
                            Escuela escuela2 = (Escuela) data.get(position);
                            nombre = escuela2.getNombre();
                            descripcion = escuela2.getDescripcion();
                            lugar = Utility.obtenerPiso(escuela2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Facultad)
                        {
                            Facultad facultad2 = (Facultad) data.get(position);
                            nombre = facultad2.getNombre();
                            descripcion = facultad2.getDescripcion();
                            lugar = Utility.obtenerPiso(facultad2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Laboratorio)
                        {
                            Laboratorio laboratorio2 = (Laboratorio) data.get(position);
                            nombre = laboratorio2.getNombre();
                            descripcion = laboratorio2.getDescripcion();
                            lugar = Utility.obtenerPiso(laboratorio2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Local)
                        {
                            Local local2 = (Local) data.get(position);
                            nombre = local2.getNombre();
                            descripcion = local2.getEspecialidad();
                            lugar = Utility.obtenerPiso(local2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Monumento)
                        {
                            Monumento monumento2 = (Monumento) data.get(position);
                            nombre = monumento2.getNombre();
                            descripcion = monumento2.getDescripcion();
                            lugar = Utility.obtenerPiso(monumento2.getTextosInformacion().get(0));
                        }
                        else if(data.get(position) instanceof Salud)
                        {
                            Salud salud2 = (Salud) data.get(position);
                            nombre = salud2.getNombre();
                            descripcion = salud2.getDescripcion();
                            lugar = Utility.obtenerPiso(salud2.getTextosInformacion().get(0));
                        }

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

        //Separamos el nombre
        String nombreSeparado [] = nombre.split(" ");

        /*Como los nombres en la BD tendran espacios pero no pueden en las fotos que estan en la
        carpeta drawables, concatenaremos el nombre que se hizo split arriba para luego obtener
         la imagen*/
        String nombreConcatenado = "";
        for (String aux : nombreSeparado)
        {
            nombreConcatenado = nombreConcatenado + aux;
        }

        //Ponemos en minúscula para evitar el problema de mayúscula
        nombreConcatenado = nombreConcatenado.toLowerCase();

        //Obtenemos la imagen dependendiendo del nombre de la Entidad
        idLugar = this.contexto.getResources().getIdentifier
                (nombreConcatenado, "drawable", this.contexto.getPackageName());

        /* Si el ID da 0 en la instruccion anterior, quiere decir que no existe una imagen valida
        * para la entidad (aun no se le ha tomado foto) por lo tanto utilizaremos una foto por
        * defecto*/
        if (idLugar == 0)
        {
            idLugar = R.drawable.nodisponible;
        }

        holder.Informacion_Principal.setText(nombre);
        holder.Informacion_Secundaria.setText(descripcion);
        holder.Informacion_Terciaria.setText(lugar);
        holder.Imagen_Lugar.setImageResource(idLugar);
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
