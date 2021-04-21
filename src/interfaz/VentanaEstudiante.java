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
import javax.swing.filechooser.FileFilter;

import Sistema.analizadorArchivo;
import Sistema.systemMain;
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
		
    public VentanaEstudiante(String nombre, String codigo, String carrera, VentanaPrincipal pVentanaMain, systemMain pSistema)
    {
    	ventanaMain = pVentanaMain;
        sistema = pSistema;
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
        reporteNotas = new JButton("Generar reporte de notas");
        editarCurso = new JButton("Editar información de un curso");
        registrarMaterias = new JButton("Registrar materia manualmente");
        candidaturaGrado = new JButton("Verificar candidatura de grado");
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
	


    public File cargarArchivoMain(File archivo)
    {
        return archivo;
    }
}


