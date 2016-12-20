package santed.com.searchucab;

/**
 * Created by Teddy J Sears on 19/12/2016.
 * Clase que contendra la informacion de un area en particular
 */
public class Area
{
    //Atributos de la clase
    private String nombre, descripcion;

    /**
     * Constructor que recibe todos los atributos de la clase
     * @param nombre Nombre que tendra el area
     * @param descripcion La descripcion que tendra el area
     */
    public Area(String nombre, String descripcion)
    {
        super();

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del area
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion que se le desea agregar al area
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre del area
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre del area que se le desea colocar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
