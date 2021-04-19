package interfaz;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IdentificadorUsuario.Estudiante;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements ActionListener
{
	
	private JButton botonEstudiante;
	private JButton botonCoordinador;
	
	public VentanaPrincipal()
	{
		setTitle("Banner Uniandes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		///Botones y paneles
		JPanel panelBotones = new JPanel();
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.PAGE_AXIS));
		GridBagConstraints c = new GridBagConstraints();
		panelBotones.setLayout(new GridBagLayout());
		botonEstudiante = new JButton ("Estudiante");
		botonCoordinador = new JButton ("Coordinador");
		botonEstudiante.addActionListener(this);
		botonCoordinador.addActionListener(this);
		c.weightx = 2;
		c.weighty = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 75;
		///Labels
		JLabel titleLabel1 = new JLabel("Bienvenido:");
		JLabel titleLabel2 = new JLabel("Seleccione su tipo de Usuario");
		cambiarSizeLabelBold(titleLabel1, panelTitulo);
		cambiarSizeLabelBold(titleLabel2, panelTitulo);
		//adds Principal
		panelTitulo.add(titleLabel1);
		panelTitulo.add(titleLabel2);
		JLabel espacioIzq = new JLabel("      ");
		JLabel espacioCentro = new JLabel("      ");
		JLabel espacioDer = new JLabel("      ");
		panelBotones.add(espacioIzq);
		panelBotones.add(botonEstudiante, c);
		panelBotones.add(espacioCentro);
		panelBotones.add(botonCoordinador, c);
		panelBotones.add(espacioDer);
		add(panelTitulo, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		setSize(700, 500);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new VentanaPrincipal();		
	}
	
	public static void cambiarSizeLabelBold(JLabel label, JComponent component)
	{
		Font f = label.getFont();
		Font font = new Font (f.getName(), Font.BOLD, 25);
		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton boton = (JButton) e.getSource();
		if(boton == botonEstudiante)
		{
			JTextField firstName = new JTextField();
			JTextField codigo =  new JTextField();
			JTextField carrera = new JTextField();
            final JComponent[] inputs = new JComponent[] 
            {
            new JLabel("Introduce tus datos:"),
            new JLabel("Nombre:"),
            firstName,
            new JLabel("Código:"),
            codigo,
            new JLabel("Carrera:"),
            carrera
            };
            int result = JOptionPane.showConfirmDialog(null, inputs, "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION && (codigo.getText().equals(null) ||  firstName.getText().equals(null) || carrera.getText().equals(null)));
            {
            	JOptionPane.showMessageDialog(null, new JLabel("Tienes que completar todos tus datos."), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(boton == botonCoordinador)
		{
			JTextField firstName = new JTextField();
			JTextField codigo =  new JTextField();
			JTextField departamento = new JTextField();
            final JComponent[] inputs = new JComponent[] 
            {
            new JLabel("Introduce tus datos:"),
            new JLabel("Nombre:"),
            firstName,
            new JLabel("Código:"),
            codigo,
            new JLabel("Departamento:"),
            departamento
            };
            int result = JOptionPane.showConfirmDialog(null, inputs, "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION && (codigo.getText().equals(null) ||  firstName.getText().equals(null) || departamento.getText().equals(null)));
            {
            	JOptionPane.showMessageDialog(null, new JLabel("Tienes que completar todos tus datos."), "Error", JOptionPane.ERROR_MESSAGE);
            }
		}
		
	}
	
}
