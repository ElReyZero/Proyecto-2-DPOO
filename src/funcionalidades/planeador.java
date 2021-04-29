package funcionalidades;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
import curriculo.Pensum;

public class planeador {

    private static int error;
    private static Estudiante copia;

    //Métodos
    public static String crearPlaneacion(Estudiante pCopia,Pensum pensum,String codigoMateria,int semestre,String nota, boolean tipoE, boolean epsilon, boolean cle, int credsCle)
    {
        String plan = "";
        error = 0;
        copia = pCopia;
        int registro = copia.registrarMaterias(codigoMateria, semestre, nota, tipoE, epsilon, pensum, cle, credsCle);  
        if(registro==0)
        {
            error = 0;
            plan += codigoMateria+"      "+String.valueOf(semestre)+"\n";
        }
        else
        {
            error = registro;
        }
        return plan;
    }

    public static void guardarPlaneación(String plan, systemMain sistema, Estudiante estudiante, File archivo) throws FileNotFoundException, UnsupportedEncodingException
    {
        sistema.guardarPlan(archivo, plan, estudiante);
    }

    public static int darError()
    {
        return error;
    }


    public static Estudiante darCopia()
    {
        return copia;
    }

}
