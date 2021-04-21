package interfaz;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
	
	private JPanel main;
	private JButton botonEstudiante;
	private JButton botonCoordinador;
	
	public VentanaPrincipal()
	{
	
		setTitle("Banner Uniandes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		///Botones y paneles
		main = new JPanel();
		JPanel panelBotones = new JPanel();
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.PAGE_AXIS));
		main.setLayout(new BorderLayout());
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
		JLabel titleLabel1 = new JLabel("Bienvenido a Banner:");
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
		main.add(panelTitulo, BorderLayout.NORTH);
		main.add(panelBotones, BorderLayout.CENTER);
		add(main);
		setSize(700, 500);
		setVisible(true);
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
            if(result == JOptionPane.OK_OPTION && (codigo.getText().equals("") ||  firstName.getText().equals("") || carrera.getText().equals("")))
            {
            	JOptionPane.showMessageDialog(null, new JLabel("Tienes que completar todos tus datos."), "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				actualizarMain(new VentanaEstudiante(firstName.getText(), codigo.getText(), carrera.getText(), this));
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
            if(result == JOptionPane.OK_OPTION && (codigo.getText().equals("") ||  firstName.getText().equals("") || departamento.getText().equals("")))
            {
            	JOptionPane.showMessageDialog(null, new JLabel("Tienes que completar todos tus datos."), "Error", JOptionPane.ERROR_MESSAGE);
            }
			else
			{
				actualizarMain(new VentanaCoordinador(firstName.getText(), codigo.getText(), departamento.getText()));
			}
		}
		
	}


	public void actualizarMain(JPanel panel)
	{
		main.removeAll();
		main.add(panel);
		add(main);
		validate();
		repaint();
	}

	public void resetMain()
	{
		main.removeAll();
		JPanel panelBotones = new JPanel();
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.PAGE_AXIS));
		main.setLayout(new BorderLayout());
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
		JLabel titleLabel1 = new JLabel("Bienvenido a Banner:");
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
		main.add(panelTitulo, BorderLayout.NORTH);
		main.add(panelBotones, BorderLayout.CENTER);
		validate();
		repaint();
	}

}
