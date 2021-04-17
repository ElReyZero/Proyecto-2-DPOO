package funcionalidades;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import IdentificadorUsuario.Estudiante;
import Sistema.analizadorArchivo;
import curriculo.Pensum;

public class planeador {

    //Métodos

    public static String crearPlaneacion(Estudiante estudiante,Pensum pensum,Scanner sn,String codigoMateria,int semestre,String nota, boolean tipoE, boolean epsilon)
    {
        Estudiante copia = estudiante;
        String plan = "";
        int registro=copia.registrarMaterias(codigoMateria, semestre, nota, tipoE, epsilon, pensum, sn);  
        if(registro==0)
        {
            plan += codigoMateria+"      "+String.valueOf(semestre)+"\n";
        }
        return plan;
    }

    public static void guardarPlaneación(String plan, analizadorArchivo analizador, Estudiante estudiante, File archivo) throws FileNotFoundException, UnsupportedEncodingException
    {
        analizador.guardarPlaneación(archivo, plan, estudiante);
    }

}
