package Sistema;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class analizadorArchivo {

    private Pensum pensum;

    public analizadorArchivo()
    {
        pensum = null;
    }
    
    public void cargarPensum(File archivo)
    {
        try
			{
                ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
				String materiasString = "";
				ArrayList<String> nivel1 = new ArrayList<String>();
				ArrayList<String> nivel2 = new ArrayList<String>();
                int totalcred = 0;
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				br.readLine();
                String linea = br.readLine();
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String nombre = partes[0];
					String codigo = partes[1];
                    String prerrequisitos = partes[2];
                    String correquisitos = partes[3];
                    int creditos = Integer.parseInt(partes[4]);
                    totalcred += creditos;
                    int nivel = Integer.parseInt(partes[5]);
                    String tipoMateria = partes[6];
                    boolean semanas = Boolean.parseBoolean(partes[7]);
					int semestreSugerido = Integer.parseInt(partes[8]);
                    Materia currentSubject = new Materia(nombre, codigo, prerrequisitos, correquisitos, creditos, tipoMateria, nivel, semanas, semestreSugerido);
                    listaMaterias.add(currentSubject);
					linea = br.readLine();
					materiasString += codigo+";";
					if(currentSubject.darNivel() == 1) 
					{
						nivel1.add(codigo);
					}
					else if(currentSubject.darNivel() == 2)
					{
						nivel2.add(codigo);
					} 
				}
				br.close();
                pensum = new Pensum(totalcred, archivo.getName(), listaMaterias, materiasString, nivel1, nivel2);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número no se pudo convertir a int ...");
				e.printStackTrace();
			}
    }

	public void guardarAvanceEstudianteArchivo(File archivo, String nombre, String codigo, String carrera, ArrayList<MateriaEstudiante> materias) throws FileNotFoundException, UnsupportedEncodingException
	{
		OutputStream os = new FileOutputStream(archivo);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		pw.println(nombre);
		pw.println(codigo);
		pw.println(carrera);
		
		for (MateriaEstudiante materia : materias)
		{	

			String nota = materia.darNota();
			String curso = materia.darCodigo();
			int creditos = materia.darCreditos();
			int numSemestre = materia.darSemestre();
			pw.println(curso + ";" + nota + ";" + creditos + ";" + numSemestre);			
		}
		pw.close();
	}

	public void guardarPlaneación(File archivo, String plan, Estudiante estudiante) throws FileNotFoundException, UnsupportedEncodingException
	{
		String nombre = estudiante.darNombre();
		String codigo = estudiante.darCodigo();
		String carrera = estudiante.darCarrera();
		OutputStream os = new FileOutputStream(archivo);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		pw.println(nombre);
		pw.println(codigo);
		pw.println(carrera);
		String [] planes = plan.split("\n");
		
		for (String materia : planes)
		{	materia = materia.replace("      ", ";");
			pw.println(materia);			
		}
		pw.close();
	}

	public void cargarAvanceEstudiante(File archivo, Estudiante estudiante, Scanner sn)
	{
		try
			{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				br.readLine();
				br.readLine();
				br.readLine();
                String linea = br.readLine();
				int caso = 0;
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String codigo = partes[0];
					String nota = partes[1];
                    int semestre = Integer.parseInt(partes[3]);
                    caso = estudiante.registrarMaterias(codigo, semestre, nota, pensum, sn);
					linea = br.readLine();
				}
				if(caso == 0)
				{
					System.out.println("Materias cargadas satisfactoriamente.");
				}
				System.out.println();
				br.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número no se pudo convertir a int ...");
				e.printStackTrace();
			}
	}

	public void cargarAvanceCoordinador(File archivo, CoordinadorAcademico coordinador, Scanner sn)
	{
		try
			{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String nombre = br.readLine();
				String codigoEst = br.readLine();
				String carrera = br.readLine();
				Estudiante estudiante = new Estudiante(nombre.split(";")[0], codigoEst.split(";")[0], carrera.split(";")[0]);
				coordinador.agregarEstudiante(estudiante);
                String linea = br.readLine();
				int caso = 0;
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String codigo = partes[0];
					String nota = partes[1];
                    int semestre = Integer.parseInt(partes[3]);
                    caso = estudiante.registrarMaterias(codigo, semestre, nota, pensum, sn);
					linea = br.readLine();
				}
				if(caso == 0)
				{
					System.out.println("Materias cargadas satisfactoriamente.");
				}
				System.out.println();
				br.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número no se pudo convertir a int ...");
				e.printStackTrace();
			}
	}

    public Pensum darPensum()
    {
        return pensum;
    }
}
