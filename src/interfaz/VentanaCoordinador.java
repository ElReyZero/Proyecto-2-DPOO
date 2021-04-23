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
import javax.swing.JPanel;

import Sistema.systemMain;

@SuppressWarnings("serial")
public class VentanaCoordinador extends JPanel implements ActionListener
{

    private JLabel name;
    private JLabel code;
    private JLabel department;
    private JButton planearSemestre;
    private JButton reporteNotas;
    private JButton editarCurso;
    private JButton candidaturaGrado;
    private JButton cargarPensum;
    private JButton guardarArchivo;
    private JButton cargarArchivo;
    private JButton validarRequisitos;
    private JButton volver;
    private VentanaPrincipal ventanaMain;
    private systemMain sistema;

    public VentanaCoordinador(String nombre, String codigo, String departamento, VentanaPrincipal pVentanaMain, systemMain pSistema)
    {
        ventanaMain = pVentanaMain;
        sistema = pSistema;
		setLayout(new BorderLayout());
        ///Botones y paneles
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new FlowLayout());
        name = new JLabel("Nombre: "+ nombre);
        code = new JLabel("Código: "+ codigo);
        department = new JLabel("Departamento: "+ departamento);
        panelInformacion.add(name);
        panelInformacion.add(code);
        panelInformacion.add(department);
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
        planearSemestre = new JButton("Planear semestre");
        reporteNotas = new JButton("Generar reporte de notas");
        editarCurso = new JButton("Editar información de un curso");
        candidaturaGrado = new JButton("Verificar candidatura de grado");
        validarRequisitos = new JButton("Validar requisitos");
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
        panelOpciones.add(Box.createRigidArea(new Dimension(0,8)));
        panelOpciones.add(validarRequisitos);
        planearSemestre.setAlignmentX(CENTER_ALIGNMENT);
        reporteNotas.setAlignmentX(CENTER_ALIGNMENT);
        editarCurso.setAlignmentX(CENTER_ALIGNMENT);
        candidaturaGrado.setAlignmentX(CENTER_ALIGNMENT);
        validarRequisitos.setAlignmentX(CENTER_ALIGNMENT);
        return panelOpciones;
    }
    public JPanel PanelArchivosCoordinador()
    {
        JPanel panelCarga = new JPanel();
        panelCarga.setLayout(new BoxLayout(panelCarga,BoxLayout.LINE_AXIS));
        cargarPensum = new JButton("Cargar Pensum");
        cargarPensum.addActionListener(this);
        guardarArchivo = new JButton("Guardar archivo");
        guardarArchivo.addActionListener(this);
        cargarArchivo = new JButton("Cargar datos del estudiante");
        cargarArchivo.addActionListener(this);
        panelCarga.add(cargarPensum);
        panelCarga.add(Box.createRigidArea(new Dimension(10,0)));
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
        JLabel estudiante = new JLabel();
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
        JLabel codigo = new JLabel();
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
        else if(e.getSource() == cargarPensum)
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
            }           
        }
		
	}
}