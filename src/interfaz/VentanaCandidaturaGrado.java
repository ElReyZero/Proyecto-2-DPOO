package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import IdentificadorUsuario.CoordinadorAcademico;
import IdentificadorUsuario.Estudiante;
import Sistema.systemMain;
import curriculo.Pensum;
import funcionalidades.candidaturaGrado;
import funcionalidades.reporteNotas;
public class VentanaCandidaturaGrado extends JPanel implements ActionListener
{
    private JButton volver;
    private VentanaPrincipal ventanaMain;
    private systemMain sistema;
    private Estudiante estudiante;
    private JButton botEstado;
    private boolean esCoordinador;
    private CoordinadorAcademico coordinador;
    private Pensum pensum;

    public VentanaCandidaturaGrado(VentanaPrincipal pVentanaMain, systemMain pSistema, Estudiante pEstudiante, Pensum pPensum, boolean pEsCoordinador, CoordinadorAcademico pCoordinador)
    {
        ventanaMain = pVentanaMain;
        sistema = pSistema;
        estudiante = pEstudiante;
        esCoordinador = pEsCoordinador;
        coordinador = pCoordinador;
        pensum = pPensum;
		setLayout(new BorderLayout());
        ///Botones y paneles
        add(PanelInformacion(estudiante,pensum), BorderLayout.WEST);
        add(PanelMaterias(estudiante),BorderLayout.EAST);
        add(Volver(),BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
    }
    public JPanel PanelInformacion(Estudiante estudiante,Pensum pensum)
    {
        JPanel panelInformacion = new JPanel();
        candidaturaGrado.darCandidaturaGrado(estudiante, pensum);
        panelInformacion.setLayout(new BoxLayout(panelInformacion,BoxLayout.PAGE_AXIS));
        panelInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel textPGA = new JLabel("   El PGA es de: " + reporteNotas.promedioPGA(estudiante));
        JLabel textEA = new JLabel("   El estado académico es: " +reporteNotas.estadoAcademico(estudiante));
        JLabel textSSC = new JLabel("   El semestre según créditos: " + reporteNotas.semestreSegunCreditos(estudiante));
        botEstado = new JButton("Estado de candidatura a grado (Detalles)");
        JLabel labelEstado = new JLabel("   Estado: " + candidaturaGrado.darEstado());
        botEstado.addActionListener(this);
        panelInformacion.add(Box.createRigidArea(new Dimension(0,5)));
        panelInformacion.add(textPGA);
        panelInformacion.add(textEA);
        panelInformacion.add(textSSC);
        panelInformacion.add(Box.createRigidArea(new Dimension(0,30)));
        panelInformacion.add(labelEstado);
        panelInformacion.add(Box.createRigidArea(new Dimension(0,5)));
        panelInformacion.add(botEstado);
        return panelInformacion;
    }
    public JPanel PanelMaterias(Estudiante estudiante)
    {
        JPanel panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout (panelMaterias, BoxLayout.PAGE_AXIS));
        panelMaterias.add(PanelMateriasFaltantes(estudiante));
        panelMaterias.add(Box.createRigidArea(new Dimension(0,30)));
        panelMaterias.add(PanelMateriasVistas(estudiante));
        panelMaterias.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panelMaterias;
    }

    public JPanel PanelMateriasFaltantes(Estudiante estudiante)
    {
        JPanel panelMateriasFaltantes = new JPanel();
        String[] falta = candidaturaGrado.darFaltantes().split("\n");
        JList<String> materiasFaltantes = new JList<String>(falta);
        JScrollPane scroll = new JScrollPane(materiasFaltantes);
        panelMateriasFaltantes.add(scroll);
        return panelMateriasFaltantes;
    }

    public JPanel PanelMateriasVistas(Estudiante estudiante)
    {
        JPanel panelMateriasVistas = new JPanel();
        String[] seen = candidaturaGrado.darVistas().split("\n");
        JList<String> materiasVistas = new JList<String>(seen);
        JScrollPane scroll2 = new JScrollPane(materiasVistas);
        panelMateriasVistas.add(scroll2);
        return panelMateriasVistas;
    }

    public JPanel Volver()
    {
      JPanel panelVolver = new JPanel();
      panelVolver.setLayout(new BoxLayout(panelVolver,BoxLayout.LINE_AXIS));
      volver = new JButton("Volver a menú principal");
      volver.addActionListener(this);
      panelVolver.add(volver);
      return panelVolver;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == volver)
		{
            if(esCoordinador == true)
            {
                ventanaMain.actualizarMain(new VentanaCoordinador(coordinador, ventanaMain, sistema, estudiante, pensum));
            }
            else
            {
                ventanaMain.actualizarMain(new VentanaEstudiante(estudiante.darNombre(), estudiante.darCodigo(), estudiante.darCodigo(), ventanaMain, sistema , estudiante));
            }	
		}
        else if (e.getSource() == botEstado)
        {
            String [] estado = candidaturaGrado.darError().split("\n");
            JList<String> cg = new JList<String>(estado);
            JScrollPane scr = new JScrollPane(cg);
            JOptionPane.showMessageDialog(this, scr, "Detalles Candidatura Grado", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}
