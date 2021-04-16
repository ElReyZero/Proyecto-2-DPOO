package curriculo;
import java.util.ArrayList;

public class Pensum {
    //Atributos

    private Integer creditos;
    private String nombre;
    private ArrayList<Materia> materias;
    private String materiasString;
    private ArrayList<String> nivel1;
    private ArrayList<String> nivel2;

    //constructor
    public Pensum(Integer pCreditos, String pNombre, ArrayList<Materia> pMaterias, String pMateriasString, ArrayList<String> pNivel1, ArrayList<String> pNivel2)
    {
        creditos = pCreditos;
        nombre = pNombre;
        materias = pMaterias;
        materiasString = pMateriasString;
        nivel1 = pNivel1;
        nivel2 = pNivel2;
    }

    //Métodos

    public Integer darCreditosPensum()
    {
        return creditos;
    }

    public String darNombrePensum()
    {
        return nombre;
    }

    public ArrayList<Materia> darMateriasPensum()
    {
        return materias;
    }
    
    public String darMateriasString()
    {
        return materiasString;
    }

    public ArrayList<String> darMateriasNivel1String()
    {
        return nivel1;
    }

    public ArrayList<String> darMateriasNivel2String()
    {
        return nivel2;
    }
}
