package santed.com.searchucab;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 15/01/2017.
 * Clase que contendra la informacion de un centro de salud en particular
 * @version 1.0.0
 */
public class Salud extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud, longitud;
    private HashMap<String, List<String>> informacion;

    /**
     * Constructor de la clase que recibe todos los datos del centro de salud
     * @param nombre El nombre que tiene el centro de salud
     * @param descripcion La descripcion que tiene el centro de salud
     * @param informacion Fotos, videos o textos que describiran el centro de salud
     */
    public Salud(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    /**
     * Getter del atributo Longitud
     * @return La longitud en la que se encuentra el centro de salud
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * Setter para el atributo Longitud
     * @param longitud El valor de la longitud donde esta el centro de salud
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    /**
     * Constructor de la clase que recibe nombre y descripcion del centro de salud
     * @param nombre El nombre que tiene el laobratorio
     * @param descripcion La descripcion que tiene el centro de salud
     */
    public Salud(String nombre, String descripcion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre de la centro de salud
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar al centro de salud
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del centro de salud
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion del centro de salud
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee este centro de salud
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee este centro de salud
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee este centro de salud
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al centro de salud
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
     * Setter para agregar una nueva informacion de tipo video al centro de salud
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
     * Setter para agregar una nueva informacion de tipo foto al centro de salud
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
     * Getter para obtener la latitud de donde se ubica al centro de salud
     * @return Latitud de la centro de salud
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica al centro de salud
     * @param latitud que tendra la centro de salud
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica al centro de salud
     * @return Altitud de la centro de salud
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica al centro de salud
     * @param altitud que tendra la centro de salud
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }
}
