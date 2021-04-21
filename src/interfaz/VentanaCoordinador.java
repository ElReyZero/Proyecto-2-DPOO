package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class VentanaCoordinador extends JPanel
{

    public VentanaCoordinador(String nombre, String codigo, String departamento)
    {
		setLayout(new BorderLayout());
        ///Botones y paneles
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new FlowLayout());
        JLabel name = new JLabel("Nombre: "+ nombre);
        JLabel code = new JLabel("Código: "+ codigo);
        JLabel major = new JLabel("Carrera: "+ departamento);
        panelInformacion.add(name);
        panelInformacion.add(code);
        panelInformacion.add(major);
        add(panelInformacion, BorderLayout.NORTH);
        add(PanelOpcionesCoordinador(),BorderLayout.CENTER);
        add(Volver(),BorderLayout.SOUTH);
        setSize(700, 500);
		setVisible(true);
    }
    public JPanel PanelOpcionesCoordinador()
    {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones,BoxLayout.PAGE_AXIS));
        JButton planearSemestre = new JButton("Planear semestre");
        JButton reporteNotas = new JButton("Generar reporte de notas");
        JButton editarCurso = new JButton("Editar información de un curso");
        JButton candidaturaGrado = new JButton("Verificar candidatura de grado");
        JPanel panelArchivos = PanelArchivosCoordinador();
        JPanel panelBusqueda = PanelBusquedaEstudiante();
        panelOpciones.add(Box.createRigidArea(new Dimension(0,40)));
        panelOpciones.add(panelBusqueda);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,15)));
        panelOpciones.add(panelArchivos);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,20)));
        panelOpciones.add(editarCurso);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(planearSemestre);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(reporteNotas);
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(candidaturaGrado);
        planearSemestre.setAlignmentX(CENTER_ALIGNMENT);
        reporteNotas.setAlignmentX(CENTER_ALIGNMENT);
        editarCurso.setAlignmentX(CENTER_ALIGNMENT);
        candidaturaGrado.setAlignmentX(CENTER_ALIGNMENT);
        return panelOpciones;
    }
    public JPanel PanelArchivosCoordinador()
    {
        JPanel panelCarga = new JPanel();
        panelCarga.setLayout(new BoxLayout(panelCarga,BoxLayout.LINE_AXIS));
        JButton guardarArchivo = new JButton("Guardar archivo");
        JButton cargarArchivo = new JButton("Cargar datos del estudiante");
        panelCarga.add(guardarArchivo);
        panelCarga.add(Box.createRigidArea(new Dimension(10,0)));
        panelCarga.add(cargarArchivo);
        panelCarga.add(Box.createRigidArea(new Dimension(0,50)));
        guardarArchivo.setAlignmentX(CENTER_ALIGNMENT);
        cargarArchivo.setAlignmentX(CENTER_ALIGNMENT);
        return panelCarga;
    }
    public JPanel PanelBusquedaEstudiante()
    {
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda,BoxLayout.PAGE_AXIS));
        panelBusqueda.add(PanelTextoEstudiante());
        panelBusqueda.add(PanelTextoCodigo());
        return panelBusqueda;
    }
    public JPanel PanelTextoEstudiante()
    {
        JPanel panelTextoEstudiante = new JPanel();
        JLabel textEstudiante = new JLabel("Estudiante Actual: ");
        JTextField estudiante = new JTextField();
        panelTextoEstudiante.setLayout(new BoxLayout(panelTextoEstudiante,BoxLayout.LINE_AXIS));
        panelTextoEstudiante.add(textEstudiante);
        panelTextoEstudiante.add(Box.createRigidArea(new Dimension(8,0)));
        panelTextoEstudiante.add(textEstudiante);
        panelTextoEstudiante.add(estudiante);
        return panelTextoEstudiante;
    }
    public JPanel PanelTextoCodigo()
    {
        JPanel panelTextoCodigo = new JPanel();
        JLabel textCodigo = new JLabel("Código: ");
        JTextField codigo = new JTextField();
        panelTextoCodigo.setLayout(new BoxLayout(panelTextoCodigo,BoxLayout.LINE_AXIS));
        panelTextoCodigo.add(textCodigo);
        panelTextoCodigo.add(Box.createRigidArea(new Dimension(0,8)));
        panelTextoCodigo.add(codigo);
        return panelTextoCodigo;
    }
    public JPanel Volver()
    {
      JPanel panelVolver = new JPanel();
      panelVolver.setLayout(new BoxLayout(panelVolver,BoxLayout.LINE_AXIS));
      JButton volver = new JButton("Volver a selección de usuario");
      panelVolver.add(volver);
      return panelVolver;
    }
}