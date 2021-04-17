package funcionalidades;
import java.util.ArrayList;
import IdentificadorUsuario.Estudiante;
import curriculo.MateriaEstudiante;

public class reporteNotas 
{
    public static String darReporteNotas(Estudiante estudiante)
    {
        String Reporte;
        int semestreActual=0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() >semestreActual)
            {
                semestreActual = curso.darSemestre();
            }
        }
        String reportePorSemestre="";
        for (int i = 1;i<=semestreActual;i++)
        {
            String materiasSemestre="";
            for (String materia:materiasVistasSemestre(estudiante, i))
            {
                materiasSemestre = materiasSemestre+"\n"+materia;
            }
            if(i == semestreActual)
            {
                reportePorSemestre+="\nSemestre actual ("+String.valueOf(i)+"):\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\n"+ materiasSemestre;
            }
            else
            {
            reportePorSemestre += "\nSemestre "+String.valueOf(i)+":\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\nLista de materias:\n"+materiasSemestre+"\n";
            }
        }
        Reporte = "El PGA es:"+promedioPGA(estudiante)+"\nEl estado académico de "+ estudiante.darNombre() + " es: " + estadoAcademico(estudiante)+"\nEl semestre según creditos es: "+semestreSegunCreditos(estudiante)+reportePorSemestre;
        return Reporte;
    }
    public static String promedioSemestre(Estudiante estudiante, int semestre)
    {
        double promedio = 0;
        int contador = 0;
        double nota = 0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() == semestre)
            {
                try
                {
                    nota += Double.parseDouble(curso.darNota());
                    contador +=1;
                }
                catch(NumberFormatException e)
                {
                }     
            }
        }
        promedio = nota/contador;

        return String.valueOf(promedio);

    }
    public static String promedioPGA(Estudiante estudiante)
    {
        double pga;
        int total =0;
        double nota = 0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            try
            {
                nota +=Double.parseDouble(curso.darNota())*curso.darCreditos();
                total+=curso.darCreditos();
            }
            catch(NumberFormatException e)
            {}
            
        }
        pga =nota/total;
        return String.valueOf(pga);
    }
    public static ArrayList<String> materiasVistasSemestre(Estudiante estudiante, int semestre)
    {
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() == semestre)
            {
                lista.add(curso.darCodigo());
            }
        }
        return lista;
    }
    public static String estadoAcademico(Estudiante estudiante)
    {
        if(Double.parseDouble(promedioPGA(estudiante))>3.25)
        {
            return "Normal";
        }
        else
        {
            return "En prueba";
        }
    }
    public static String semestreSegunCreditos(Estudiante estudiante)
    {
        int creditos = 0;
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            creditos += curso.darCreditos();
        }
        return String.valueOf(creditos/17);
    }
}

