package es.leyenda.AwesomeScript;

import de.jinxos.AwesomeScript.*;

public class Start {

	public static AwesomeScript AS;
	public static UpdateThread PCT;
	public static int NautsHandle;
	
	public static void main(String[] args) {
		// This is your workspace, all the memory stuff is in de.jinxos.AwesomeScript
		if (System.getProperty("sun.arch.data.model") == "32")
			System.loadLibrary("AwesomeScriptNative");
		else
			System.loadLibrary("AwesomeScriptNative64");
		
		AS = new AwesomeScript();
		PCT = new UpdateThread();
		PCT.start();
		AS.setVisible(true);
		while (true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
