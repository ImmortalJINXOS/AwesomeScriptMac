package es.leyenda.AwesomeScript;

import javax.swing.JOptionPane;

import de.jinxos.AwesomeScript.*;

public class Start {

	public static AwesomeScript AS;
	public static UpdateThread PCT;
	public static int NautsHandle;
	public static int BaseAddress;
	
	public static void main(String[] args) {
		try
		{
			if (System.getProperty("sun.arch.data.model") == "32")
				System.loadLibrary("AwesomeScriptNative");
			else
				System.loadLibrary("AwesomeScriptNative64");
			
			AS = new AwesomeScript();
			PCT = new UpdateThread();
			PCT.start();
			AS.setVisible(true);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e, "Error", 0);
		}
	}

}
