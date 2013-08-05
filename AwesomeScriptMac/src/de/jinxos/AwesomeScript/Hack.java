package de.jinxos.AwesomeScript;

import java.util.Properties;
import javax.swing.JOptionPane;
import com.sun.jna.*;

public class Hack {

	public String Name;
	public int Key;
	public int Size;
	public int Pointer;
	public byte[] Replace;
	public byte[] Original;
	
	private Hack(String name, int pointer, String key, int size, byte[] repl)
	{
		Name = name;
		Pointer = pointer;
		Key = key.charAt(0);
		Size = size;
		Replace = repl;
		Original = new byte[Size];
	}
	
	// Load hack from .properties file, example: asmac.prop
	public static Hack FromProperties(Properties p, int offset)
	{
		try
		{
			String name = p.getProperty("name"+offset, "broken");
			String key = p.getProperty("key"+offset, "0");
			int size = Integer.parseInt(p.getProperty("size"+offset, "0"));
			int pointer = Integer.parseInt(p.getProperty("ptr"+ 0, "0"));
			byte[] repl = htobs(p.getProperty("repl"+offset, "00"));
			return new Hack(name, pointer, key, size, repl);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Loading hack " + offset + " failed:\n" + e);
		}
		return null;
	}
	
	// Convert hex string to byte array for example AB7C = 171, 124
	public static byte[] htobs(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
