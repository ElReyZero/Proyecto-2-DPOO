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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

    public VentanaPlaneador(Estudiante estudiante, VentanaPrincipal pVentanaMain, systemMain pSistema, Pensum pensum)
    {
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

            String plan = planeador.crearPlaneacion(estudiante, pensum, codigoMateria, semestre, "A", tipoE, epsilon, cle, credsCle);
        }
        else if (e.getSource() == editarMateria)
        {

        }
        else if (e.getSource() == guardar)
        {

        }
	}
}