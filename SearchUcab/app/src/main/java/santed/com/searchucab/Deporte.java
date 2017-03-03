package santed.com.searchucab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 15/01/2017.
 * Clase que contendra la informacion de un servicio de serviciosdedeporte en particular
 * @version 1.0.0
 */
public class Deporte extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud, longitud;
    private HashMap<String, List<String>> informacion;

    /**
     * Constructor de la clase que recibe todos los datos del servicio de serviciosdedeporte
     * @param nombre El nombre que tiene el servicio de serviciosdedeporte
     * @param descripcion La descripcion que tiene el servicio de serviciosdedeporte
     * @param informacion Fotos, videos o textos que describiran el servicio de serviciosdedeporte
     */
    public Deporte(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    /**
     * Constructor de la clase que recibe nombre y descripcion del servicio de serviciosdedeporte
     * @param nombre El nombre que tiene el laobratorio
     * @param descripcion La descripcion que tiene el servicio de serviciosdedeporte
     */
    public Deporte(String nombre, String descripcion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = new HashMap<>();
        this.informacion.put("foto", new ArrayList<String>());
        this.informacion.put("video", new ArrayList<String>());
        this.informacion.put("texto", new ArrayList<String>());
    }

    /**
     * Getter del atributo Longitud
     * @return La longitud en la que se encuentra en el servicio deportivo
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * Setter para el atributo Longitud
     * @param longitud El valor de la longitud donde este servicio deportivo
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre de la servicio de serviciosdedeporte
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar al servicio de serviciosdedeporte
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion del servicio de serviciosdedeporte
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion del servicio de serviciosdedeporte
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee este servicio de serviciosdedeporte
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee este servicio de serviciosdedeporte
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee este servicio de serviciosdedeporte
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al servicio de serviciosdedeporte
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
     * Setter para agregar una nueva informacion de tipo video al servicio de serviciosdedeporte
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
     * Setter para agregar una nueva informacion de tipo foto al servicio de serviciosdedeporte
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
     * Getter para obtener la latitud de donde se ubica al servicio de serviciosdedeporte
     * @return Latitud de la servicio de serviciosdedeporte
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica al servicio de serviciosdedeporte
     * @param latitud que tendra la servicio de serviciosdedeporte
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica al servicio de serviciosdedeporte
     * @return Altitud de la servicio de serviciosdedeporte
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica al servicio de serviciosdedeporte
     * @param altitud que tendra la servicio de serviciosdedeporte
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }
}
