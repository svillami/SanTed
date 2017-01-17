package santed.com.searchucab;

/**
 * Created by Teddy J Sears on 16/01/2017.
 * Clase para crear familias de clases y representacion en objetos de las entidades en DB
 */
public abstract class Entidad
{
    //Atributos de la clase
    private int Id;

    /**
     * Constructor vacio de la entidad
     */
    public Entidad ()
    {
        this.Id = 0;
    }

    /**
     * Constructor de la entidad que ya contiene el id de BD
     * @param id El identificador de la Base de Datos
     */
    public Entidad (int id)
    {
        this.Id = id;
    }

    /**
     * Setter para el Id
     * @return El id de la entidad proveniente de la BD
     */
    public int getId() {
        return Id;
    }

    /**
     * Setter para el Id
     * @param id El ID de la BD que queremos settear
     */
    public void setId(int id) {
        Id = id;
    }
}