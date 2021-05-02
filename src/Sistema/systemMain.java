package Sistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import curriculo.Pensum;

///Hecho por: Juan Andrés Romero C - 202013449
/// Luccas Rojas - 201923052

public class systemMain 
{
    analizadorArchivo analizador;

    public systemMain()
    {
        analizador = new analizadorArchivo();
    }

    public void cargarPensumAnalizador(File archivo)
    {
        analizador.cargarPensum(archivo);
    }

    public Pensum darPensum()
    {
        return analizador.darPensum();
    }

    public int cargarAvanceEstudiante(File archivo, Estudiante estudiante)
    {
        return analizador.cargarAvanceEstudiante(archivo, estudiante);
    }

    public int guardarAvanceEstudiante(File archivo, Estudiante estudiante) throws FileNotFoundException, UnsupportedEncodingException
    {
        return analizador.guardarAvanceEstudianteArchivo(archivo, estudiante.darNombre(), estudiante.darCodigo(), estudiante.darCarrera(), estudiante.darCursosTomados());
    }

    public int guardarPlan(File archivo, String plan, Estudiante estudiante) throws FileNotFoundException, UnsupportedEncodingException
    {
        return analizador.guardarPlaneación(archivo, plan, estudiante);
    }

    public int cargarAvanceCoordinador(File archivo, CoordinadorAcademico coordinador)
    {
        return analizador.cargarAvanceCoordinador(archivo, coordinador);
    }
}

