package IdentificadorUsuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import Sistema.analizadorArchivo;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class Estudiante extends Usuario {

	//Atributos
	private String carrera;
	private ArrayList<MateriaEstudiante> cursosTomados;
	private ArrayList<String> cursosTomadosArrayString;
	private String tomadosString;
	
	//Constructor
	public Estudiante(String pNombre, String pCodigo, String pCarrera) 
	{
		super(pNombre, pCodigo);
		carrera = pCarrera;
		cursosTomados = new ArrayList<MateriaEstudiante>();
		cursosTomadosArrayString = new ArrayList<String>();
		tomadosString = "-----------------------------------\n";
	}

	//Métodos
	public int registrarMaterias(String codigo, int semestre, String nota, Pensum pensum, Scanner sn)
	{
		if (codigo.length() != 9 || !codigo.contains("-"))
		{
			System.out.println("El código de materia "+codigo+" no está escrito en un formato adecuado. Formato: AAAA-XXXX");
			return 1;
		}
		var listaMaterias = pensum.darMateriasPensum();
		String matString = pensum.darMateriasString();
		if(!tomadosString.contains(codigo))
		{	if(matString.contains(codigo))
			{
				for(Materia current:listaMaterias)
				{
					if (current.darCodigo().contains(codigo) && current.darNivel() >=3)
					{
						for(int i = 0; pensum.darMateriasNivel1String().size()>i; i++)
						{
							if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel1String().get(i)))
							{
								System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 1");
								return 1;
							}
						}	
					}
					if ((current.darCodigo().contains(codigo) && (current.darNivel() == 4| current.darCodigo().equals("ISIS-3007"))))
					{
						for(int i = 0; pensum.darMateriasNivel2String().size()>i; i++)
						{
							if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel2String().get(i)))
							{
								System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 2");
								return 1;
							}
						}
					}
					if(current.darCodigo().contains(codigo) && current.darPreRequisitos().equals("N/A") && current.darRequisitos().equals("N/A"))
					{
						MateriaEstudiante agregada = revisarAprobado(current, nota, semestre);
						cursosTomados.add(agregada);
						tomadosString += current.darCodigo()+"\n";
						cursosTomadosArrayString.add(current.darCodigo());
						return 0;
						
					}
					else if(current.darCodigo().contains(codigo))
					{
						ArrayList<String> prerrequisitos = new ArrayList<String> (Arrays.asList(current.darPreRequisitos().split("&")));
						ArrayList<String> correquisitos = new ArrayList<String> (Arrays.asList(current.darRequisitos().split("&")));
						if(!prerrequisitos.get(0).equals("N/A"))
						{
							for(Materia tomada:cursosTomados)
							{

								for(int i = 0; prerrequisitos.size() > i; i++)
								{
									if(prerrequisitos.get(i).contains(tomada.darCodigo()))
									{
										prerrequisitos.remove(i);
									}
								}
							}	
							if (prerrequisitos.size()!= 0)
							{
								System.out.println("Se está intentando registrar "+ codigo +" sin haber cumplido todos los prerrequisitos previamente.\nPrerrequisito(s) sin cumplir:\n" + String.join("\n", prerrequisitos));
								return 1;
							}

						}
						else 
						{
							prerrequisitos.remove(0);
						}
						if(!correquisitos.get(0).equals("N/A"))
						{
							{
								for(Materia tomada:cursosTomados)
								{
									for(int i = 0; correquisitos.size() > i; i++)
									{
										if(correquisitos.get(i).contains(tomada.darCodigo()))
										{
											correquisitos.remove(i);
										}
									}
							}
								if (correquisitos.size()!= 0)
								{
									System.out.println(tomadosString);
									System.out.println("Se está intentando registrar "+ codigo +" sin haber inscrito todos los correquisitos previamente.\nCorrequisitos(s) sin inscribir:\n" + String.join("\n", correquisitos));
									return 1;
								}
							}
						}
						else
						{
							correquisitos.remove(0);
						}
						if(correquisitos.size() == 0 && prerrequisitos.size() == 0)
						{
							MateriaEstudiante agregada = revisarAprobado(current, nota, semestre);
							cursosTomados.add(agregada);
							tomadosString += current.darCodigo()+"\n";
							cursosTomadosArrayString.add(current.darCodigo());
							return 0;
						}
					}
				}
			}
		else if(codigo.contains("CB"))
		{
			System.out.println("¿El CBU "+ codigo +" es de Tipo E?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			String seleccion = sn.next();
			if (seleccion.equals("1"))
			{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2,"Electiva CBU " +codigo.charAt(2)+codigo.charAt(3)+" - Tipo E", 0, true, semestre);
				MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
				cursosTomados.add(agregada);
				tomadosString += nuevaMateria.darCodigo()+"\n";
				cursosTomadosArrayString.add(nuevaMateria.darCodigo());
				return 0;
			}
			else if (seleccion.equals("2"))
			{
				System.out.println("¿El CBU "+ codigo +" es de tipo épsilon?");
				System.out.println("1. Sí");
            	System.out.println("2. No");
				String op = sn.next();
				if (op.equals("1"))
				{
					Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU " +codigo.charAt(2)+codigo.charAt(3)+" - Tipo Épsilon", 0, true, semestre);
					MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
					cursosTomados.add(agregada);
					tomadosString += nuevaMateria.darCodigo()+"\n";
					cursosTomadosArrayString.add(nuevaMateria.darCodigo());
					return 0;
				}
				else if(op.equals("2"))
				{
					Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva CBU", 0, true, semestre);
					MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
					cursosTomados.add(agregada);
					tomadosString += nuevaMateria.darCodigo()+"\n";
					cursosTomadosArrayString.add(nuevaMateria.darCodigo());
					return 0;
				}
				else
				{
					System.out.println("Ha introducido una respuesta inválida.");
					registrarMaterias(codigo, semestre, nota, pensum, sn);
				}
			}
			else
			{
				System.out.println("Ha introducido una respuesta inválida.");
				registrarMaterias(codigo, semestre, nota, pensum, sn);
			}
		}
		else if (codigo.equals("BIOL 3300")|codigo.equals("FISI 1038")|codigo.equals("FISI 1048")|codigo.equals("MATE 1107")|codigo.equals("MATE 2211")|codigo.equals("MATE 2301")|codigo.equals("MATE 2303")|codigo.equals("MATE 2411")|codigo.equals("MATE 3712")|codigo.equals("MBIO 2102")|codigo.equals("QUIM 1105")|codigo.equals("QUIM 1301")|codigo.equals("QUIM 1303")|codigo.equals("QUIM 1510")|codigo.equals("QUIM 2620"))
		{
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva en Ciencias", 0, true, semestre);
			MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
			cursosTomados.add(agregada);
			tomadosString += nuevaMateria.darCodigo()+"\n";
			cursosTomadosArrayString.add(nuevaMateria.darCodigo());
			return 0;
		}
		else if(codigo.equals("IBIO-2099")|codigo.equals("IBIO-2240")|codigo.equals("ICYA-1101")|codigo.equals("ICYA-1110")|codigo.equals("ICYA-1116")|codigo.equals("ICYA-1125")|codigo.equals("ICYA-2001")|codigo.equals("ICYA-2401")|codigo.equals("ICYA-2412")|codigo.equals("IELE-1002")|codigo.equals("IELE-1006")|codigo.equals("IELE-1010")|codigo.equals("IELE-1502")|codigo.equals("IELE-2010")|codigo.equals("IELE-2210")|codigo.equals("IELE-2300")|codigo.equals("IELE-2500")|codigo.equals("IIND-2103")|codigo.equals("IIND-2104")|codigo.equals("IIND-2107")|codigo.equals("IIND-2202")|codigo.equals("IIND-2301")|codigo.equals("IIND-2400")|codigo.equals("IMEC-1001")|codigo.equals("IMEC-1330")|codigo.equals("IMEC-1410")|codigo.equals("IMEC-1503")|codigo.equals("IQUI-2010")|codigo.equals("IQUI-2020")|codigo.equals("IQUI-2101")|codigo.equals("IQUI-2200"))
		{
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Ingeniería", 0, true, semestre);
				MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
				cursosTomados.add(agregada);
				tomadosString += nuevaMateria.darCodigo()+"\n";
				cursosTomadosArrayString.add(nuevaMateria.darCodigo());
				return 0;
		}
		else if(codigo.equals("IIND-4115")|codigo.equals("IIND-4123")|codigo.equals("MATE-3133")|codigo.equals("IELE-4231")|codigo.equals("FISI-3024")|codigo.equals("IELE-3338")|codigo.equals("IELE-4014")|codigo.equals("MATE-3102")|codigo.equals("MATE-4527")|codigo.equals("IBIO-3470")|codigo.equals("MATE-3134")|codigo.equals("IBIO-4680")|codigo.equals("IBIO-4490"))
		{
			for(int i = 0; pensum.darMateriasNivel1String().size()>i; i++)
				{
					if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel1String().get(i)))
					{
						System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 1");
						return 1;
					}
				}
			for(int i = 0; pensum.darMateriasNivel2String().size()>i; i++)
			{
				if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel2String().get(i)))
					{
						System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 2");
						return 1;
					}
			}
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 3, "Electiva Profesional", 4, true, semestre);
			MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
			cursosTomados.add(agregada);
			tomadosString += nuevaMateria.darCodigo()+"\n";
			cursosTomadosArrayString.add(nuevaMateria.darCodigo());
			return 0;
		}				
	else if(codigo.contains("ISIS-4"))
		{
			for(int i = 0; pensum.darMateriasNivel1String().size()>i; i++)
				{
					if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel1String().get(i)))
					{
						System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 1");
						return 1;
					}
				}
			for(int i = 0; pensum.darMateriasNivel2String().size()>i; i++)
			{
				if(!cursosTomadosArrayString.contains(pensum.darMateriasNivel2String().get(i)))
					{
						System.out.println("Para poder inscribir " + codigo + " necesitas haber inscrito todas las materias de nivel 2");
						return 1;
					}
			}
			Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", 2, "Electiva Profesional", 0, true, semestre);
			MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
			cursosTomados.add(agregada);
			tomadosString += nuevaMateria.darCodigo()+"\n";
			cursosTomadosArrayString.add(nuevaMateria.darCodigo());
			return 0;
		}
		else if (codigo.contains("-"))
		{
			System.out.println("No se encontró la materia "+ codigo+" en el pensum, ¿estás seguro de que quieres inscribrla como curso de libre elección?");
			System.out.println("1. Sí");
            System.out.println("2. No");
			int opcion = sn.nextInt();
			int creds = 0;
            switch (opcion)
            {
                case 1:
				System.out.println("¿De cuántos créditos es "+codigo+"?");
				try
				{
					creds = sn.nextInt();
				}
				catch (InputMismatchException e) 
                {
                    System.out.println("Debes insertar un número");
                    creds = sn.nextInt();
					registrarMaterias(codigo, semestre, nota, pensum, sn);
                }
				Materia nuevaMateria = new Materia(codigo, codigo, "N/A", "N/A", creds, "Curso de Libre Elección", 0, true, semestre);
				MateriaEstudiante agregada = revisarAprobado(nuevaMateria, nota, semestre);
				cursosTomados.add(agregada);
				tomadosString += nuevaMateria.darCodigo()+"\n";
				cursosTomadosArrayString.add(nuevaMateria.darCodigo());
				return 0;			
				case 2:
				return 0;
            }
			return 0;
		}	
		}
		for (MateriaEstudiante mat : cursosTomados)
		{
			if (mat.darCodigo().contains(codigo))
			{
				String grade = mat.darNota();
				try 
				{
					Double gradeNum = Double.parseDouble(grade);
					Double notaNum = 0.0;
					try 
					{
						notaNum = Double.parseDouble(nota);
					}
					catch (NumberFormatException e)
					{
					}
					if (gradeNum<3.0 && (notaNum >=3.0 || nota.equals("A")))
					{
						int creds = 0;
						for (Materia mater : pensum.darMateriasPensum())
						{
							if(mater.darCodigo().contains(mat.darCodigo()))
							{
								creds = mater.darCreditos();
								break;
							}
						}
						MateriaEstudiante agregada = revisarAprobado(mat, nota, semestre);
						agregada.setCredits(creds);
						cursosTomados.add(agregada);
						tomadosString += agregada.darCodigo()+"\n";
						cursosTomadosArrayString.add(agregada.darCodigo());
						return 0;
					}
					else
					{
						System.out.println("No se puede repetir una materia que no haya sido perdida.");
						return 1;
					}

					} 
				catch (Exception e) 
				{
					Double notaNum = 0.0;
					try 
					{
						notaNum = Double.parseDouble(nota);
					}
					catch (NumberFormatException ex)
					{
					}
					if((grade.equals("R")||grade.equals("I")) && (notaNum >=3.0 || nota.equals("A")))
					{
						int creds = 0;
						for (Materia mater : pensum.darMateriasPensum())
						{
							if(mater.darCodigo().contains(mat.darCodigo()))
							{
								creds = mater.darCreditos();
								break;
							}
						}
						MateriaEstudiante agregada = revisarAprobado(mat, nota, semestre);
						agregada.setCredits(creds);
						cursosTomados.add(agregada);
						tomadosString += agregada.darCodigo()+"\n";
						cursosTomadosArrayString.add(agregada.darCodigo());
						return 0;
					}
					else
					{
						System.out.println("No se puede repetir una materia que no haya sido perdida.");
						return 1;
					}
			}
		}
	}
		return 0;
	}


	public MateriaEstudiante revisarAprobado(Materia materia, String nota, int semestre)
	{
		///TODO
		try
		{
			Double notaNum = Double.parseDouble(nota);
			if(notaNum < 3)
			{
				MateriaEstudiante agregada = new MateriaEstudiante(materia, nota, semestre);
				agregada.setCredits(0);
				return agregada;
			}
		else
			{
				MateriaEstudiante agregada = new MateriaEstudiante(materia, nota, semestre);
				return agregada;
			}
		}
		catch (Exception e)
		{
			if(nota.equals("R"))
			{
				MateriaEstudiante agregada = new MateriaEstudiante(materia, nota, semestre);
				agregada.setCredits(0);
				return agregada;
			}
			else
			{
				MateriaEstudiante agregada = new MateriaEstudiante(materia, nota, semestre);
				return agregada;
			}
		}
		
	}
	public void guardarAvance(analizadorArchivo analizador, File archivo) throws FileNotFoundException, UnsupportedEncodingException
	{
		analizador.guardarAvanceEstudianteArchivo(archivo, nombre, codigo, carrera, cursosTomados);
		System.out.println("El archivo fue guardado en: " + archivo.getAbsolutePath());
	}

	public ArrayList<MateriaEstudiante> darCursosTomados()
	{
		return cursosTomados;
	}

	public String darCarrera()
	{
		return carrera;
	}
	public ArrayList<String> darCursosTomadosString()
	{
		return cursosTomadosArrayString;
	}

}
