package santed.com.searchucab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 19/12/2016.
 * Clase que contendra la informacion de un area en particular
 * @version 2.1.0
 * @// FIXME: 28/12/2016 ELIMINAR CONSTRUCTOR CREADO MOMENTANEAMENTE
 */
public class Area
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud;
    private HashMap<String, List<String>> informacion;
    private List<Piso> listaPisos;

    /**
     * Constructor que recibe todos los atributos de la clase
     * @param nombre Nombre que tendra el area
     * @param descripcion La descripcion que tendra el area
     * @param informacion Fotos, videos o textos que describiran el area
     */
    public Area(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
        this.listaPisos = new ArrayList<Piso>();
    }
    
    public Area(String nombre, String descripcion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaPisos = new ArrayList<Piso>();
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

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee esta area
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee esta area
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee esta area
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al area
     * @param fotoInformacion la nueva foto que se le agregara
     */
    public void setFotosInformacion(String fotoInformacion)
    {
        //Obtenemos la lista, le agregamos la nueva foto y actualizamos el hashmap
        List<String> lista = this.informacion.get("foto");
        lista.add(fotoInformacion);
        this.informacion.put("foto", lista);

    }

    /**
     * Setter para agregar una nueva informacion de tipo video al area
     * @param videoInformacion el nuevo video que se le agregara
     */
    public void setVideosInformacion(String videoInformacion)
    {
        //Obtenemos la lista, le agregamos la nueva foto y actualizamos el hashmap
        List<String> lista = this.informacion.get("video");
        lista.add(videoInformacion);
        this.informacion.put("video", lista);

    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al area
     * @param textoInformacion el nuevo texto que se le agregara
     */
    public void setTextosInformacion(String textoInformacion)
    {
        //Obtenemos la lista, le agregamos la nueva foto y actualizamos el hashmap
        List<String> lista = this.informacion.get("texto");
        lista.add(textoInformacion);
        this.informacion.put("texto", lista);

    }

    /**
     * Getter para obtener la latitud de donde se ubica el Area
     * @return Latitud del area
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica el Area
     * @param latitud que tendra el area
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica el Area
     * @return Altitud del area
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica el Area
     * @param altitud que tendra el area
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }


    /**
     * Getter para obtener todos los pisos de esa area
     * @return Los pisos del area
     */
    public List<Piso> getListaPiso() {
        return listaPisos;
    }

    /**
     * Setter para asignar pisos en particular al area
     * @param listaPisos Pisos nuevos que contendra el piso
     */
    public void setListaPisos(List<Piso> listaPisos) {
        this.listaPisos = listaPisos;
    }

    /**
     * Setter para asignar un piso en particular al area
     * @param piso piso nuevo que contendra el area
     */
    public void AgregarPiso(Piso piso)
    {
        this.listaPisos.add(piso);
    }
}
