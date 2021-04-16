package curriculo;

public class MateriaEstudiante extends Materia 
{
	//Atributos
	private String nota;
	private int semestre;

	public MateriaEstudiante(Materia materia, String pNota, int pSemestre) 
	{
		super(materia.darNombre(), materia.darCodigo(), materia.darPreRequisitos(), materia.darRequisitos(), materia.darCreditos(), materia.darTipoMateria(), materia.darNivel(), materia.darSemanas16(), materia.darSemestreSugerido());
		nota =  pNota;
		semestre = pSemestre;
	}
	
	public String darNota()
	{
		return nota;
	}
	public int darSemestre()
	{
		return semestre;
	}
	public void setCredits(int credits)
	{
		super.creditos = credits;
	}
}
