package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;
@SuppressWarnings("serial")
public class VentanaEstudiante extends JPanel implements ActionListener
{

		private JLabel name;
		private JLabel code;
		private JLabel major;
		private JButton planearSemestre;
		private JButton reporteNotas;
		private JButton editarCurso;
		private JButton registrarMaterias;
		private JButton candidaturaGrado;
		private JButton cargarPensum;
		private JButton guardarArchivo;
		private JButton cargarArchivo;
        private JButton validarRequisitos;
		private JButton volver;
		private VentanaPrincipal ventanaMain;
        private systemMain sistema;
        private Estudiante estudiante;
        private Pensum pensum;
		
    public VentanaEstudiante(String nombre, String codigo, String carrera, VentanaPrincipal pVentanaMain, systemMain pSistema, Estudiante pEstudiante)
    {
    	ventanaMain = pVentanaMain;
        sistema = pSistema;
        estudiante = pEstudiante;
		setLayout(new BorderLayout());
        ///Botones y paneles
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new FlowLayout());
        name = new JLabel("Nombre: "+ nombre);
        code = new JLabel("          Código: "+ codigo);
        major = new JLabel("          Carrera: "+ carrera);
        panelInformacion.add(name);
        panelInformacion.add(code);
        panelInformacion.add(major);
        add(panelInformacion, BorderLayout.NORTH);
        JPanel panelOpciones = PanelOpcionesEstudiante();
        add(panelOpciones,BorderLayout.CENTER);
        JPanel volver = Volver();
        add(volver,BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
        pensum = sistema.darPensum();
    }
    public JPanel PanelOpcionesEstudiante()
    {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones,BoxLayout.PAGE_AXIS));
        planearSemestre = new JButton("Planear semestre");
        planearSemestre.addActionListener(this);
        reporteNotas = new JButton("Generar reporte de notas");
        reporteNotas.addActionListener(this);
        editarCurso = new JButton("Editar información de un curso");
        editarCurso.addActionListener(this);
        registrarMaterias = new JButton("Registrar materia manualmente");
        registrarMaterias.addActionListener(this);
        candidaturaGrado = new JButton("Verificar candidatura de grado");
        candidaturaGrado.addActionListener(this);
        validarRequisitos = new JButton("Validar requisitos");
        validarRequisitos.addActionListener(this);
        JPanel panelArchivos = PanelArchivosEstudiante();
        panelOpciones.add(Box.createRigidArea(new Dimension(0,50)));
        panelOpciones.add(registrarMaterias);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(reporteNotas);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(editarCurso);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(planearSemestre);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(candidaturaGrado);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(validarRequisitos);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,15)));
        panelOpciones.add(panelArchivos);
        planearSemestre.setAlignmentX(CENTER_ALIGNMENT);
        reporteNotas.setAlignmentX(CENTER_ALIGNMENT);
        editarCurso.setAlignmentX(CENTER_ALIGNMENT);
        registrarMaterias.setAlignmentX(CENTER_ALIGNMENT);
        candidaturaGrado.setAlignmentX(CENTER_ALIGNMENT);
        validarRequisitos.setAlignmentX(CENTER_ALIGNMENT);
        return panelOpciones;
    }
    public JPanel PanelArchivosEstudiante()
    {
        JPanel panelCarga = new JPanel();
        panelCarga.setLayout(new BoxLayout(panelCarga,BoxLayout.LINE_AXIS));
        cargarPensum = new JButton("Cargar Pensum");
        cargarPensum.addActionListener(this);
        guardarArchivo = new JButton("Guardar avance en archivo");
        guardarArchivo.addActionListener(this);
        cargarArchivo = new JButton("Cargar materias desde archivo");
        cargarArchivo.addActionListener(this);
        panelCarga.add(cargarPensum);
        panelCarga.add(Box.createRigidArea(new Dimension(10,0)));
        panelCarga.add(guardarArchivo);
        panelCarga.add(Box.createRigidArea(new Dimension(10,0)));
        panelCarga.add(cargarArchivo);
        panelCarga.add(Box.createRigidArea(new Dimension(0,150)));
        guardarArchivo.setAlignmentX(CENTER_ALIGNMENT);
        cargarArchivo.setAlignmentX(CENTER_ALIGNMENT);
        return panelCarga;
    }
    public JPanel Volver()
    {
      JPanel panelVolver = new JPanel();
      panelVolver.setLayout(new BoxLayout(panelVolver,BoxLayout.LINE_AXIS));
      volver = new JButton("Volver a selección de usuario");
      volver.addActionListener(this);
      panelVolver.add(volver);
      return panelVolver;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
        var boton = e.getSource();
		if(boton == volver)
		{
			ventanaMain.resetMain();
		}
        else if(boton == cargarPensum)
		{
            File archivo = null;
		    JFileChooser fc = new JFileChooser();
		    fc.setDialogTitle("Seleccione el archivo con el pensum");
			fc.setFileFilter(new FiltroCSV());
            int respuesta = fc.showOpenDialog(this);
            if(respuesta == JFileChooser.APPROVE_OPTION)
            {
                archivo = fc.getSelectedFile();
                sistema.cargarPensumAnalizador(archivo);
                pensum = sistema.darPensum();
            }           
        }
        else if(boton == registrarMaterias)
        {

            if(pensum == null)
            {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de registrar materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JTextField codMateria = new JTextField();
                JTextField nota = new JTextField();
                JTextField semestre = new JTextField();
                JCheckBox tipoE = new JCheckBox("Tipo E");
                JCheckBox epsilon = new JCheckBox("Tipo Épsilon");
                JCheckBox cle = new JCheckBox("Curso de Libre Elección");
                final JComponent[] inputs = new JComponent[] 
                {
                new JLabel("Materia a Registrar:"),
                codMateria,
                new JLabel("Nota:"),
                nota,
                new JLabel("Semestre:"),
                semestre,
                new JLabel("¿El curso es de tipo especial?"),
                tipoE,
                epsilon,
                cle
                };
                int result = JOptionPane.showConfirmDialog(this, inputs, "Registrar Materia", JOptionPane.PLAIN_MESSAGE);
                if(result == JOptionPane.OK_OPTION && (codMateria.getText().equals("")) || nota.getText().equals("") || semestre.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this, new JLabel("Tienes que completar todos los datos."), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(result == -1)
                {
                }
                else
                {
                    int error = 1;
                    try
                    {
                        Integer.parseInt(semestre.getText());
                        try
                        {
                            Double notaNum = Double.valueOf(nota.getText());
                            if(notaNum>5.0 || notaNum < 1.5)
                            {
                                error = -1;
                                JOptionPane.showMessageDialog(this, new JLabel("Debes insertar una nota válida."), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        catch (NumberFormatException enx) 
                        {
                            if(!nota.getText().equals("A")&&!nota.getText().equals("R")&&!nota.getText().equals("PD")&&!nota.getText().equals("I")&&!nota.getText().equals("PE"))
                            {
                                error = -1;
                                JOptionPane.showMessageDialog(this, new JLabel("Debes insertar una nota válida."), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (NumberFormatException ex)
                    {
                        error = -1;
                        JOptionPane.showMessageDialog(this, new JLabel("Solo puedes ingresar números en semestre."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
    
                    if (error != -1)
                    {
                        if(cle.isSelected())
                        {
                            registroCle(estudiante, codMateria.getText(), Integer.parseInt(semestre.getText()), nota.getText(), tipoE.isSelected(), epsilon.isSelected(), pensum);
                        }
                        else
                        {   
                            registroMat(estudiante, codMateria.getText(), Integer.parseInt(semestre.getText()), nota.getText(), tipoE.isSelected(), epsilon.isSelected(), pensum, false, 0);
                        }
                    }
                }
            }
        }
        else if(boton == reporteNotas)
        {
            if(pensum == null)
            {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de revisar tu avance."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String [] options = {"Toda la carrera", "Semestre Específico"};
                int ans = JOptionPane.showOptionDialog(this, "¿Quieres generar el reporte para toda tu carrera o un semestre específico?", "Reporte Notas", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (ans == 0)
                {
                    ventanaMain.actualizarMain(new VentanaReporteNotas(ventanaMain, sistema, estudiante, true, null));
                }
                else if (ans == 1)
                {
                    JTextField sem = new JTextField();
                    final JComponent[] inputs = new JComponent[] 
                    {
                    new JLabel("Ingresa el Semestre:"),
                    sem
                    };
                    int result = JOptionPane.showConfirmDialog(this, inputs, "Seleccionar Semestre", JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION && sem.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Tienes que completar todos los datos."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else 
                    {
                        try
                        {
                            int semes = Integer.parseInt(sem.getText());
                            try {
                                Estudiante copia = estudiante.clone();
                                ArrayList<MateriaEstudiante> lista = copia.darCursosTomados();
                                for (MateriaEstudiante materia : estudiante.darCursosTomados())
                                {
                                    if(materia.darSemestre() != semes) 
                                    {
                                        lista.remove(materia);
                                    }
                                }
                                copia.setCursosTomados(lista);
                                ventanaMain.actualizarMain(new VentanaReporteNotas(ventanaMain, sistema, copia, false, estudiante));
                            }
                            catch (CloneNotSupportedException exe)
                            {
                                exe.printStackTrace();
                            }
                        }
                        catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this, new JLabel("Tienes que ingresar un número."), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                
                }
                else 
                {

                }
            }
        }
        else if(boton == cargarArchivo)
        {
            if(pensum == null)
            {
                
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de registrar materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                File archivo = null;
		        JFileChooser fc = new JFileChooser();
    		    fc.setDialogTitle("Seleccione el archivo con las materias a cargar");
	    		fc.setFileFilter(new FiltroCSV());
                int respuesta = fc.showOpenDialog(this);
                if(respuesta == JFileChooser.APPROVE_OPTION)
                {
                    archivo = fc.getSelectedFile();
                    int res = sistema.cargarAvanceEstudiante(archivo, estudiante);
                    if (res == -10)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("El archivo no fue encontrado"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -11)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Error de lectura"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                        else if(res == -12)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Error en los datos: un número no se pudo convertir a int ..."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -1)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Hubo un problema con el código de una materia, no está escrito en un formato adecuado. Formato: AAAA-XXXX"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -2)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Hubo un problema inscribiendo materias. No se han visto todas las materias de Nivel 1. Error: (Restricción de Nivel)"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -3)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Hubo un problema inscribiendo materias. No se han visto todas las materias de Nivel 2. Error: (Restricción de Nivel)"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -4)
                    {
                        String errorEstudianteReg = estudiante.darErrorString();
                        JOptionPane.showMessageDialog(this, new JLabel(errorEstudianteReg), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -5)
                    {
                        String errorEstudianteReg = estudiante.darErrorString();
                        JOptionPane.showMessageDialog(this, new JLabel(errorEstudianteReg), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (res == -6)
                    {
                        String errorEstudianteReg = estudiante.darErrorString();
                        JOptionPane.showMessageDialog(this, new JLabel("Hubo un problema inscribiendo materias " + errorEstudianteReg + " no fue encontrada."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == -7)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("No se puede repetir una materia que no haya sido perdida."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(res == 0)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("Materias registradas satisfactoriamente!"), null, JOptionPane.INFORMATION_MESSAGE);
                    }
                }    
            } 
        }
        else if (boton == guardarArchivo)
        {
            if(pensum == null)
            {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de registrar materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                File archivo = null;
		        JFileChooser fc = new JFileChooser();
    		    fc.setDialogTitle("Selecciona dónde guardar tu avance");
	    		fc.setFileFilter(new FiltroCSV());
                int response = fc.showSaveDialog(this);
                {
                    if (response == JFileChooser.APPROVE_OPTION)
                    {
                        archivo = fc.getSelectedFile();
                        try {
                            sistema.guardarAvanceEstudiante(archivo, estudiante);
                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(this, new JLabel("El archivo no fue encontrado"), "Error", JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        } catch (UnsupportedEncodingException e1) {
                            JOptionPane.showMessageDialog(this, new JLabel("Hubo un problema con el encoding del archivo"), "Error", JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        else if(boton == validarRequisitos)
        {
            if(pensum == null)
            {
                
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de registrar materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
            JComboBox<String> opciones = new JComboBox<String>();
            opciones.addItem("Ninguno");
            opciones.addItem("Examen de inglés de admisión");
            opciones.addItem("Aprobó el nivel 6 de su segunda lengua");
            opciones.addItem("Requisito de segunda lengua (Homologación con examen)");
            opciones.addItem("Requisito de segunda lengua (Nivel 10)");
            opciones.addActionListener(this);
                final JComponent[] inputs = new JComponent[] 
                {
                new JLabel("Seleccione el requisito a validar: "),
                opciones
                };
                int revisar = JOptionPane.showConfirmDialog(this, inputs, "Validar requisitos", JOptionPane.PLAIN_MESSAGE);
                String opcion = opciones.getSelectedItem().toString();
                if(opcion.equals("Examen de inglés de admisión"))
                {
                    estudiante.registrarMaterias("LENG-2999", 1, "A",false,false, pensum, false, 0);
                    JOptionPane.showMessageDialog(this, new JLabel("Requisito registrado"), null, JOptionPane.INFORMATION_MESSAGE);
                }
                else if(opcion.equals("Aprobó el nivel 6 de su segunda lengua"))
                {
                    estudiante.registrarMaterias("LENG-2999", 1, "A",false,false, pensum, false, 0);
                    JOptionPane.showMessageDialog(this, new JLabel("Requisito registrado"), null, JOptionPane.INFORMATION_MESSAGE);
                }
                else if(opcion.equals("Requisito de segunda lengua (Homologación con examen)"))
                {
                    estudiante.registrarMaterias("LENG-3999", 1, "A",false,false, pensum, false, 0);
                    JOptionPane.showMessageDialog(this, new JLabel("Requisito registrado"), null, JOptionPane.INFORMATION_MESSAGE);
                }
                else if(opcion.equals("Requisito de segunda lengua (Nivel 10)"))
                {
                    estudiante.registrarMaterias("LENG-3999", 1, "A",false,false, pensum, false, 0);
                    JOptionPane.showMessageDialog(this, new JLabel("Requisito registrado"), null, JOptionPane.INFORMATION_MESSAGE);
                }
                else if(revisar == JOptionPane.CLOSED_OPTION)
                {
                }
            }
        }
        else if(boton == editarCurso)
        {
            if(pensum == null)
            {
                
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de editar tus materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JTextField codEditar = new JTextField();
                final JComponent[] materia = new JComponent[] 
                {
                    new JLabel("Escriba el código del curso a editar:"),
                    codEditar
                };
                int result = JOptionPane.showConfirmDialog(this, materia, "Editar curso", JOptionPane.PLAIN_MESSAGE);
                
                if(result == JOptionPane.OK_OPTION && codEditar.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this, new JLabel("Tienes que completar todos los datos."), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(result == JOptionPane.OK_OPTION)
                {
                    boolean encontrar = false;
                    MateriaEstudiante editar = null;
                    for(MateriaEstudiante materiaAEditar : estudiante.darCursosTomados())
                    {
                        if (materiaAEditar.darCodigo().contains(codEditar.getText()))
                        {
                            encontrar = true;
                            editar = materiaAEditar;
                            break;
                        }
                    }
    
                    if(encontrar == false)
                    {
                        JOptionPane.showMessageDialog(this, new JLabel("La materia no fue encontrada en los cursos inscritos."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JComboBox<String> estado = new JComboBox<String>();
                        estado.addItem("Inscrita");
                        estado.addItem("Retirada");
                        estado.addActionListener(this);
                        JTextField nota = new JTextField();
                        final JComponent[] edicion = new JComponent[] 
                        {
                            new JLabel("Estado de la materia:"),
                            estado,
                            new JLabel("Nota:"),
                            nota
                        };
                        int resultado = JOptionPane.showConfirmDialog(this, edicion, "Editar curso", JOptionPane.PLAIN_MESSAGE);
                        if(resultado == JOptionPane.OK_OPTION && nota.getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(this, new JLabel("Tienes que completar todos los datos."), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (resultado == JOptionPane.OK_OPTION)
                        {
                            try
                            {
                                Double notaNum = Double.valueOf(editar.darNota());
                                if(notaNum>5.0 || notaNum < 1.5)
                                {
                                    JOptionPane.showMessageDialog(this, new JLabel("Debes insertar una nota válida."), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    editar.cambiarNota(nota.getText());
                                    String est = estado.getSelectedItem().toString();
                                    if(est.equals("Inscrita"))
                                    {   
                                        editar.setRetiro(false);
                                    }
                                    else if(est.equals("Retirada"))
                                    {
                                        editar.setRetiro(true);
                                        estudiante.retirarMateria(editar);
                                    }
                                    if(editar.darInfoRetiro() == false)
                                    {
                                        
                                        JOptionPane.showMessageDialog(this, new JLabel("Nota cambiada satisfactoriamente a: " + editar.darNota()+" Estado de la materia cambiado a: Inscrita"), null, JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(this, new JLabel("Nota cambiada satisfactoriamente a: " + editar.darNota()+" Estado de la materia cambiado a: Retirada" ), null, JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                            catch (NumberFormatException exa)
                            {
                                if(!nota.getText().equals("A")&&!nota.getText().equals("R")&&!nota.getText().equals("PD")&&!nota.getText().equals("I")&&!nota.getText().equals("PE"))
                                {
                                    JOptionPane.showMessageDialog(this, new JLabel("Debes insertar una nota válida."), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    editar.cambiarNota(nota.getText());
                                    String est = estado.getSelectedItem().toString();
                                    if(est.equals("Inscrita"))
                                    {   
                                        editar.setRetiro(false);
                                    }
                                    else if(est.equals("Retirada"))
                                    {   
                                        editar.setRetiro(true);
                                        estudiante.retirarMateria(editar);
                                    }
                                    if(editar.darInfoRetiro() == false)
                                    {
                                        
                                        JOptionPane.showMessageDialog(this, new JLabel("Nota cambiada satisfactoriamente a: " + editar.darNota()+"\nEstado de la materia cambiado a: Inscrita"), null, JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(this, new JLabel("Nota cambiada satisfactoriamente a: " + editar.darNota()+"\nEstado de la materia cambiado a: Retirada" ), null, JOptionPane.INFORMATION_MESSAGE);
                                    }
    
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(boton == candidaturaGrado)
        {
            if(pensum == null)
            {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de editar tus materias."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                ventanaMain.actualizarMain(new VentanaCandidaturaGrado(ventanaMain, sistema, estudiante, pensum));
            } 
        }
        else if(boton == planearSemestre)
        {
            ArrayList<String> lista = new ArrayList<String>();
            lista.add("El plan actual es:    ");
            lista.add("Materia       Semestre\n");
            ventanaMain.actualizarMain(new VentanaPlaneador(estudiante,ventanaMain,sistema,pensum, null, lista));
        }
	}
	
    public void registroMat(Estudiante estudiante, String codigoMateria, int semester, String grade, boolean esE, boolean esEpsilon, Pensum pensum, boolean esCle, int credsCle)
    {
        int respuesta = estudiante.registrarMaterias(codigoMateria, semester, grade, esE, esEpsilon, pensum, esCle, credsCle);
        if(respuesta == -1)
            {
                JOptionPane.showMessageDialog(this, new JLabel("El código de la materia "+codigoMateria+" no está escrito en un formato adecuado. Formato: AAAA-XXXX"), "Error", JOptionPane.ERROR_MESSAGE);
            }
        else if(respuesta == -2)
        {
            JOptionPane.showMessageDialog(this, new JLabel("Para poder inscribir " + codigoMateria + " necesitas haber inscrito todas las materias de nivel 1"), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(respuesta == -3)
        {
            JOptionPane.showMessageDialog(this, new JLabel("Para poder inscribir " + codigoMateria + " necesitas haber inscrito todas las materias de nivel 2"), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(respuesta == -4)
        {
            String errorEstudianteReg = estudiante.darErrorString();
            JOptionPane.showMessageDialog(this, new JLabel("Se está intentando registrar "+ codigoMateria +" sin haber cumplido todos los prerrequisitos previamente." + errorEstudianteReg), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(respuesta == -5)
        {
            String errorEstudianteReg = estudiante.darErrorString();
            JOptionPane.showMessageDialog(this, new JLabel(errorEstudianteReg), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (respuesta == -6)
        {
            JOptionPane.showMessageDialog(this, new JLabel("El curso "+codigoMateria+" no fue encontrado."), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(respuesta == -7)
        {
            JOptionPane.showMessageDialog(this, new JLabel("No se puede repetir una materia que no haya sido perdida."), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(respuesta == 0)
        {
            JOptionPane.showMessageDialog(this, new JLabel(codigoMateria+" Registrada Satisfactoriamente!"), null, JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void registroCle(Estudiante estudiante, String codMateria, int semestre, String nota, boolean esE, boolean epsilon, Pensum pensum)
    {
        JTextField creds = new JTextField();
        final JComponent[] inputsCLE = new JComponent[] 
        {
            new JLabel("¿De cuántos créditos es el Curso de Libre Elección?"),
            creds,
        };
        int result2 = JOptionPane.showConfirmDialog(this, inputsCLE, "Créditos CLE", JOptionPane.PLAIN_MESSAGE);
        if(result2 == JOptionPane.OK_OPTION && (creds.getText().equals("")))
        {
            JOptionPane.showMessageDialog(this, new JLabel("Tienes que completar todos los datos."), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(result2 == -1)
        {
            JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Número de Créditos Faltantes)"), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try
            {
                int creditsCle = Integer.parseInt(creds.getText());
                registroMat(estudiante, codMateria, semestre, nota, esE, epsilon, pensum, true, creditsCle);
            }
            catch (NumberFormatException exa)
            {
                JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Formato inválido de créditos)"), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public File cargarArchivoMain(File archivo)
    {
        return archivo;
    }
}


