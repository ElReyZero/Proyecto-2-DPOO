package IdentificadorUsuario;

import java.util.ArrayList;

public class CoordinadorAcademico extends Usuario {
	
	//Atributos
	private String departamento;
	private ArrayList<Estudiante> estudiantes;
	private String codEstudianteRec;
	private String nomEstRec;
	
	//Constructor
	public CoordinadorAcademico(String pNombre, String pCodigo, String pDepto) 
	{
		super(pNombre, pCodigo);
		departamento = pDepto;
		estudiantes = new ArrayList<>();
		codEstudianteRec = "";
		nomEstRec = "";
	}

	//Métodos
	
	public String darDepto()
	{
		return departamento;
	}

	public Estudiante darEstudiante(String codigoEstudianteRevisar) 
	{
		
		for (Estudiante estudiante : estudiantes) 
		{
			if(estudiante.darCodigo().contains(codigoEstudianteRevisar))
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
	public String darListaEstudiantes()
	{
		String sumando = "";
		for(Estudiante est: estudiantes)
		{
			sumando += "Nombre: " + est.darNombre() + " - Código: " + est.darCodigo()+";";
		}
		return sumando;
	}

	public void codEstReciente(String codigoEst) 
	{
		codEstudianteRec = codigoEst;
	}

	public String darCodEstReciente()
	{
		return codEstudianteRec;
	}

	public void nomEstReciente(String string) 
	{
		nomEstRec = string;
	}

	public String darNomEstReciente()
	{
		return nomEstRec;
	}

}
