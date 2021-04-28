package funcionalidades;

import java.util.ArrayList;
import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class candidaturaGrado {  

    private static String errores;
    private static String faltantes;
    private static String vistas;
    public static void darCandidaturaGrado(Estudiante estudiante, Pensum pensum)
    {
        int creditosPensum = pensum.darCreditosPensum();
        ArrayList<MateriaEstudiante> listacursos = estudiante.darCursosTomados();
        ArrayList<String> tomadosString = estudiante.darCursosTomadosString();
        faltantes = "Materias Faltantes para grado:                                                 \n";
        vistas = "Materias Vistas/Requisitos Cumplidos:                                      \n";
        int creditosVistos = 0;
        int cantidadCBPC = 0;
        int cantidadCBCO = 0;
        int cantidadCBCA = 0;
        int cantidadCBU = 0;
        int cantidadElectIng = 0;
        int cantidadTipoE = 0;
        int cantidadTipoEpsilon = 0;
        int cle = 0;
        int electivasProfesionales = 0;
        boolean posible = true;
        errores = "";
        for (MateriaEstudiante materia : listacursos) 
        {
            if(materia.darInfoRetiro() == false)
            {
                creditosVistos += materia.darCreditos();
                if(materia.darTipoMateria().contains("Electiva CBU"))
                {
                    cantidadCBU +=1;
                    if(materia.darTipoMateria().contains("Electiva CBU CO"))
                    {
                        cantidadCBCO += 1;
                    }
                    else if(materia.darTipoMateria().contains("Electiva CBU CA"))
                    {
                        cantidadCBCA += 1;
                    }
                    else if(materia.darTipoMateria().contains("Electiva CBU PC"))
                    {
                        cantidadCBPC +=1;
                    }
                    if(materia.darTipoMateria().contains("Tipo E"))
                    {
                        cantidadTipoE += 1;
                    }
                    if(materia.darTipoMateria().contains("Épsilon"))
                    {
                        cantidadTipoEpsilon += 1;
                    }
                    
                }
                else if(materia.darTipoMateria().equals("Electiva Ingeniería"))
                {
                    cantidadElectIng +=1;
                }
                if(materia.darTipoMateria().contains("Tipo E"))
                {
                    cantidadTipoE += 1;
                }
                if(materia.darTipoMateria().contains("Tipo Épsilon"))
                {
                    cantidadTipoEpsilon += 1;
                }
                else if(materia.darTipoMateria().contains("Curso de Libre Elección"))
                {
                    cle += materia.darCreditos();
                }
                else if(materia.darTipoMateria().contains("Electiva Profesional"))
                {
                    electivasProfesionales += materia.darCreditos();
                }
            }
        }
        int cont = 0;
        for (Materia matGeneral : pensum.darMateriasPensum()) 
        {
            String code = matGeneral.darCodigo();
            if(!tomadosString.contains(code))
            {
                if(!code.contains("CB") && !code.equals("") && !code.equals("ISIS-4XXX") && !code.equals("ISIS-3XXX"))
                {
                    faltantes += code+"\n";
                    cont +=1;
                }
            }
        }
        if(cont !=0)
        {
            faltantes += "Ninguno";
        }

        for (String codigoMat : tomadosString) 
        {
            vistas += codigoMat+"\n";
        }

        if(creditosVistos<creditosPensum)
        {
            posible = false;
            errores += "No se han cursado suficientes créditos para poder ser candidato a grado.\n";
            errores += "Créditos vistos: " + creditosVistos+"\n";
            errores += "Créditos faltantes: "+(creditosPensum-creditosVistos+"\n");
        }
        if(cantidadCBU < 6)
        {
            posible = false;
            errores += "No se han cursado suficientes CBUs para ser candidato a grado. Son necesarias mínimo 6.\n";
            errores += "Cantidad de CBU vistas:" + cantidadCBU+"\n";
        }
        if(cantidadCBCA <1 || cantidadCBCO<1 || cantidadCBPC<1)
        {
            posible = false;
            errores += "No se han cursado suficientes CBUs de cada tipo.\nMínimo 1 CBCA, 1 CBPC, 1 CBCO\n";
            errores += "Cantidad de CBCA vistas: "+cantidadCBCA+"\n";
            errores += "Cantidad de CBPC vistas: "+cantidadCBPC+"\n";
            errores += "Cantidad de CBCO vistas: "+cantidadCBCO + "\n";
        }
        if(cantidadElectIng<1)
        {
            posible = false;
            errores += "No se han cursado suficientes electivas en ingeniería. Mínimo 1.\n";
        }

        if(electivasProfesionales < 9)
        {
            posible = false;
            errores += "No se han cursado suficientes créditos de electivas profesionales. Mínimo 9.\n";
        }

        if(cantidadTipoE < 2)
        {
            posible = false;
            errores += "No se han cursado suficientes cursos Tipo E. Mínimo 2\n";
        }
        if (cantidadTipoEpsilon < 1)
        {
            posible = false;
            errores += "No se han cursado suficientes cursos tipo épsilon. Mínimo 1.\n";
        }
        if(cle <6)
        {
            posible = false;
            errores += "No han sido cursados suficientes créditos en cursos de libre elección. Mínimo 6 créditos.\nHan sido cursado solamente: "+cle+"\n";
        }
        if(posible == true)
        {
            errores = "Ninguno";
            errores += estudiante.darNombre() + " ha cumplido con todos los requisitos y puede ser candidato a grado.\n";
        }
    }

    public static String darError()
    {
        return errores;
    }

    public static String darFaltantes()
    {
        return faltantes;
    }

    public static String darVistas()
    {
        return vistas;
    }
}
