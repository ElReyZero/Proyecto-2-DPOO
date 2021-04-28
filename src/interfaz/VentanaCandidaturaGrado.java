package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

    public VentanaCandidaturaGrado(VentanaPrincipal pVentanaMain, systemMain pSistema, Estudiante pEstudiante, Pensum pensum)
    {
        ventanaMain = pVentanaMain;
        sistema = pSistema;
        estudiante = pEstudiante;
		setLayout(new BorderLayout());
        ///Botones y paneles
        add(PanelInformacion(estudiante,pensum), BorderLayout.WEST);
        add(PanelMateriasFaltantes(estudiante),BorderLayout.EAST);
        add(Volver(),BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
    }
    public JPanel PanelInformacion(Estudiante estudiante,Pensum pensum)
    {
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new BoxLayout(panelInformacion,BoxLayout.PAGE_AXIS));
        panelInformacion.add(PanelPGA(estudiante));
        panelInformacion.add(PanelEstadoAcademico(estudiante));
        panelInformacion.add(PanelSemestreSegunCreditos(estudiante));
        panelInformacion.add(Box.createRigidArea(new Dimension(0,30)));
        panelInformacion.add(PanelEstadoCandidaturaGrado(estudiante,pensum));
        return panelInformacion;
    }
    public JPanel PanelPGA(Estudiante estudiante)
    {
        JPanel panelPGA = new JPanel();
        JLabel textPGA = new JLabel("El PGA es de: ");
        JLabel PGA = new JLabel(reporteNotas.promedioPGA(estudiante));
        panelPGA.setLayout(new BoxLayout(panelPGA,BoxLayout.LINE_AXIS));
        panelPGA.add(textPGA);
        panelPGA.add(Box.createRigidArea(new Dimension(8,0)));
        panelPGA.add(PGA);
        return panelPGA;
    }
    public JPanel PanelEstadoAcademico(Estudiante estudiante)
    {
        JPanel panelEA = new JPanel();
        JLabel textEA = new JLabel("El estado académico es: ");
        JLabel ea = new JLabel(reporteNotas.estadoAcademico(estudiante));
        panelEA.setLayout(new BoxLayout(panelEA,BoxLayout.LINE_AXIS));
        panelEA.add(textEA);
        panelEA.add(Box.createRigidArea(new Dimension(8,0)));
        panelEA.add(ea);
        return panelEA;
    }
    public JPanel PanelSemestreSegunCreditos(Estudiante estudiante)
    {
        JPanel panelSSC = new JPanel();
        JLabel textSSC = new JLabel("El semestre según créditos: ");
        JLabel ssc = new JLabel(reporteNotas.semestreSegunCreditos(estudiante));
        panelSSC.setLayout(new BoxLayout(panelSSC,BoxLayout.LINE_AXIS));
        panelSSC.add(textSSC);
        panelSSC.add(Box.createRigidArea(new Dimension(8,0)));
        panelSSC.add(ssc);
        return panelSSC;
    }
    public JPanel PanelEstadoCandidaturaGrado(Estudiante estudiante,Pensum pensum)
    {
        JPanel panelCG = new JPanel();
        JLabel textCG = new JLabel("Estado de candidatura a grado");
        //JLabel cg = new JLabel(candidaturaGrado.darCandidaturaGrado(estudiante, pensum));
        panelCG.setLayout(new BoxLayout(panelCG,BoxLayout.LINE_AXIS));
        panelCG.add(textCG);
        panelCG.add(Box.createRigidArea(new Dimension(8,0)));
        //panelCG.add(cg);
        return panelCG;
    }

    public JPanel PanelMateriasFaltantes(Estudiante estudiante)
    {
        JPanel panelMateriasFaltantes = new JPanel();
        //String[] parts = reporteNotas.darReporteNotas(estudiante).split("\n"); 
        //JList<String> materiasVistas = new JList<String>(parts);
        //JScrollPane scroll = new JScrollPane(materiasVistas);
        //panelMateriasFaltantes.add(scroll);
        return panelMateriasFaltantes;
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
			ventanaMain.actualizarMain(new VentanaEstudiante(estudiante.darNombre(), estudiante.darCodigo(), estudiante.darCodigo(), ventanaMain, sistema , estudiante));
		}
	}
}
