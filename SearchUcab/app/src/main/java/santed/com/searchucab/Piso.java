package santed.com.searchucab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy J Sears on 28/12/2016.
 * Clase que contendra la informacion de un piso en particular en un area
 * @version 1.0.0
 * @// TODO: 15/01/2017 Incluir Laboratorio, Salud y Deporte
 */
public class Piso
{
    //Atributos de la clase
    private int nombre;
    private List<String> listaSalones;
    private List<Monumento> listaMonumentos;
    private List<Auditorio> listaAuditorios;
    private List<Local> listaLocal;
    private List<Banco> listaBanco;
    private List<Facultad> listaFacultad;
    private List<Dependencia> listaDependencia;

    /**
     * Constructor que cargara el piso solo con su numero e inicializa la lista de salones
     * @param nombre El numero del piso
     */
    public Piso (int nombre)
    {
        this.nombre = nombre;
        this.listaSalones = new ArrayList<String>();
    }

    /**
     * Constructor que recibira el numero y los salones deseados
     * @param nombre El numero del piso
     * @param listaSalones Lista de salones que tenga ese piso
     */
    public Piso (int nombre, List<String> listaSalones)
    {
        this.nombre = nombre;
        this.listaSalones = listaSalones;
    }

    /**
     * Constructor que recibira todos los datos y lugares que contiene un piso
     * @param nombre El numero del piso
     * @param listaSalones Lista de salones que tenga ese piso
     * @param listaMonumentos Lista de monumentos que tenga ese piso
     * @param listaAuditorios Lista de auditorios que tenga ese piso
     * @param listaLocal Lista de locales que tenga ese piso
     * @param listaBanco Lista de bancos que tenga ese piso
     * @param listaFacultad Lista de facultades que tenga ese piso
     * @param listaDependencia Lista de dependencias que tenga ese piso
     */
    public Piso(int nombre, List<String> listaSalones, List<Monumento> listaMonumentos,
                List<Auditorio> listaAuditorios, List<Local> listaLocal, List<Banco> listaBanco,
                List<Facultad> listaFacultad, List<Dependencia> listaDependencia)
    {
        this.nombre = nombre;
        this.listaSalones = listaSalones;
        this.listaMonumentos = listaMonumentos;
        this.listaAuditorios = listaAuditorios;
        this.listaLocal = listaLocal;
        this.listaBanco = listaBanco;
        this.listaFacultad = listaFacultad;
        this.listaDependencia = listaDependencia;
    }

    /**
     * Getter para obtener el numero del piso
     * @return El numero del piso
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Setter para establecer el numero del piso
     * @param nombre Numero de piso
     */
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter para obtener todos los salones de ese piso
     * @return Los salones del piso
     */
    public List<String> getListaSalones() {
        return listaSalones;
    }

    /**
     * Setter para establecer la cantidad de salones del piso
     * @param listaSalones Los salones que esten en ese piso
     */
    public void setListaSalones(List<String> listaSalones) {
        this.listaSalones = listaSalones;
    }

    /**
     * Setter para asignar un salon en particular al piso
     * @param salon Salon nuevo que contendra el piso
     */
    public void AgregarSalon(String salon)
    {
        this.listaSalones.add(salon);
    }

    /**
     * Getter para obtener todos los monumentos de ese piso
     * @return Los monumentos del piso
     */
    public List<Monumento> getListaMonumentos() {
        return listaMonumentos;
    }

    /**
     * Setter para establecer la cantidad de salones del piso
     * @param listaMonumentos Los monumentos que esten en ese piso
     */
    public void setListaMonumentos(List<Monumento> listaMonumentos) {
        this.listaMonumentos = listaMonumentos;
    }

    /**
     * Setter para asignar un monumento en particular al piso
     * @param monumento Monumento nuevo que contendra el piso
     */
    public void AgregarMonumento(Monumento monumento)
    {
        this.listaMonumentos.add(monumento);
    }

    /**
     * Getter para obtener todos los auditorios de ese piso
     * @return Los auditorios del piso
     */
    public List<Auditorio> getListaAuditorios() {
        return listaAuditorios;
    }

    /**
     * Setter para establecer la cantidad de auditorios del piso
     * @param listaAuditorios Los auditorios que esten en ese piso
     */
    public void setListaAuditorios(List<Auditorio> listaAuditorios) {
        this.listaAuditorios = listaAuditorios;
    }

    /**
     * Setter para asignar un auditorio en particular al piso
     * @param auditorio auditorio nuevo que contendra el piso
     */
    public void AgregarAuditorio(Auditorio auditorio)
    {
        this.listaAuditorios.add(auditorio);
    }

    /**
     * Getter para obtener todos los locales de ese piso
     * @return Los locales del piso
     */
    public List<Local> getListaLocal() {
        return listaLocal;
    }

    /**
     * Setter para establecer la cantidad de locales del piso
     * @param listaLocal Los locales que esten en ese piso
     */
    public void setListaLocal(List<Local> listaLocal) {
        this.listaLocal = listaLocal;
    }

    /**
     * Setter para asignar un auditorio en particular al piso
     * @param local auditorio nuevo que contendra el piso
     */
    public void AgregarLocal(Local local)
    {
        this.listaLocal.add(local);
    }

    /**
     * Getter para obtener todos los bancos de ese piso
     * @return Los bancos del piso
     */
    public List<Banco> getListaBanco() {
        return listaBanco;
    }

    /**
     * Setter para establecer la cantidad de bancos del piso
     * @param listaBanco Los bancos que esten en ese piso
     */
    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }

    /**
     * Setter para asignar un auditorio en particular al piso
     * @param banco auditorio nuevo que contendra el piso
     */
    public void AgregarBanco(Banco banco)
    {
        this.listaBanco.add(banco);
    }

    /**
     * Getter para obtener todas las facultades de ese piso
     * @return Las facultades del piso
     */
    public List<Facultad> getListaFacultad() {
        return listaFacultad;
    }

    /**
     * Setter para asignar una facultad en particular al piso
     * @param listaFacultad Facultades nuevas que contendra el piso
     */
    public void setListaFacultad(List<Facultad> listaFacultad) {
        this.listaFacultad = listaFacultad;
    }

    /**
     * Setter para asignar un auditorio en particular al piso
     * @param facultad facultad nueva que contendra el piso
     */
    public void AgregarFacultad(Facultad facultad)
    {
        this.listaFacultad.add(facultad);
    }

    /**
     * Getter para obtener todas las dependencias de ese piso
     * @return Las dependencias del piso
     */
    public List<Dependencia> getListaDependencia() {
        return listaDependencia;
    }

    /**
     * Setter para asignar una dependencia en particular al piso
     * @param listaDependencia Dependencias nuevas que contendra el piso
     */
    public void setListaDependencia(List<Dependencia> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    /**
     * Setter para asignar un auditorio en particular al piso
     * @param dependencia dependencia nueva que contendra el piso
     */
    public void AgregarDependencia(Dependencia dependencia)
    {
        this.listaDependencia.add(dependencia);
    }
}
