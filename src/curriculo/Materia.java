package curriculo;
public class Materia
 {
    protected String nombre;
    protected String preRequisitos;
    protected String requisitos;
    protected int creditos;
    protected String tipoMateria;
    protected boolean semanas16;
    protected String codigo;
    protected int nivel;
    protected int semestreSugerido;

    public Materia(String pnombre,String pcodigo, String ppreRequisitos, String prequisitos, int pcreditos, String ptipoMateria, int pNivel, boolean psemanas16, int pSemestreSugerido)

    {
        nombre = pnombre;
        codigo = pcodigo;
        preRequisitos = ppreRequisitos;
        requisitos = prequisitos;
        creditos = pcreditos;
        tipoMateria = ptipoMateria;
        semanas16 = psemanas16;
        nivel = pNivel;
        semestreSugerido = pSemestreSugerido;
    }

public String darNombre()
{
    return nombre;
}
public String darCodigo()
{
    return codigo;
}
public String darPreRequisitos()
{
    return preRequisitos;
}
public String darRequisitos()
{
   return requisitos; 
}
public int darCreditos()
{
    return creditos;
}
public String darTipoMateria()
{
    return tipoMateria;
}
public boolean darSemanas16()
{
    return semanas16;
}

public int darNivel()
{
    return nivel;
}

public int darSemestreSugerido()
{
    return semestreSugerido;
}

public void setType(String pTipo)
{
    tipoMateria = pTipo;
}

}
