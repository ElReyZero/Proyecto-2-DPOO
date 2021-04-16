package IdentificadorUsuario;

import java.util.ArrayList;

import curriculo.MateriaEstudiante;

public class CoordinadorAcademico extends Usuario {
	
	//Atributos
	private String departamento;
	private static ArrayList<Estudiante> estudiantes;
	
	
	//Constructor
	public CoordinadorAcademico(String pNombre, String pCodigo, String pDepto) 
	{
		super(pNombre, pCodigo);
		departamento = pDepto;
		estudiantes = new ArrayList<>();
	}

	//Métodos
	
	public String darDepto()
	{
		return departamento;
	}

	public static Estudiante darEstudiante(String codigoEstudianteRevisar) 
	{
		
		for (Estudiante estudiante : estudiantes) 
		{
			if(estudiante.darCodigo().equals(codigoEstudianteRevisar))
			{
				return estudiante;
			}
		}
		
		return null;
	}
	public void agregarEstudiante(Estudiante estudiante)
	{
		estudiantes.add(estudiante);
	}
	public ArrayList<Estudiante> darListaEstudiantes()
	{
		return estudiantes;
	}
	public static void revisarAvance(Estudiante estudiante)
	{
		String avance="";
		String lineas="";
		ArrayList<MateriaEstudiante> cursosTomados= estudiante.darCursosTomados();
        for (MateriaEstudiante curso:cursosTomados)
        {
			String linea="";
			linea = "Materia: "+curso.darCodigo()+" Semestre: "+String.valueOf(curso.darSemestre())+" Nota: "+String.valueOf(curso.darNota())+"\n";
			lineas +=linea;
        }
		avance = "El avance del estudiante: "+estudiante.darNombre()+" es el siguiente:\n"+lineas;
		System.out.println(avance);
	}

}
