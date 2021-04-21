package interfaz;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public final class FiltroCSV extends FileFilter 
{
	@Override
	public String getDescription()
	{
		return "Archivo CSV";
	}

	@Override
	public boolean accept(File f)
	{
		return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
	}
}
