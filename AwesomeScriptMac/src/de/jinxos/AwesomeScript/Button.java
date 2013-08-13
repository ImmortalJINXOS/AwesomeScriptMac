package de.jinxos.AwesomeScript;

public class Button {
	
	int V_KEY;
	boolean PRESSED;
	
	public Button(int vkey)
	{
		V_KEY = vkey;
	}

	public boolean Update()
	{
		if (KeyPress.IsPressed(V_KEY) && !PRESSED)
		{
			return PRESSED = true;
		}
		else if (!KeyPress.IsPressed(V_KEY) && PRESSED)
		{
			return PRESSED = false;
		}
		return false;
	}
}
