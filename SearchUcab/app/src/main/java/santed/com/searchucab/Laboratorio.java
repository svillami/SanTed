package santed.com.searchucab;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 15/01/2017.
 * Clase que contendra la informacion de un Laboratorio en particular
 * @version 1.0.0
 */
public class Laboratorio
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud;
    private HashMap<String, List<String>> informacion;

    /**
     * Constructor de la clase que recibe todos los datos del Laboratorio
     * @param nombre El nombre que tiene el laboratorio
     * @param descripcion La descripcion que tiene el laboratorio
     * @param informacion Fotos, videos o textos que describiran el laboratorio
     */
    public Laboratorio(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    /**
     * Constructor de la clase que recibe nombre y descripcion del laboratorio
     * @param nombre El nombre que tiene el laobratorio
     * @param descripcion La descripcion que tiene el laboratorio
     */
    public Laboratorio(String nombre, String descripcion)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre de la laboratorio
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar a la laboratorio
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion de la laboratorio
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion de la laboratorio
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee esta laboratorio
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee esta laboratorio
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee esta laboratorio
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto a la laboratorio
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
     * Setter para agregar una nueva informacion de tipo video a la laboratorio
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
     * Setter para agregar una nueva informacion de tipo foto a la laboratorio
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
     * Getter para obtener la latitud de donde se ubica la laboratorio
     * @return Latitud de la laboratorio
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica la laboratorio
     * @param latitud que tendra la laboratorio
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica la laboratorio
     * @return Altitud de la laboratorio
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica la laboratorio
     * @param altitud que tendra la laboratorio
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }
}
