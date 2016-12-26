package santed.com.searchucab;

/**
 * Created by Teddy J Sears on 25/12/2016.
 * Clase que contendra la informacion de un banco en particular
 */
public class Banco
{
    //Atributos de la clase
    private String nombre;
    private String descripcion;

    /**
     * Constructor de la clase que recibe todos los datos del banco
     * @param nombre El nombre que tiene el banco
     * @param descripcion La descripcion que tiene el banco
     */
    public Banco(String nombre, String descripcion)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre del banco
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar al banco
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del banco
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion del banco
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}
