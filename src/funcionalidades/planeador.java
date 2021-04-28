package funcionalidades;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import IdentificadorUsuario.Estudiante;
import Sistema.analizadorArchivo;
import curriculo.Pensum;

public class planeador {

    private static int error;

    //Métodos

    public static String crearPlaneacion(Estudiante estudiante,Pensum pensum,Scanner sn,String codigoMateria,int semestre,String nota, boolean tipoE, boolean epsilon, boolean cle, int credsCle)
    {
        Estudiante copia;
        try {
            copia = estudiante.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            copia = null;
        }
        String plan = "";
        if (copia == null)
        {
            System.out.println("Hubo un error en la copia del estudiante.");
            System.exit(1);
        }
        int registro = copia.registrarMaterias(codigoMateria, semestre, nota, tipoE, epsilon, pensum, cle, credsCle);  
        if(registro==0)
        {
            plan += codigoMateria+"      "+String.valueOf(semestre)+"\n";
        }
        else
        {
            error = registro;
        }
        return plan;
    }

    public static void guardarPlaneación(String plan, analizadorArchivo analizador, Estudiante estudiante, File archivo) throws FileNotFoundException, UnsupportedEncodingException
    {
        analizador.guardarPlaneación(archivo, plan, estudiante);
    }

    public static int darError()
    {
        return error;
    }

}
