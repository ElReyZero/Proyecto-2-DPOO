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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
import curriculo.Pensum;
import funcionalidades.planeador;

@SuppressWarnings("serial")
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

    public VentanaPlaneador(Estudiante pEstudiante, VentanaPrincipal pVentanaMain, systemMain pSistema, Pensum pPensum)
    {
        estudiante = pEstudiante;
        pensum = pPensum;
        if(pensum == null)
        {
                JOptionPane.showMessageDialog(this, new JLabel("Tienes cargar el pensum antes de registrar materias."), "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {        
        ventanaMain = pVentanaMain;
        sistema = pSistema;
		setLayout(new BorderLayout());
        ///Botones y paneles
        add(PanelOpciones(),BorderLayout.WEST);
        add(PanelPlan(estudiante),BorderLayout.EAST);
        add(Volver(),BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
        }
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
        //String[] parts = planeador.darPlaneacion(estudiante).split("\n"); 
        //JList<String> materiasVistas = new JList<String>(parts);
        //JScrollPane scroll = new JScrollPane(materiasVistas);
        //panelMateriasPlaneadas.add(scroll);
        return panelMateriasPlaneadas;
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
		if(e.getSource() == volver)
		{
			ventanaMain.resetMain();
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
                        if(cle.isSelected())
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
                                    String plan = planeador.crearPlaneacion(estudiante, pensum, codMateria.getText(), Integer.parseInt(semestre.getText()), "A", tipoE.isSelected(), epsilon.isSelected(), true, creditsCle);
                                }
                                catch (NumberFormatException exa)
                                {
                                    JOptionPane.showMessageDialog(this, new JLabel("No se registró la materia. (Formato inválido de créditos)"), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        else
                        {
                            //if (o)
                            //{
                            //}
                            //else
                            //{
                            //String plan = planeador.crearPlaneacion(estudiante, pensum, codMateria.getText(), Integer.parseInt(semestre.getText()), "A", tipoE.isSelected(), epsilon.isSelected(), false, 0);
                            //}
                        }
                    }
            }
            
        }
        else if (e.getSource() == editarMateria)
        {

        }
        else if (e.getSource() == guardar)
        {

        }
	}
}