package santed.com.searchucab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 25/12/2016.
 * Clase que contendra la informacion de un banco en particular
 * @version 2.1.0
 * @// FIXME: 28/12/2016 ELIMINAR EL CONSTRUCTOR TEMPORAL CREADO
 */
public class Banco extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion, horaInicio, horaFin;
    private float altitud, latitud;
    private HashMap<String, List<String>> informacion;


    /**
     * Constructor de la clase que recibe todos los datos del banco
     * @param nombre El nombre que tiene el banco
     * @param descripcion La descripcion que tiene el banco
     * * @param informacion Fotos, videos o textos que describiran el banco
     */
    public Banco(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    public Banco(String nombre, String descripcion)
    {
        super();
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

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee este banco
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee este banco
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee este banco
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al banco
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
     * Setter para agregar una nueva informacion de tipo video al banco
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
     * Setter para agregar una nueva informacion de tipo foto al banco
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
     * Getter para obtener la latitud de donde se ubica el Banco
     * @return Latitud del banco
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica el Banco
     * @param latitud que tendra el banco
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica el Banco
     * @return Altitud del banco
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica el Banco
     * @param altitud que tendra el banco
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    /**
     * Getter para obtener la hora en que inicia la prestación de servicio del banco
     * @return La hora de inicio en que comienza a prestar servicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Setter para indicar la hora en que comienza a prestar servicio el banco
     * @param horaInicio La hora en que comienza a prestar servicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Getter para obtener la hora en que termina la prestación de servicio del banco
     * @return La hora de cierre del banco
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Setter para indicar la hora en que termina la prestación de servicio del banco
     * @param horaFin La hora de cierre del banco
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
