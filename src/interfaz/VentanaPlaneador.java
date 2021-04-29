package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
import curriculo.MateriaEstudiante;
import curriculo.Pensum;
import funcionalidades.planeador;

@SuppressWarnings({"unchecked", "rawtypes"})
public class VentanaPlaneador extends JPanel implements ActionListener
{
    private JButton registrarMateria;
    private JButton editarMateria;
    private JButton guardar;
    private JButton volver;
    private VentanaPrincipal ventanaMain;
    private systemMain sistema;
    private Estudiante estudiante;
    private Pensum pensum;
    private ArrayList<String> parts;
    private String lastSubject;
    private Estudiante copia;
    private String plan;

    public VentanaPlaneador(Estudiante pEstudiante, VentanaPrincipal pVentanaMain, systemMain pSistema, Pensum pPensum, Estudiante pCopia, ArrayList<String> pParts)
    {
        copia = pCopia;
        estudiante = pEstudiante;
        pensum = pPensum;
        parts = pParts;
        plan = "";     
        ventanaMain = pVentanaMain;
        sistema = pSistema;
		setLayout(new BorderLayout());
        ///Botones y paneles
        add(PanelOpciones(),BorderLayout.CENTER);
        add(PanelPlan(estudiante),BorderLayout.EAST);
        add(Volver(),BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
    }
    public JPanel PanelOpciones()
    {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones,BoxLayout.PAGE_AXIS));
        registrarMateria = new JButton("Planear semestre");
        registrarMateria.addActionListener(this);
        editarMateria = new JButton("Editar materia del plan");
        editarMateria.addActionListener(this);
        guardar = new JButton("Guardar plan actual");
        guardar.addActionListener(this);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,40)));
        panelOpciones.add(registrarMateria);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(editarMateria);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(guardar);
        registrarMateria.setAlignmentX(CENTER_ALIGNMENT);
        editarMateria.setAlignmentX(CENTER_ALIGNMENT);
        guardar.setAlignmentX(CENTER_ALIGNMENT);
        return panelOpciones;
    }

    public JPanel PanelPlan(Estudiante estudiante)
    {
        JPanel panelMateriasPlaneadas = new JPanel();
        JList <String> materiasVistas = new JList(parts.toArray());
        JScrollPane scroll = new JScrollPane(materiasVistas);
        panelMateriasPlaneadas.add(scroll);
        return panelMateriasPlaneadas;
    }
    public JPanel Volver()
    {
      JPanel panelVolver = new JPanel();
      panelVolver.setLayout(new BoxLayout(panelVolver,BoxLayout.LINE_AXIS));
      volver = new JButton("Volver al menú inicial");
      volver.addActionListener(this);
      panelVolver.add(volver);
      return panelVolver;
    }

    public String registroMateriasPlaneador(Estudiante estudiante, String codigoMateria, int semester, String grade, boolean esE, boolean esEpsilon, Pensum pensum, boolean esCle, int credsCle, String plan)
    {
        lastSubject = planeador.crearPlaneacion(estudiante, pensum, codigoMateria, semester, grade, esE, esEpsilon, esCle, credsCle);
        plan += lastSubject;
        int respuesta = planeador.darError();
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
            actualizarLista();
        }
        return plan;
    }

    public String registroClePlaneador(Estudiante estudiante, String codMateria, int semestre, String nota, boolean esE, boolean epsilon, Pensum pensum, String plan)
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
            return "";
        }
        else if(result2 == -1)
        {
            JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Número de Créditos Faltantes)"), "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        else
        {
            try
            {
                int creditsCle = Integer.parseInt(creds.getText());
                plan += registroMateriasPlaneador(estudiante, codMateria, semestre, nota, esE, epsilon, pensum, true, creditsCle, plan);
                return plan;
            }
            catch (NumberFormatException exa)
            {
                JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Formato inválido de créditos)"), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return plan;
        }
    }





	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == volver)
		{
			ventanaMain.actualizarMain(new VentanaEstudiante(estudiante.darNombre(), estudiante.darCodigo(), estudiante.darCodigo(), ventanaMain, sistema , estudiante));
		}
        else if (e.getSource() == registrarMateria)
        {
                JTextField codMateria = new JTextField();
                JTextField semestre = new JTextField();
                JCheckBox tipoE = new JCheckBox("Tipo E");
                JCheckBox epsilon = new JCheckBox("Tipo Épsilon");
                JCheckBox cle = new JCheckBox("Curso de Libre Elección");
                final JComponent[] inputs = new JComponent[] 
                {
                new JLabel("Materia a Registrar:"),
                codMateria,
                new JLabel("Semestre:"),
                semestre,
                new JLabel("¿El curso es de tipo especial?"),
                tipoE,
                epsilon,
                cle
                };
                int result = JOptionPane.showConfirmDialog(this, inputs, "Planear materia", JOptionPane.PLAIN_MESSAGE);
                if(result == JOptionPane.OK_OPTION && (codMateria.getText().equals("")) || semestre.getText().equals(""))
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
                    }
                    catch (NumberFormatException ex)
                    {
                        error = -1;
                        JOptionPane.showMessageDialog(this, new JLabel("Solo puedes ingresar números en semestre."), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (error != -1)
                    {
                        if (copia == null)
                        {
                            try {
                                copia = estudiante.clone();
                            } catch (CloneNotSupportedException exer) {
                                exer.printStackTrace();
                                copia = null;
                            }
                            if (copia == null)
                            {
                                System.out.println("Hubo un error en la copia del estudiante.");
                            }
                        }
                        if(cle.isSelected())
                        {
                            plan += registroClePlaneador(copia, codMateria.getText(), Integer.parseInt(semestre.getText()), "A", tipoE.isSelected(), epsilon.isSelected(), pensum, plan);
                        }
                        else
                        {
                            plan += registroMateriasPlaneador(copia, codMateria.getText(), Integer.parseInt(semestre.getText()), "A", tipoE.isSelected(), epsilon.isSelected(), pensum, false, 0, plan);
                        }
                    }
            }
            
        }
        else if (e.getSource() == editarMateria)
        {
            copia = planeador.darCopia();
            if(copia == null)
            {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes que empezar una planeación antes de editar las materias"), "Error", JOptionPane.ERROR_MESSAGE);
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
                    for(MateriaEstudiante materiaAEditar : copia.darCursosTomados())
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
                                        copia.retirarMateria(editar);
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
                                        copia.retirarMateria(editar);
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
        else if (e.getSource() == guardar)
        {
            if(parts.isEmpty())
            {
                JOptionPane.showMessageDialog(this, new JLabel("No has realizado ninguna planeación."), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int cont = 0;
                for(String part : parts)
                {
                    if(part == "" || part == null)
                    {
                        parts.remove(part);
                        System.out.println("Hola");
                    }
                    cont +=1;
                }
                String guardarPlan = String.join("\n", parts);
                File archivo = null;
		        JFileChooser fc = new JFileChooser();
    		    fc.setDialogTitle("Selecciona dónde guardar el plan.");
	    		fc.setFileFilter(new FiltroCSV());
                int response = fc.showSaveDialog(this);
                {
                    if (response == JFileChooser.APPROVE_OPTION)
                    {
                        archivo = fc.getSelectedFile();
                        try {
                            planeador.guardarPlaneación(guardarPlan, sistema, estudiante, archivo);
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
	}

    public void actualizarLista()
    {
        parts.add(lastSubject);
        ventanaMain.actualizarMain(new VentanaPlaneador(estudiante, ventanaMain, sistema, pensum, copia, parts));
    }
}
