package santed.com.searchucab;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 28/12/2016.
 * Clase que contendra la informacion de un monumento en particular
 * @version 1.1.0
 */
public class Monumento extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud;
    private HashMap<String, List<String>> informacion;

    /**
     * Constructor de la clase que recibe nombre y descripcion del monumento
     * @param nombre El nombre que tiene el monumento
     * @param descripcion La descripcion que tiene el monumento
     */
    public Monumento(String nombre, String descripcion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor de la clase que recibe todos los datos del monumento
     * @param nombre El nombre que tiene el monumento
     * @param descripcion La descripcion que tiene el monumento
     * @param informacion Fotos, videos o textos que describiran el monumento
     */
    public Monumento(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre del monumento
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar al monumento
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del monumento
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion del monumento
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee este monumento
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee este monumento
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee este monumento
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al monumento
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
     * Setter para agregar una nueva informacion de tipo video al monumento
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
     * Setter para agregar una nueva informacion de tipo foto al monumento
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
     * Getter para obtener la latitud de donde se ubica el Monumento
     * @return Latitud del monumento
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica el Monumento
     * @param latitud que tendra el monumento
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica el Monumento
     * @return Altitud del monumento
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica el monumento
     * @param altitud que tendra el monumento
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

}
