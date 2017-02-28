package santed.com.searchucab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 28/12/2016.
 * Clase que contendra la informacion de una facultad en particular
 * @version 1.1.0
 */
public class Facultad extends Entidad
{
    //Atributos de la clase
    private String nombre, descripcion;
    private float altitud, latitud, longitud;
    private HashMap<String, List<String>> informacion;
    private List<Escuela> listaEscuelas;

    /**
     * Constructor de la clase que recibe todos los datos de la facultad
     * @param nombre El nombre que tiene la facultad
     * @param descripcion La descripcion que tiene la facultad
     * @param informacion Fotos, videos o textos que describiran la facultad
     */
    public Facultad(String nombre, String descripcion, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacion = informacion;
        this.listaEscuelas = new ArrayList<Escuela>();
    }

    /**
     * Constructor de la clase que recibe nombre y descripcion de la facultad
     * @param nombre El nombre que tiene la facultad
     * @param descripcion La descripcion que tiene la facultad
     */
    public Facultad (String nombre, String descripcion)
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
     * @return La longitud en la que se encuentra la Facultad
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * Setter para el atributo Longitud
     * @param longitud El valor de la longitud donde esta la Facultad
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre de la facultad
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar a la facultad
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo descripcion
     * @return La descripcion de la facultad
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Setter del atributo descripcion
     * @param descripcion La descripcion de la facultad
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee esta facultad
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee esta facultad
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee esta facultad
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto a la facultad
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
     * Setter para agregar una nueva informacion de tipo video a la facultad
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
     * Setter para agregar una nueva informacion de tipo foto a la facultad
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
     * Getter para obtener la latitud de donde se ubica la Facultad
     * @return Latitud de la facultad
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica la Facultad
     * @param latitud que tendra la facultad
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica la Facultad
     * @return Altitud de la Facultad
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica la Facultad
     * @param altitud que tendra la facultad
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    /**
     * Getter para obtener las escuelas que pertenecen a la Facultad
     * @return Las escuelas pertenecientes a la Facultad
     */
    public List<Escuela> getListaEscuelas() {
        return listaEscuelas;
    }

    /**
     * Setter para asignar todas las escuelas que pertenecen a la facultad
     * @param listaEscuelas Todas las escuelas que tiene la facultad
     */
    public void setListaEscuelas(List<Escuela> listaEscuelas) {
        this.listaEscuelas = listaEscuelas;
    }

    /**
     * Setter para asignar una escuela en particular a la facultad
     * @param escuela Escuela nueva que contendra la facultad
     */
    public void AgregarEscuela(Escuela escuela)
    {
        this.listaEscuelas.add(escuela);
    }
}
