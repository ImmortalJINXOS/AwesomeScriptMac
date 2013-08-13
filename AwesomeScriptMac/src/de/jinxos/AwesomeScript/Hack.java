package de.jinxos.AwesomeScript;

import java.util.Properties;

import javax.swing.JOptionPane;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

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
		Original = new byte[Size];
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
			System.out.println("Enabled: " + !Enabled);
			System.out.println("Error: " + MemoryWriter.GetLastError());
			if (!Enabled)
			{
				Pointer newmem = new Memory(Size);
				Pointer oldmem = new Memory(Size);
				System.out.println("ReadMemory: " + MemoryWriter.ReadMemory(Start.NautsHandle, Ptr + Start.BaseAddress, Size, (int)Pointer.nativeValue(oldmem)));
				System.out.println("Error: " + MemoryWriter.GetLastError());
				oldmem.read(0, Original, 0, Size);
				newmem.write(0, Replace, 0, Size);
				MemoryWriter.ProtectMemory(Start.NautsHandle, Ptr + Start.BaseAddress, Size, MemoryWriter.PAGE_EXECUTE_WRITE);
				System.out.println("WriteMemory: " + MemoryWriter.WriteMemory(Start.NautsHandle, Ptr + Start.BaseAddress, Size, (int)Pointer.nativeValue(newmem)));
				System.out.println("Error: " + MemoryWriter.GetLastError());
			}
			else
			{
				Pointer newmem = new Memory(Size);
				newmem.write(0, Original, 0, Size);
				MemoryWriter.ProtectMemory(Start.NautsHandle, Ptr + Start.BaseAddress, Size, MemoryWriter.PAGE_EXECUTE_WRITE);
				System.out.println("WriteMemory: " + MemoryWriter.WriteMemory(Start.NautsHandle, Ptr + Start.BaseAddress, Size, (int)Pointer.nativeValue(newmem)));
				System.out.println("Error: " + MemoryWriter.GetLastError());
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
