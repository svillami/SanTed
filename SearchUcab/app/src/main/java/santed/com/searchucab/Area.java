package santed.com.searchucab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 19/12/2016.
 * Clase que contendra la informacion de un areas en particular
 * @version 2.1.0
 */
public class Area extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud, longitud;
    private HashMap<String, List<String>> informacion;
    private List<Piso> listaPisos;

    /**
     * Constructor que recibe todos los atributos de la clase
     * @param nombre Nombre que tendra el areas
     * @param descripcion La descripcion que tendra el areas
     * @param informacion Fotos, videos o textos que describiran el areas
     */
    public Area(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
        this.listaPisos = new ArrayList<Piso>();
    }

    /**
     * Contrustor que recibe los datos basicos del areas
     * @param nombre Nombre que tendra el areas
     * @param descripcion La descripcion que tendra el areas
     */
    public Area(String nombre, String descripcion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaPisos = new ArrayList<Piso>();
        this.informacion = new HashMap<>();
        this.informacion.put("foto", new ArrayList<String>());
        this.informacion.put("video", new ArrayList<String>());
        this.informacion.put("texto", new ArrayList<String>());
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del areas
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Getter del atributo Longitud
     * @return La longitud en la que se encuentra el Area
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * Setter para el atributo Longitud
     * @param longitud El valor de la longitud donde esta el Area
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }


    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion que se le desea agregar al areas
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre del areas
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre del areas que se le desea colocar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee esta areas
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee esta areas
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee esta areas
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al areas
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
     * Setter para agregar una nueva informacion de tipo video al areas
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
     * Setter para agregar una nueva informacion de tipo foto al areas
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
     * @return Latitud del areas
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica el Area
     * @param latitud que tendra el areas
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica el Area
     * @return Altitud del areas
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica el Area
     * @param altitud que tendra el areas
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }


    /**
     * Getter para obtener todos los pisos de esa areas
     * @return Los pisos del areas
     */
    public List<Piso> getListaPiso() {
        return listaPisos;
    }

    /**
     * Setter para asignar pisos en particular al areas
     * @param listaPisos Pisos nuevos que contendra el piso
     */
    public void setListaPisos(List<Piso> listaPisos) {
        this.listaPisos = listaPisos;
    }

    /**
     * Setter para asignar un piso en particular al areas
     * @param piso piso nuevo que contendra el areas
     */
    public void AgregarPiso(Piso piso)
    {
        this.listaPisos.add(piso);
    }
}
