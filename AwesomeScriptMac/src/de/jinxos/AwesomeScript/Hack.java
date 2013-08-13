package de.jinxos.AwesomeScript;

import java.util.Properties;

import javax.swing.JOptionPane;

import es.leyenda.AwesomeScript.Start;

public class Hack {

	public String Name;
	public int Size;
	public int Ptr;
	public byte[] Replace;
	public byte[] Original;
	public boolean Enabled;
	
	public Hack(String name, int pointer, int size, byte[] repl)
	{
		Name = name;
		Ptr = pointer;
		Size = size;
		Replace = repl;
	}
	
	// Load hack from .properties file, example: asmac.prop
	public static Hack FromProperties(Properties p, int offset)
	{
		try
		{
			String name = p.getProperty("name"+offset, "broken");
			int size = Integer.parseInt(p.getProperty("size"+offset, "0"));
			int pointer = Integer.parseInt(p.getProperty("ptr"+ 0, "0"));
			byte[] repl = htobs(p.getProperty("repl"+offset, "00"));
			return new Hack(name, pointer, size, repl);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Loading hack " + offset + " failed:\n" + e, "Error", 0);
		}
		return null;
	}
	
	// Convert hex string to byte array for example "AB7C" = 171, 124
	public static byte[] htobs(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	public boolean Toggle() {
		try
		{
			int address = Start.BaseAddress + Ptr;
			System.out.println("Enabled: " + !Enabled);
			System.out.println("Address: 0x" + Integer.toHexString(address));
			if (!Enabled)
			{
				int newmem = MemoryWriter.AllocateBytes(Replace);
				int oldmem = MemoryWriter.AllocateMemory(Size);
				int rm = MemoryWriter.ReadMemory(Start.NautsHandle, address, Size, oldmem);
				System.out.println("ReadMemError: " + MemoryWriter.GetLastError());
				Original = MemoryWriter.GetBytes(oldmem, Size);
				int pm = MemoryWriter.ProtectMemory(Start.NautsHandle, address, Size, MemoryWriter.PAGE_EXECUTE_WRITE);
				System.out.println("ProtMemError: " + MemoryWriter.GetLastError());
				int wm = MemoryWriter.WriteMemory(Start.NautsHandle, address, Size, newmem);
				System.out.println("WriteMemError: " + MemoryWriter.GetLastError());
				System.out.println("Errors: " + rm + " " + pm + " " + wm);
				
			}
			else
			{
				int newmem = MemoryWriter.AllocateBytes(Original);
				int pm = MemoryWriter.ProtectMemory(Start.NautsHandle, address, Size, MemoryWriter.PAGE_EXECUTE_WRITE);
				System.out.println("ProtError: " + MemoryWriter.GetLastError());
				int wm = MemoryWriter.WriteMemory(Start.NautsHandle, address, Size, newmem);
				System.out.println("WriteMemError: " + MemoryWriter.GetLastError());
				System.out.println("Errors: " + pm + " " + wm);
			}
			Enabled = !Enabled;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e, "Error", 0);
		}
		return false;
	}
}
