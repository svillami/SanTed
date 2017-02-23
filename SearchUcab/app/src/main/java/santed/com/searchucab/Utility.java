package santed.com.searchucab;

/**
 * Created by Teddy J Sears on 01/01/2017.
 * Clase donde se tendran todas las variables estaticas que se necesiten para en cualquier momento
 */
public class Utility
{
    public static final int CONEXION_TIMEOUT = 10000;

    public static final int LECTURA_TIMEOUT = 15000;

    public static final String METODO_POST = "POST";

    public static final String BUSCADOR_ESCRITO = "http://santedsearch.000webhostapp.com/Buscadorprestamos.php";

    @Deprecated
    public static final String BUSCADOR_LISTA = "https://santedsearch.000webhostapp.com/pruebaphp.php";

    public static final String WEBSERVICE_BANCO = "https://santedsearch.000webhostapp.com/Bancos.php";

    public static final String WEBSERVICE_FACULTAD = "https://santedsearch.000webhostapp.com/Facultades.php";

    public static final String WEBSERVICE_ESCUELAS = "https://santedsearch.000webhostapp.com/Escuelas.php";

    public static final String WEBSERVICE_LOCALES = "https://santedsearch.000webhostapp.com/Locales.php";

    public static final String WEBSERVICE_LABORATORIOS = "https://santedsearch.000webhostapp.com/Laboratorios.php";

    public static final String WEBSERVICE_SALUD = "https://santedsearch.000webhostapp.com/Salud.php";

    public static final String WEBSERVICE_DEPORTES = "https://santedsearch.000webhostapp.com/Deportes.php";

    public static final String WEBSERVICE_ADMINISTRATIVOS = "https://santedsearch.000webhostapp.com/Administrativos.php" ;

    public static final String WEBSERVICE_CLIENTES = "https://santedsearch.000webhostapp.com/Clientes.php" ;

    public static final String WEBSERVICE_AREAS = "https://santedsearch.000webhostapp.com/Areas.php";

    /**
     * Metodo para obtener la URL para el webservice dependiendo de que tipo de lugar estamos buscando
     * @param opcion La opcion para saber a cual de todos los lugares nos referimos para buscar
     * @return
     */
    public static final String getWebservice(int opcion)
    {
        String URL = "";

        switch (opcion)
        {
            case 1:

                URL = "https://santedsearch.000webhostapp.com/AAuditorio.php";
                break;

            case 2:

                URL = "https://santedsearch.000webhostapp.com/ABanco.php";
                break;

            case 3:

                URL = "https://santedsearch.000webhostapp.com/ADependencia.php";
                break;

            case 4:

                URL = "https://santedsearch.000webhostapp.com/ADeporte.php";
                break;

            case 5:

                URL = "https://santedsearch.000webhostapp.com/AEscuela.php";
                break;

            case 6:

                URL = "https://santedsearch.000webhostapp.com/AFacultad.php";
                break;

            case 7:

                URL = "https://santedsearch.000webhostapp.com/ALaboratorio.php";
                break;

            case 8:

                URL = "https://santedsearch.000webhostapp.com/ALocal.php";
                break;

            case 9:

                URL = "https://santedsearch.000webhostapp.com/AMonumento.php";
                break;

            case 10:

                URL = "https://santedsearch.000webhostapp.com/ASalud.php";
                break;

        }

        return URL;
    }
}
