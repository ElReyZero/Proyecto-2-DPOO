package Sistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;
import funcionalidades.candidaturaGrado;
import funcionalidades.reporteNotas;

///Hecho por: Juan Andrés Romero C - 202013449
/// Luccas Rojas - 201923052

public class systemMain 
{
    analizadorArchivo analizador;

    public systemMain()
    {
        analizador = new analizadorArchivo();
    }

	public static void Consola(Pensum pensum, analizadorArchivo analizador)
	{

		Scanner sn = new Scanner(System.in);
        int opcion; 

			System.out.println("Bienvenido a Banner \n-----------------------");
			System.out.println("Elija su tipo de usuario");
 
            System.out.println("1. Estudiante");
            System.out.println("2. Coordinador Académico");
            System.out.println("3. Salir");
 
            try {
                System.out.println("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                    String nombreEstudiante = "";
                    String codigoEstudiante = "";
                    String carrera = "";
                    System.out.println("\nBienvenido estudiante \n-----------------------");
                    System.out.println("Digite su nombre:");
                    try
                    {
                    nombreEstudiante = sn.next();
                    } catch (InputMismatchException e) 
                    {
                    System.out.println("Debes insertar tu nombre");
                    sn.next();
                    }
                    System.out.println("Digite su codigo:");
                    try
                    {
                    codigoEstudiante = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu codigo");
                        sn.next();
                    }
                    System.out.println("Digite su carrera:");
                    try
                    {
                    carrera = sn.next();
                    } catch (InputMismatchException e) 
                    {
                    System.out.println("Debes insertar el nombre de tu carrera");
                    sn.next();
                    }
                    Estudiante estudiante = new Estudiante(nombreEstudiante,codigoEstudiante,carrera);
                    System.out.println("Bienvenido " + nombreEstudiante+ "\n-----------------------");
                    seleccionEstudiante(sn, pensum, estudiante, analizador);      
                    break;                 
                    case 2:
                    String nombreCoordinador = "";
                    String codigoCoordinador = "";
                    String departamento = "";
                    System.out.println("\nBienvenido coordinador \n-----------------------");
                    System.out.println("Digite su nombre:");
                    try
                    {
                        nombreCoordinador = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu nombre");
                        sn.next();
                    }
                    System.out.println("Digite su codigo:");
                    try
                    {
                        codigoCoordinador = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu codigo");
                        sn.next();
                    }
                    System.out.println("Digite su departamento:");
                    try
                    {
                        departamento = sn.next();
                    } catch (InputMismatchException e) 
                    {
                        System.out.println("Debes insertar tu departamento");
                        sn.next();
                    }
                    CoordinadorAcademico coordinador = new CoordinadorAcademico(nombreCoordinador,codigoCoordinador,departamento);
                    seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, null);
                    break;
                    case 3:
                        sn.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            
        }
	

    public static void seleccionEstudiante(Scanner sn, Pensum pensum, Estudiante estudiante, analizadorArchivo analizador)
    {
        System.out.println("Seleccione la opción a realizar: ");
        System.out.println("1. Registrar Materias Manualmente. | NOTA: Se puede usar esta opción para homologar materias / Inscribir una práctica académica (ISIS-3991) (Usar código de la materia y nota como A)");
        System.out.println("2. Registrar Materias desde un archivo");
        System.out.println("3. Editar información de una materia. (Cambiar nota, marcar como retirada)");
        System.out.println("4. Guardar registro de materias en un archivo");
        System.out.println("5. Generar reporte notas");
        System.out.println("6. Dar candidatura grado");
        System.out.println("7. Crear planeación");
        System.out.println("8. Validar requisitos de idiomas");
        System.out.println("9. Salir");
        String opcion = String.valueOf(sn.nextInt());
        System.out.println(opcion);
        if(opcion.equals("1"))
        {
            registrarMateriaEstudiante(sn, estudiante, pensum, analizador);  
        }
        else if(opcion.equals("2"))
        {
            System.out.println("Escribe exit para salir");
            System.out.println("Ingresa la ruta donde se encuentra el archivo: ");
            String ruta = sn.next();
            if(ruta.toLowerCase().contains("exit"))
            {
                seleccionEstudiante(sn, pensum, estudiante, analizador);
            }
            else
            {
                File avance = new File(ruta);
                analizador.cargarAvanceEstudiante(avance, estudiante);
                seleccionEstudiante(sn, pensum, estudiante, analizador);
            }   
        }
        else if(opcion.equals("3"))
        {
            editarMateriaEstudiante(estudiante, sn);
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }  
        else if(opcion.equals("4"))
        {
            File archivoMaterias = new File("./data/materias"+estudiante.darCodigo()+".csv");
            try {
               estudiante.guardarAvance(analizador, archivoMaterias);
               seleccionEstudiante(sn, pensum, estudiante, analizador);
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else if(opcion.equals("5"))
        {
            System.out.println("¿Quieres generar un reporte de notas de un semestre particular o de toda la carrera?");
            System.out.println("1. Semestre Específico");
            System.out.println("2. Toda la carrera");
            int opci = sn.nextInt();
            switch (opci)
            {
                case 1:
                System.out.println("Ingresa el semestre para generar el reporte: ");
                int semestre = sn.nextInt();
                try {
                    Estudiante copia = estudiante.clone();
                    ArrayList<MateriaEstudiante> lista = copia.darCursosTomados();
                    for (MateriaEstudiante materia : estudiante.darCursosTomados())
                    {
                        if(materia.darSemestre() != semestre) 
                        {
                            lista.remove(materia);
                        }
                    }
                    copia.setCursosTomados(lista);
                    System.out.println(reporteNotas.darReporteNotas(copia));
                    seleccionEstudiante(sn, pensum, estudiante, analizador);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                System.out.println(reporteNotas.darReporteNotas(estudiante));
                seleccionEstudiante(sn, pensum, estudiante, analizador);
                break;
            }   
        }
        else if(opcion.equals("6"))
        {
            candidaturaGrado.darCandidaturaGrado(estudiante,pensum);
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }
            
        else if(opcion.equals("7"))
        {
            registrarMateriaPlaneadorEstudiante(sn,estudiante,pensum, analizador,"");
        }
        else if(opcion.equals("8"))
        {
         validarRequisitos(sn,pensum,estudiante,analizador);   
        }
        else if(opcion.equals("9"))
        {
            sn.close();
            System.exit(0);
        }
        else
        {
            System.out.println("Debes ingresar una opción válida.");
            seleccionEstudiante(sn, pensum, estudiante, analizador);
        }        
    }
    public static void validarRequisitos(Scanner sn, Pensum pensum, Estudiante estudiante, analizadorArchivo analizador)
    {
        System.out.println("Seleccione la opción a validar: ");
        System.out.println("1. Requisito de Inglés");
        System.out.println("2. Requisito de segunda lengua");
        System.out.println("3. Volver");
        int opcion = sn.nextInt();
        switch (opcion)
        {
            case 1:
            System.out.println("¿Aprobó el examen de inglés de admisión? ");
            System.out.println("1. Sí");
            System.out.println("2. No / Volver al menú anterior");
            int opcion1 = sn.nextInt();
            switch(opcion1)
            {
                case 1:
                ///estudiante.registrarMaterias("LENG-2999", 1, "A", false, false, pensum, sn);
                seleccionEstudiante(sn, pensum, estudiante, analizador);
                break;
                case 2:
                validarRequisitos(sn, pensum, estudiante, analizador);
                break;
            }
            break;
            case 2:
            System.out.println("¿Homologó el requisito de segunda lengua con algún examen? ");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion2 = sn.nextInt();
            switch(opcion2)
            {
                case 1:
                ///estudiante.registrarMaterias("LENG-3999", 1, "A", false, false, pensum, sn);
                seleccionEstudiante(sn, pensum, estudiante, analizador);
                break;
                case 2:
                System.out.println("¿Aprobó el último nivel de inglés(10)?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                int opcion3 = sn.nextInt();
                switch(opcion3)
                {
                    case 1:
                    ///estudiante.registrarMaterias("LENG-3999", 1, "A", false, false, pensum, sn);
                    seleccionEstudiante(sn, pensum, estudiante, analizador);
                    break;
                    case 2:
                    validarRequisitos(sn, pensum, estudiante, analizador);
                    break;
                }
            }
            break;
            case 3: 
            seleccionEstudiante(sn, pensum, estudiante, analizador);
            break;
        }
    }
    public static void seleccionCoordinadorAcademico(Scanner sn, Pensum pensum, CoordinadorAcademico coordinador, analizadorArchivo analizador, File avance)
    {
        System.out.println("\nEscriba el código del estudiante que desea revisar: ");
        System.out.println("Escriba exit para volver al menú anterior.");
        String codigoEstudianteRevisar = sn.next();
        if(codigoEstudianteRevisar.toLowerCase().contains("exit"))
        {
            Consola(pensum, analizador);
        }
        Estudiante estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
        if(avance == null)
        {
            System.out.println("Ingresa la ruta donde se encuentra el archivo con la información del estudiante: ");
            avance = new File(sn.next());
            analizador.cargarAvanceCoordinador(avance, coordinador, sn);
            estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
        }
        if(estudiante == null)
        {
            System.out.println("Debes cargar la información del estudiante primero.");
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, null);
        }
        System.out.println("\nSeleccione la opción a realizar: ");
        System.out.println("1. Cargar información del estudiante nuevamente.");
        System.out.println("2. Revisar avance de estudiante");
        System.out.println("3. Editar información de una materia");
        System.out.println("4. Generar reporte notas");
        System.out.println("5. Dar candidatura grado");
        System.out.println("6. Crear planeación");
        System.out.println("7. Cambiar Estudiante.");
        System.out.println("8. Salir");
        int opcion = sn.nextInt();
        switch (opcion)
        {
            case 1:
            System.out.println("Ingresa la ruta donde se encuentra el archivo: ");
            File avanceopc = new File(sn.next());
            analizador.cargarAvanceCoordinador(avanceopc, coordinador, sn);
            estudiante = CoordinadorAcademico.darEstudiante(codigoEstudianteRevisar);
            break;
            case 2:
            CoordinadorAcademico.revisarAvance(estudiante);
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 3 :
            editarMateriaCoordinador(estudiante, sn);
            break;
            case 4: 
            System.out.println("¿Quieres generar un reporte de notas de un semestre particular o de toda la carrera?");
            System.out.println("1. Semestre Específico");
            System.out.println("2. Toda la carrera");
            int opci = sn.nextInt();
            switch (opci)
            {
                case 1:
                System.out.println("Ingresa el semestre para generar el reporte: ");
                int semestre = sn.nextInt();
                try {
                    Estudiante copia = estudiante.clone();
                    ArrayList<MateriaEstudiante> lista = copia.darCursosTomados();
                    for (MateriaEstudiante materia : estudiante.darCursosTomados())
                    {
                        if(materia.darSemestre() != semestre) 
                        {
                            lista.remove(materia);
                        }
                    }
                    copia.setCursosTomados(lista);
                    System.out.println(reporteNotas.darReporteNotas(copia));
                    seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                System.out.println(reporteNotas.darReporteNotas(estudiante));
                seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
                break;
            }
            break;
            case 5:
            candidaturaGrado.darCandidaturaGrado(estudiante,pensum);
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 6:
            registrarMateriaPlaneadorCoordinador(sn, estudiante, coordinador, pensum, analizador, avance,"");
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 7:
            seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, avance);
            break;
            case 8:
            sn.close();
            System.exit(0); 
            break;
            }
    }
    @Deprecated
    public static void registrarMateriaEstudiante(Scanner sn, Estudiante estudiante, Pensum pensum, analizadorArchivo analizador)
    {

        String nota;
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que viste la materia: ");
        try
        {
            ///semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }
        System.out.println("Introduce la nota definitiva que sacaste en la materia.");
        nota = sn.next();
        try
        {
            Double notaNum = Double.valueOf(nota);
            if(notaNum>5.0 || notaNum < 1.5)
            {
                System.out.println("Debes insertar una nota válida.");
                registrarMateriaEstudiante(sn, estudiante, pensum, analizador);
            }
        }
        catch (NumberFormatException e) 
        {
            if(!nota.equals("A")&&!nota.equals("R")&&!nota.equals("PD")&&!nota.equals("I")&&!nota.equals("PE"))
            {
                System.out.println("Debes insertar una nota válida.");
                registrarMateriaEstudiante(sn, estudiante, pensum, analizador);
            }
        }
        System.out.println("¿El curso "+ codigoMateria + "es de tipo especial? (Tipo E o Tipo Épsilon)");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opc = sn.nextInt();
        switch(opc)
        {
            case 1:
            System.out.println("¿Qué tipo de curso es "+ codigoMateria + "?");
            System.out.println("1. Tipo E");
            System.out.println("2. Tipo Épsilon");
            System.out.println("3. Ambos");
            int opc2 = sn.nextInt();
            switch(opc2)
            {
                case 1:
                ///estudiante.registrarMaterias(codigoMateria, semestre, nota, true, false, pensum, sn);
                break;
                case 2:
                ///estudiante.registrarMaterias(codigoMateria, semestre, nota, false, true, pensum, sn);
                break;
                case 3:
                ///estudiante.registrarMaterias(codigoMateria, semestre, nota, true, true, pensum, sn);
            }
            break;
            case 2:
            ///estudiante.registrarMaterias(codigoMateria, semestre, nota, false, false, pensum, sn);
        }   
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaEstudiante(sn, estudiante, pensum, analizador);
                case 2:
                seleccionEstudiante(sn, pensum, estudiante, analizador); 
        }
    }
    public static void registrarMateriaPlaneadorEstudiante(Scanner sn, Estudiante estudiante, Pensum pensum, analizadorArchivo analizador,String planactual)
    {
        ///int semestre = 0;
        ///String nota = "A";
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que se verá la materia: ");
        try
        {
            ///semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }   
        System.out.println("¿El curso "+ codigoMateria + " es de tipo especial? (Tipo E o Tipo Épsilon)");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opc = sn.nextInt();
        switch(opc)
        {
            case 1:
            System.out.println("¿Qué tipo de curso es "+ codigoMateria + "?");
            System.out.println("1. Tipo E");
            System.out.println("2. Tipo Épsilon");
            System.out.println("3. Ambos");
            int opc2 = sn.nextInt();
            switch(opc2)
            {
                case 1:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, true, false);
                break;
                case 2:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, false, true);
                break;
                case 3:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, true, true);
                break;
            }
            case 2:
            ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, false, false);
            break;
        }
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaPlaneadorEstudiante(sn, estudiante, pensum, analizador,planactual);
                case 2:
                System.out.println("El plan actual es: \n"+"Materia     Semestre\n"+planactual);
                System.out.println("Ingresa la ruta para guardar la planeación: (escribe exit para no guardar y volver al menú anterior)");
                String ruta = sn.next();
                if(ruta.toLowerCase().contains("exit"))
                {
                    seleccionEstudiante(sn, pensum, estudiante, analizador);
                }
                else
                {
                    ///File planeacion = new File(ruta);
                    ///try {
                        ///planeador.guardarPlaneación(planactual, analizador, estudiante, planeacion);
                        ///System.out.println("La planeación fue guardada en: "+planeacion.getAbsolutePath());
                    ///} ///catch (FileNotFoundException e) {
                        ///e.printStackTrace();
                    ///} ///catch (UnsupportedEncodingException e) {
                        ///e.printStackTrace();
                    ///}
                    seleccionEstudiante(sn, pensum, estudiante, analizador);
                }   
        }
    }

    public static void registrarMateriaPlaneadorCoordinador(Scanner sn, Estudiante estudiante, CoordinadorAcademico coordinador, Pensum pensum, analizadorArchivo analizador, File archivo,String planactual)
    {
        ///int semestre = 0;
        ///String nota = "A";
        System.out.println("Introduce el código de la materia: ");
        String codigoMateria = sn.next();
        System.out.println("Introduce el semestre en que se verá la materia: ");
        try
        {
            //semestre = sn.nextInt();
        }
        catch (InputMismatchException e) 
        {
        System.out.println("Debes insertar un semestre válido.");
        sn.next();
        }   
        System.out.println("¿El curso "+ codigoMateria + "es de tipo especial? (Tipo E o Tipo Épsilon)");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opc = sn.nextInt();
        switch(opc)
        {
            case 1:
            System.out.println("¿Qué tipo de curso es "+ codigoMateria + "?");
            System.out.println("1. Tipo E");
            System.out.println("2. Tipo Épsilon");
            System.out.println("3. Ambos");
            int opc2 = sn.nextInt();
            switch(opc2)
            {
                case 1:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, true, false);
                break;
                case 2:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, false, true);
                break;
                case 3:
                ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, true, true);
                break;
            }
            case 2:
            ///planactual += planeador.crearPlaneacion(estudiante, pensum, sn,codigoMateria,semestre,nota, false, false);
            break;
        }
            System.out.println("¿Quieres seguir registrando materias?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int seguir = sn.nextInt();
            switch (seguir)
            {
                case 1:
                registrarMateriaPlaneadorCoordinador(sn, estudiante, coordinador, pensum, analizador, archivo,planactual);
                case 2:
                System.out.println("El plan actual es: \n"+"Materia     Semestre\n"+planactual);
                System.out.println("Ingresa la ruta para guardar la planeación: ");
                ////File planeacion = new File(sn.next());
                ///try {
                    ////planeador.guardarPlaneación(planactual, analizador, estudiante, planeacion);
                    ////System.out.println("La planeación fue guardada en: "+planeacion.getAbsolutePath());
                ////} catch (FileNotFoundException e) {
                   /// e.printStackTrace();
                ///} catch (UnsupportedEncodingException e) {
                  ///  e.printStackTrace();
                ///}
                seleccionCoordinadorAcademico(sn, pensum, coordinador, analizador, archivo); 
        }
    }

    public static void editarMateriaEstudiante(Estudiante estudiante, Scanner sn)
    {
        System.out.println("Ingresa el código de la materia a editar: ");
        String codigoMateria = sn.next();
        boolean encontrar = false;
        for (MateriaEstudiante materia : estudiante.darCursosTomados()) 
        {
            if (materia.darCodigo().contains(codigoMateria))
            {   
                encontrar = true;
                System.out.println("¿Qué característica de la materia desea editar?");
                System.out.println("1. Nota");
                System.out.println("2. Estado (Inscrita/Retirada)");
                int opcion = sn.nextInt();
                switch (opcion)
                {
                    case 1:
                    System.out.println("Ingresa la nueva nota: ");
                    String nota = sn.next();
                    try
                    {
                        Double notaNum = Double.valueOf(nota);
                        if(notaNum>5.0 || notaNum < 1.5)
                        {
                            System.out.println("Debes insertar una nota válida.");
                        }
                        else
                        {
                            materia.cambiarNota(nota);
                            System.out.println("Nota cambiada satisfactoriamente a: " + materia.darNota());
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        if(!nota.equals("A")&&!nota.equals("R")&&!nota.equals("PD")&&!nota.equals("I")&&!nota.equals("PE"))
                        {
                            System.out.println("Debes insertar una nota válida.");
                        }
                        else
                        {
                            materia.cambiarNota(nota);
                            System.out.println("Nota cambiada satisfactoriamente a: " + materia.darNota());
                        }
                    }
                    break;
                    case 2:
                    System.out.println("Nuevo estado de la materia: ");
                    System.out.println("1. Inscrita.");
                    System.out.println("2. Retirada.");
                    int num = sn.nextInt();
                    switch(num)
                    {
                        case 1:
                        materia.setRetiro(false);
                        System.out.println("Estado cambiado satisfactoriamente a: " + materia.darInfoRetiro());
                        break;
                        case 2:
                        materia.setRetiro(true);
                        System.out.println("Estado cambiado satisfactoriamente a: " + materia.darInfoRetiro());
                        break;

                    }
                    break;  
                }
                break;
            }
        
        if(encontrar == false)
        {
            System.out.println("La materia no fue encontrada en los cursos inscritos.");
        }
    
    }
    }

    public static void editarMateriaCoordinador(Estudiante estudiante, Scanner sn)
    {
        System.out.println("Ingresa el código de la materia a editar: ");
        String codigoMateria = sn.next();
        boolean encontrar = false;
        for (MateriaEstudiante materia : estudiante.darCursosTomados()) 
        {
            if (materia.darCodigo().contains(codigoMateria))
            {   
                encontrar = true;
                System.out.println("¿Qué característica de la materia desea editar?");
                System.out.println("1. Nota");
                System.out.println("2. Estado (Inscrita/Retirada)");
                System.out.println("3. Editar tipo de la materia");
                int opcion = sn.nextInt();
                switch (opcion)
                {
                    case 1:
                    System.out.println("Ingresa la nueva nota: ");
                    String nota = sn.next();
                    try
                    {
                        Double notaNum = Double.valueOf(nota);
                        if(notaNum>5.0 || notaNum < 1.5)
                        {
                            System.out.println("Debes insertar una nota válida.");
                        }
                        else
                        {
                            materia.cambiarNota(nota);
                            System.out.println("Nota cambiada satisfactoriamente a: " + materia.darNota());
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        if(!nota.equals("A")&&!nota.equals("R")&&!nota.equals("PD")&&!nota.equals("I")&&!nota.equals("PE"))
                        {
                            System.out.println("Debes insertar una nota válida.");
                        }
                        else
                        {
                            materia.cambiarNota(nota);
                            System.out.println("Nota cambiada satisfactoriamente a: " + materia.darNota());
                        }
                    }
                    break;
                    case 2:
                    System.out.println("Nuevo estado de la materia: ");
                    System.out.println("1. Inscrita.");
                    System.out.println("2. Retirada.");
                    int num = sn.nextInt();
                    switch(num)
                    {
                        case 1:
                        materia.setRetiro(false);
                        System.out.println("Estado cambiado satisfactoriamente a: " + materia.darInfoRetiro());
                        break;
                        case 2:
                        materia.setRetiro(true);
                        System.out.println("Estado cambiado satisfactoriamente a: " + materia.darInfoRetiro());
                        case 3:
                        editarMateriaCoordinador(estudiante, sn);
                        break;         
                    }
                    case 3:
                    System.out.println("¿Desea quitar o añadirle tipos a la materia (Tipo E, Épsilon, CBU, CLE, etc)");
                    System.out.println("1. Añadir");
                    System.out.println("2. Quitar");
                    System.out.println("3. Salir");
                    String tipoMat = materia.darTipoMateria();
                    int coord = sn.nextInt();
                    switch(coord)
                    {
                        case 1 :
                        System.out.println("¿Qué tipo deseas añadir?");
                        System.out.println("1. Tipo E");
                        System.out.println("2. Tipo Épsilon");
                        System.out.println("3. Tipo CBU");
                        System.out.println("4. Curso de Libre Elección");
                        System.out.println("5. Electiva Profesional");
                        System.out.println("6. Electiva en Ingeniería");
                        System.out.println("7. Electiva en Ciencias");
                        int tipe = sn.nextInt();
                        switch(tipe)
                        {
                            case 1:
                            tipoMat += " - Tipo E";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 2:
                            tipoMat += " - Tipo Épsilon";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 3:
                            tipoMat += " - CBU";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 4:
                            tipoMat += " - Curso de Libre Elección";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 5:
                            tipoMat += " - Electiva Profesional";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 6:
                            tipoMat += " - Electiva en Ingeniería";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                            case 7:
                            tipoMat += " - Electiva en Ciencias";
                            materia.setType(tipoMat);
                            editarMateriaCoordinador(estudiante, sn);
                            break;
                        }
                        break;
                        case 2:
                        System.out.println("¿Qué tipo deseas quitar?");
                        System.out.println("1. Tipo E");
                        System.out.println("2. Tipo Épsilon");
                        System.out.println("3. Tipo CBU");
                        System.out.println("4. Curso de Libre Elección");
                        System.out.println("5. Electiva Profesional");
                        System.out.println("6. Electiva en Ingeniería");
                        System.out.println("7. Electiva en Ciencias");
                        int quitar = sn.nextInt();
                        switch (quitar)
                            {
                                case 1:
                                tipoMat.replace(" - Tipo E", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 2:
                                tipoMat.replace(" - Tipo Épsilon", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 3:
                                tipoMat.replace(" - CBU", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 4:
                                tipoMat.replace(" - Curso de Libre Elección", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 5:
                                tipoMat.replace(" - Electiva Profesional", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 6:
                                tipoMat.replace(" - Electiva en Ingeniería", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                                case 7:
                                tipoMat.replace(" - Electiva en Ciencias", "");
                                materia.setType(tipoMat);
                                editarMateriaCoordinador(estudiante, sn);
                                break;
                            }
                    }
                    break;  
                }
                break;
            }
        
        if(encontrar == false)
        {
            System.out.println("La materia no fue encontrada en los cursos inscritos.");
        }
    
    }
    }

    public void cargarPensumAnalizador(File archivo)
    {
        analizador.cargarPensum(archivo);
    }

    public Pensum darPensum()
    {
        return analizador.darPensum();
    }

    public int cargarAvanceEstudiante(File archivo, Estudiante estudiante)
    {
        return analizador.cargarAvanceEstudiante(archivo, estudiante);
    }

    public int guardarAvanceEstudiante(File archivo, Estudiante estudiante) throws FileNotFoundException, UnsupportedEncodingException
    {
        return analizador.guardarAvanceEstudianteArchivo(archivo, estudiante.darNombre(), estudiante.darCodigo(), estudiante.darCarrera(), estudiante.darCursosTomados());
    }

    public int guardarPlan(File archivo, String plan, Estudiante estudiante) throws FileNotFoundException, UnsupportedEncodingException
    {
        return analizador.guardarPlaneación(archivo, plan, estudiante);
    }
}

