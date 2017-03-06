package santed.com.searchucab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Teddy J Sears on 28/12/2016.
 * Clase que contendra la informacion de un local de serviciosdecomida en particular
 * @version 1.1.0
 * @// TODO: 28/12/2016 Falta agregarle la informacion restante 
 */
public class Local extends Entidad
{
    //Atributos de la clase
    private String nombre, especialidad, horaInicio, horaFin;
    private float altitud, latitud, precioPromedio, longitud;
    private HashMap<String, List<String>> informacion;


    /**
     * Constructor de la clase que recibe todos los datos del local
     * @param nombre El nombre que tiene el local
     * @param especialidad La especialidad de serviciosdecomida que tiene el local
     * @param informacion Fotos, videos o textos que describiran el local
     */
    public Local(String nombre, String especialidad, HashMap<String, List<String>> informacion)
    {
        super();
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.informacion = informacion;
    }

    /**
     * Constructor de la clase que recibe el nombre y la especialidad del local
     * @param nombre El nombre que tiene el local
     * @param especialidad La especialidad de serviciosdecomida que tiene el local
     */
    public Local (String nombre, String especialidad)
    {
        super();
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.informacion = new HashMap<>();
        this.informacion.put("foto", new ArrayList<String>());
        this.informacion.put("video", new ArrayList<String>());
        this.informacion.put("texto", new ArrayList<String>());
    }

    /**
     * Getter del atributo Longitud
     * @return La longitud en la que se encuentra el local de serviciosdecomida
     */
    public float getLongitud() {
        return longitud;
    }

    /**
     * Setter para el atributo Longitud
     * @param longitud El valor de la longitud donde esta el local de serviciosdecomida
     */
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    /**
     * Getter del atributo nombre
     * @return El nombre del local
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre El nombre que se le desea asignar al local
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo especialidad
     * @return La especialidad culinaria del local
     */
    public String getEspecialidad()
    {
        return especialidad;
    }

    /**
     * Setter del atributo descripcion
     * @param especialidad La especialidad culinaria del local
     */
    public void setEspecialidad(String especialidad)
    {
        this.especialidad = especialidad;
    }

    /**
     * Getter para obtener toda la informacion relacionada con fotos
     * @return Lista con todas las fotos que posee este local
     */
    public List<String> getFotosInformacion()
    {
        return this.informacion.get("foto");
    }

    /**
     * Getter para obtener toda la informacion relacionada con videos
     * @return Lista con todos los videos que posee este local
     */
    public List<String> getVideosInformacion()
    {
        return this.informacion.get("video");
    }

    /**
     * Getter para obtener toda la informacion relacionada con textos
     * @return Lista con todos los textos que posee este local
     */
    public List<String> getTextosInformacion()
    {
        return this.informacion.get("texto");
    }

    /**
     * Setter para agregar una nueva informacion de tipo foto al local
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
     * Setter para agregar una nueva informacion de tipo video al local
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
     * Setter para agregar una nueva informacion de tipo foto al local
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
     * Getter para obtener la hora en que inicia la prestación de servicio del Local
     * @return La hora de inicio en que comienza a prestar servicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Setter para indicar la hora en que comienza a prestar servicio del Local
     * @param horaInicio La hora en que comienza a prestar servicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Getter para obtener la hora en que termina la prestación de servicio del Local
     * @return La hora de cierre del local
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Setter para indicar la hora en que termina la prestación de servicio del Local
     * @param horaFin La hora de cierre del local
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Getter para obtener la latitud de donde se ubica el Local
     * @return Latitud del local
     */
    public float getLatitud() {
        return latitud;
    }

    /**
     * Setter para asignar la latitud de donde se ubica el Local
     * @param latitud que tendra el local
     */
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter para obtener la Altitud de donde se ubica el Local
     * @return Altitud del local
     */
    public float getAltitud() {
        return altitud;
    }

    /**
     * Setter para asignar la altitud de donde se ubica el Local
     * @param altitud que tendra el local
     */
    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    /**
     * Getter para indicar el precio en promedio que se gasta al consumir en el local
     * @return Precio promedio que cuesta un menu en el local
     */
    public float getPrecioPromedio() {
        return precioPromedio;
    }

    /**
     * Setter para establecer el precio promedio de un menu en el Local
     * @param precioPromedio El costo en promedio que cuesta al consumir en el Local
     */
    public void setPrecioPromedio(float precioPromedio) {
        this.precioPromedio = precioPromedio;
    }


}
