package de.jinxos.AwesomeScript;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import es.leyenda.AwesomeScript.Start;

public class UpdateThread extends Thread {
	
	public static Button[] Buttons = new Button[] { new Button(KeyEvent.VK_F1), new Button(KeyEvent.VK_F2), new Button(KeyEvent.VK_F3), new Button(KeyEvent.VK_F4), new Button(KeyEvent.VK_F5) };
	public static Hack[] Hacks = new Hack[] {
			new Hack("No Cooldown", 2215872, 3, new byte[] {(byte) 0xD9, (byte) 0xEE, (byte) 0x90}),
			new Hack("No Charge", 2200085, 6, new byte[] {(byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0x90}),
			new Hack("No Increase", 1484635, 1, new byte[] {(byte) 0x90}),
			new Hack("Megataunt", 1306203, 3, new byte[] {(byte) 0x90, (byte) 0x90, (byte) 0x90}),
			new Hack("No Bot AI", 2723482, 3, new byte[] {(byte) 0x90, (byte) 0x90, (byte) 0x90})
	};
	
	public void run()
	{
		while (true)
		{
			try {
				int IsRunning = ProcessChecker.IsRunning("Awesomenauts");
				
				if (IsRunning != 0 && !Start.AS.btnToggleMegataunt.isEnabled())
				{
					Start.AS.chkbxNautsRunning.setSelected(true);
					Start.AS.btnToggleNoCooldown.setEnabled(true);
					Start.AS.btnToggleMegataunt.setEnabled(true);
					Start.AS.btnToggleNoBotAI.setEnabled(true);
					Start.AS.btnToggleNoCharge.setEnabled(true);
					Start.AS.btnToggleNoIncrease.setEnabled(true);
					Start.NautsHandle = MemoryWriter.OpenTask(IsRunning);
					while (Start.BaseAddress < 0x100000 || Start.BaseAddress > 0xBFFFFF)
						Start.BaseAddress = MemoryWriter.GetBaseAddress(Start.NautsHandle);
					System.out.println("Base Address: 0x" + Integer.toHexString(Start.BaseAddress));
				}
				else if (IsRunning == 0 && Start.AS.btnToggleMegataunt.isEnabled())
				{
					Start.AS.chkbxNautsRunning.setSelected(false);
					Start.AS.btnToggleNoCooldown.setEnabled(false);
					Start.AS.btnToggleMegataunt.setEnabled(false);
					Start.AS.btnToggleNoBotAI.setEnabled(false);
					Start.AS.btnToggleNoCharge.setEnabled(false);
					Start.AS.btnToggleNoIncrease.setEnabled(false);
					MemoryWriter.CloseTask(Start.NautsHandle);
					for (Hack h : Hacks) h.Enabled = false;
				}
				
				for (int i = 0; i < Hacks.length; i++)
				{
					if (Buttons[i].Update() && IsRunning != 0)
					{
						Hacks[i].Toggle();
					}
					
					switch (i)
					{
					case 0:
						Start.AS.lbl1.setText(Hacks[i].Enabled ? "On" : "Off");
						Start.AS.lbl1.setForeground(Hacks[i].Enabled ? Color.green : Color.red);
						break;
					case 1:
						Start.AS.lbl2.setText(Hacks[i].Enabled ? "On" : "Off");
						Start.AS.lbl2.setForeground(Hacks[i].Enabled ? Color.green : Color.red);
						break;
					case 2:
						Start.AS.lbl3.setText(Hacks[i].Enabled ? "On" : "Off");
						Start.AS.lbl3.setForeground(Hacks[i].Enabled ? Color.green : Color.red);
						break;
					case 3:
						Start.AS.lbl4.setText(Hacks[i].Enabled ? "On" : "Off");
						Start.AS.lbl4.setForeground(Hacks[i].Enabled ? Color.green : Color.red);
						break;
					case 4:
						Start.AS.lbl5.setText(Hacks[i].Enabled ? "On" : "Off");
						Start.AS.lbl5.setForeground(Hacks[i].Enabled ? Color.green : Color.red);
						break;
					}
				}
				Thread.sleep(20);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Error", 0);
			}
		}
		
	}

}
