package funcionalidades;
import java.util.ArrayList;
import IdentificadorUsuario.Estudiante;
import curriculo.MateriaEstudiante;


public class reporteNotas 
{
    static String reporte;

    public static String darReporteNotas(Estudiante estudiante)
    {
        reporte = "El PGA es:"+promedioPGA(estudiante)+"\nEl estado académico de "+ estudiante.darNombre() + " es: " + estadoAcademico(estudiante)+"\nEl semestre según creditos es: "+semestreSegunCreditos(estudiante)+reporteSemestre(estudiante);
        System.out.println(reporte);
        return reporte;
    }
    public static String reporteSemestre (Estudiante estudiante)
    {
        int semestreActual=0;
        String reportePorSemestre="Reporte de notas por semestre:\n";
        ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
            if(curso.darSemestre() >semestreActual)
            {
                semestreActual = curso.darSemestre();
            }
        }
        String retiro ="Inscrita";
        for (int i = 1;i<=semestreActual;i++)
        {
            String materiasSemestre="";
            for (MateriaEstudiante materia : ordenarMaterias(estudiante, i))
            {

                if(materia.darInfoRetiro() == true)
                {
                    retiro = "Retirada";
                }
                else
                {
                    retiro = "Inscrita";
                }
                materiasSemestre = materiasSemestre+"\n"+materia.darCodigo() + "                Nota: "+materia.darNota() + "         Estado: " + retiro;
            }
            if(i == semestreActual)
            {
                reportePorSemestre+="\n*****************************************\nSemestre actual ("+String.valueOf(i)+"):\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\n"+ materiasSemestre;
            }
            else
            {
            reportePorSemestre += "\n*****************************************\nSemestre "+String.valueOf(i)+":\n"+"El promedio del semestre es: "+promedioSemestre(estudiante, i)+ "\nLista de materias:\n"+materiasSemestre+"\n";
            }
        }
        return reportePorSemestre;
    }

    public static ArrayList<MateriaEstudiante> ordenarMaterias(Estudiante estudiante, int semestre)
    {
        ArrayList<MateriaEstudiante> copy = new ArrayList<>();
        for (MateriaEstudiante mat : estudiante.darCursosTomados())
        {
            if (mat.darSemestre() == semestre)
            {
                copy.add(mat);
            }       
        }
        return copy;
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

