package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class VentanaEstudiante extends JPanel
{

    public VentanaEstudiante(String nombre, String codigo, String carrera)
    {
		setLayout(new BorderLayout());
        ///Botones y paneles
        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new FlowLayout());
        JLabel name = new JLabel("Nombre: "+ nombre);
        JLabel code = new JLabel("Código: "+ codigo);
        JLabel major = new JLabel("Carrera: "+ carrera);
        panelInformacion.add(name);
        panelInformacion.add(code);
        panelInformacion.add(major);
        add(panelInformacion, BorderLayout.NORTH);
        setSize(700, 500);
		setVisible(true);
    }
}
