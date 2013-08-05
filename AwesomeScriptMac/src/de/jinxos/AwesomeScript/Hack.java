package de.jinxos.AwesomeScript;

import java.awt.event.KeyEvent;

public class Hack {

	public String Name;
	public int Key;
	
	public Hack(String name, String key)
	{
		Name = name;
		
		switch (key)
		{
		default:
			Key = key.charAt(0);
		}
	}
	
}
