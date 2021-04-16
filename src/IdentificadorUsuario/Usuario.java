package IdentificadorUsuario;

public class Usuario {
	
	// Atributos
	protected String nombre;
	protected String codigo;
	
	
	//Constructor
	
	public Usuario(String pNombre, String pCodigo)
	{
		nombre = pNombre;
		codigo = pCodigo;
	}
	
	//Métodos
	
	public String darNombre()
	{
		return nombre;
		
	}
	
	public String darCodigo()
	{
		return codigo;
		
	}
}
