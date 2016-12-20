package santed.com.searchucab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Teddy J Sears on 19/12/2016.
 */
public class Adaptador_area extends ArrayAdapter<Area>
{
    Context context;
    int LayoutResourceId;
    Area[] data;

    public Adaptador_area(Context context, int LayoutResourceId, Area[] data)
    {
        super(context, LayoutResourceId, data);
        this.context = context;
        this.LayoutResourceId = LayoutResourceId;
        this.data = data;
    }

    public View getView(int posicion, View convertView, ViewGroup parent)
    {
        View row = convertView;
        Area_holder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
            row = inflater.inflate(LayoutResourceId, parent, false);

            holder = new Area_holder();
            holder.nombre = (TextView) row.findViewById(R.id.primario);
            holder.descripcion = (TextView) row.findViewById(R.id.secundario);
            row.setTag(holder);
        }
        else
            holder = (Area_holder) row.getTag();

        Area area = data[posicion];
        holder.nombre.setText(area.getNombre());
        holder.descripcion.setText(area.getDescripcion());


        return row;

    }

    static class Area_holder
    {
        TextView nombre, descripcion;
    }
}

























/*

package com.example.sandra.primerapruebara;
  import android.app.Fragment;
        import android.app.FragmentManager;
         import android.content.SharedPreferences;
         import android.support.v7.app.AppCompatActivity; 
        import android.os.Bundle;  
        import android.content.Intent; 
        import android.content.Context; 
        import android.support.v4.content.ContextCompat;
         import android.support.v7.widget.RecyclerView; 
        import android.view.LayoutInflater; 
        import android.view.View;
         import android.view.ViewGroup;
         import android.widget.TextView;
         import android.widget.Toast; 
        import java.util.Collections;
         import java.util.List;   
public class AdaptandoBuscador extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{      public Context context; 
    private LayoutInflater inflater; 
    List<DataBuscador> data= Collections.emptyList(); 
    DataBuscador current;  
    public static final String codidint = "codidint";  

// / create constructor to initialize context and data sent from MainActivity 
public AdaptandoBuscador(Context context, List<DataBuscador> data)
{         this.context=context; 
    inflater= LayoutInflater.from(context); 
    this.data=data;     }   

// Inflate the layout when ViewHolder created 
@Override 
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {         View view=inflater.inflate(R.layout.activity_adaptando_buscador, parent, false); 
        MyHolder holder=new MyHolder(view);         return holder; 
    }  

// Bind data 
 @Override 
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {          // Get current position of item in RecyclerView to bind data and assign values from list 
         MyHolder myHolder= (MyHolder) holder; 
        DataBuscador current=data.get(position); 
        myHolder.textPrice.setText(current.price); 
        myHolder.textTitulo.setText(current.titulo); 
        myHolder.textNombreautor.setText("Autor: " + current.nombreautor); 
        myHolder.textEditorial.setText("Editorial: " + current.editorial); 
        myHolder.textCodigo.setText("C" +current.codigo); 
        myHolder.textCodigo.setTextColor(ContextCompat.getColor(context, R.color.white)); 
    }  

// return total item from List 
  @Override     public int getItemCount()
{         return data.size(); 
}   

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {          TextView textTitulo; 
        TextView textNombreautor; 
        TextView textEditorial; 
        TextView textCodigo;  

// create constructor to get widget reference 
  public MyHolder(View itemView) { 
      super(itemView); 
      textPrice = (TextView) itemView.findViewById(R.id.textPrice); 
      textTitulo= (TextView) itemView.findViewById(R.id.textTitulo); 
      textNombreautor = (TextView) itemView.findViewById(R.id.textNombreautor); 
      textEditorial = (TextView) itemView.findViewById(R.id.textEditorial); 
      textCodigo = (TextView) itemView.findViewById(R.id.textCodigo); 
      itemView.setOnClickListener(this); 
  }   

// Da un click a cualquiera de los items 
  @Override
  public void onClick(View v)
  {  
      // CodId guarda el id del libro pulsado en la lista del buscador, se pasa de String a Integer por def en BD 
               String CodId= textCodigo.getText().toString(); 
      CodId = CodId.substring(1); 
      int CodIdInt = Integer.parseInt(CodId);  
      //Muestra el id del libro escogido 
      Toast.makeText(context, "Id Integer: " + CodIdInt, Toast.LENGTH_SHORT).show();  
      //Pasando el valor del libro a las Preferencias 
       Intent i = new Intent(context, PreferenciasActivity.class); 
      i.putExtra("codidint", CodIdInt);             context.startActivity(i);   
      //Levanta la RA 
      Intent intent = new Intent(context, SampleCamActivity.class); 
      context.startActivity(intent);  
  }   
    }   
} */