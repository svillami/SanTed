package santed.com.searchucab;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 28/12/2016.
 * Clase que contendra la informacion de una dependencia de la universidad en particular
 * @version 1.1.0
 */
public class Dependencia extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion, horaInicio, horaFin;
    private float altitud, latitud;
    private HashMap<String, List<String>> informacion;
    private boolean administrativo;

    /**
     * Constructor de la clase que recibe todos los datos de la dependencia
     * @param nombre El nombre que tiene la dependencia
     * @param descripcion La descripcion que tiene la dependencia
     * @param informacion Fotos, videos o textos que describiran la dependencia
     * @param administrativo Positivo si la dependencia es de tipo administrativo, falso si es
     *                       de servicios al cliente
     */
    public Dependencia(String nombre, String descripcion,
                       HashMap<String, List<String>> informacion, boolean administrativo)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
        this.administrativo = administrativo;
    }

    /**
     * Constructor que recibe los datos basicos de la depenencia
     * @param nombre El nombre de la dependencia
     * @param descripcion La descripcion de la dependencia
     * @param administrativo Tipo que indica si la dependencia es o no administrativa
     */
    public Dependencia (String nombre, String descripcion, boolean administrativo)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.administrativo = administrativo;
    }

    /**
     * Getter para del atributo administrativo
     * @return El tipo de dependencia que se tiene
     */
    public boolean isAdministrativo() {
        return administrativo;
    }

    /**
     * Setter para el atributo administrativo
     * @param administrativo Variable que indica si la dependencia es o no de tipo administrativo
     */
    public void setAdministrativo(boolean administrativo) {
        this.administrativo = administrativo;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre de la dependencia
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar a la dependencia
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion de la dependencia
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion de la dependencia
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee esta dependencia
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee esta dependencia
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee esta dependencia
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto a la dependencia
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
     * Setter para agregar una nueva informacion de tipo video a la dependencia
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
     * Setter para agregar una nueva informacion de tipo foto a la dependencia
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
     * Getter para obtener la hora en que inicia la prestación de servicio de la dependencia
     * @return La hora de inicio en que comienza a prestar servicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Setter para indicar la hora en que comienza a prestar servicio de la dependencia
     * @param horaInicio La hora en que comienza a prestar servicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Getter para obtener la hora en que termina la prestación de servicio de la dependencia
     * @return La hora de cierre de la dependencia
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Setter para indicar la hora en que termina la prestación de servicio de la dependencia
     * @param horaFin La hora de cierre de la dependencia
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Getter para obtener la latitud de donde se ubica la Dependencia
     * @return Latitud de la dependencia
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica la Dependencia
     * @param latitud que tendra la dependencia
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica la dependencia
     * @return Altitud de la dependencia
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica la Dependencia
     * @param altitud que tendra la dependencia
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }
}
