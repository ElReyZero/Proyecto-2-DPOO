package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
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
        registrarMaterias = new JButton("Registrar materias manualmente");
        registrarMaterias.addActionListener(this);
        candidaturaGrado = new JButton("Verificar candidatura de grado");
        candidaturaGrado.addActionListener(this);
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
        panelOpciones.add(Box.createRigidArea(new Dimension(0,15)));
        panelOpciones.add(panelArchivos);
        planearSemestre.setAlignmentX(CENTER_ALIGNMENT);
        reporteNotas.setAlignmentX(CENTER_ALIGNMENT);
        editarCurso.setAlignmentX(CENTER_ALIGNMENT);
        registrarMaterias.setAlignmentX(CENTER_ALIGNMENT);
        candidaturaGrado.setAlignmentX(CENTER_ALIGNMENT);
        return panelOpciones;
    }
    public JPanel PanelArchivosEstudiante()
    {
        JPanel panelCarga = new JPanel();
        panelCarga.setLayout(new BoxLayout(panelCarga,BoxLayout.LINE_AXIS));
        cargarPensum = new JButton("Cargar Pensum");
        cargarPensum.addActionListener(this);
        guardarArchivo = new JButton("Guardar archivo");
        guardarArchivo.addActionListener(this);
        cargarArchivo = new JButton("Cargar un archivo");
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
        JButton boton = (JButton) e.getSource();
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
                int result = JOptionPane.showConfirmDialog(this, inputs, "Registrar Materias", JOptionPane.PLAIN_MESSAGE);
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
                        int numSemestre = Integer.parseInt(semestre.getText());
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
                        if(tipoE.isSelected() && epsilon.isSelected() && cle.isSelected())
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
                                    registroMat(estudiante, codMateria.getText(), Integer.parseInt(semestre.getText()), nota.getText(), true, true, pensum, true, creditsCle);
                                }
                                catch (NumberFormatException exa)
                                {
                                    JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Formato inválido de créditos)"), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            
                        }
                        else if (tipoE.isSelected()&& !epsilon.isSelected())
                        {
    
                        }
                    }
                }
            }

        }
		
	}
	
    public void registroMat(Estudiante estudiante, String codigoMateria, int semester, String grade, boolean esE, boolean esEpsilon, Pensum pensum, boolean esCle, int credsCle)
    {
        int respuesta = estudiante.registrarMaterias(codigoMateria, semester, grade, esE, esEpsilon, pensum, esCle, credsCle);
        if(respuesta == -1)
            {
                JOptionPane.showMessageDialog(this, new JLabel("El código de la materia "+codigoMateria+"no está escrito en un formato adecuado. Formato: AAAA-XXXX"), "Error", JOptionPane.ERROR_MESSAGE);
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
    }

    public File cargarArchivoMain(File archivo)
    {
        return archivo;
    }
}


