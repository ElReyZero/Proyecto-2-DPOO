package funcionalidades;

import java.util.ArrayList;
import IdentificadorUsuario.Estudiante;
import curriculo.Materia;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;

public class candidaturaGrado {  

    public static void darCandidaturaGrado(Estudiante estudiante, Pensum pensum)
    {
        int creditosPensum = pensum.darCreditosPensum();
        ArrayList<MateriaEstudiante> listacursos = estudiante.darCursosTomados();
        ArrayList<String> tomadosString = estudiante.darCursosTomadosString();
        String faltantes = "";
        int creditosVistos = 0;
        int cantidadCBPC = 0;
        int cantidadCBCO = 0;
        int cantidadCBCA = 0;
        int cantidadCBU = 0;
        int cantidadElectIng = 0;
        int cantidadTipoE = 0;
        int cantidadTipoEpsilon = 0;
        int cle = 0;
        boolean posible = true;
        for (MateriaEstudiante materia : listacursos) 
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
        }
        for (Materia matGeneral : pensum.darMateriasPensum()) 
        {
            String code = matGeneral.darCodigo();
            if(!tomadosString.contains(code))
            {
                if(!code.contains("CB") && !code.equals("") && !code.equals("ISIS-4XXX") && !code.equals("ISIS-3XXX"))
                {
                    faltantes += code+"\n";
                }
            }
        }

        System.out.println("Materias Vistas/Requisitos Cumplidos:\n");
        for (String codigoMat : tomadosString) 
        {
            System.out.println(codigoMat);
        }

        if(!faltantes.equals(""))
        {
            posible = false;
            System.out.println("Para poder graduarse "+estudiante.darNombre()+" necesita cumplir con los siguientes cursos/requisitos: ");
        }
        if(creditosVistos<creditosPensum)
        {
            posible = false;
            System.out.println("No se han cursado suficientes créditos para poder ser candidato a grado.");
            System.out.println("Créditos vistos: " + creditosVistos);
            System.out.print("Créditos faltantes: "+(creditosPensum-creditosVistos+"\n"));
        }
        if(cantidadCBU < 6)
        {
            posible = false;
            System.out.println("No se han cursado suficientes CBUs para ser candidato a grado. Son necesarias mínimo 6.");
            System.out.println("Cantidad de CBU vistas:" + cantidadCBU);
        }
        if(cantidadCBCA <1 || cantidadCBCO<1 || cantidadCBPC<1)
        {
            posible = false;
            System.out.println("No se han cursado suficientes CBUs de cada tipo.\nMínimo 1 CBCA, 1 CBPC, 1 CBCO");
            System.out.println("Cantidad de CBCA vistas: "+cantidadCBCA);
            System.out.println("Cantidad de CBPC vistas: "+cantidadCBPC);
            System.out.println("Cantidad de CBCO vistas: "+cantidadCBCO);
        }
        if(cantidadElectIng<1)
        {
            posible = false;
            System.out.println("No se han cursado suficientes electivas en ingeniería. Mínimo 1.");
        }
        if(cantidadTipoE < 2)
        {
            posible = false;
            System.out.println("No se han cursado suficientes cursos Tipo E. Mínimo 2");
        }
        if (cantidadTipoEpsilon < 1)
        {
            posible = false;
            System.out.println("No se han cursado suficientes cursos tipo épsilon. Mínimo 1.");
        }
        if(cle <6)
        {
            posible = false;
            System.out.println("No han sido cursados suficientes créditos en cursos de libre elección. Mínimo 6 créditos.\nHan sido cursado solamente: "+cle);
        }
        if(!faltantes.equals(""))
        {
            posible = false;
            System.out.println(faltantes);
        }
        if(posible == true)
        {
            System.out.println(estudiante.darNombre() + " ha cumplido con todos los requisitos y puede ser candidato a grado.");
        }
    }
}
