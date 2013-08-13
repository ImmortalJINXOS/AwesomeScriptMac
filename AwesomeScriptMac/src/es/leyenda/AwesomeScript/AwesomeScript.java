package es.leyenda.AwesomeScript;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import de.jinxos.AwesomeScript.UpdateThread;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AwesomeScript extends JFrame {

	/**
	 * AwesomeScript UID
	 */
	private static final long serialVersionUID = 1937302578303040043L;
	
	private JPanel contentPane;
	public JCheckBox chkbxNautsRunning;
	public JButton btnToggleNoCooldown;
	public JButton btnToggleNoCharge;
	public JButton btnToggleNoIncrease;
	public JButton btnToggleMegataunt;
	public JButton btnToggleNoBotAI;
	public JLabel lbl1;
	public JLabel lbl2;
	public JLabel lbl3;
	public JLabel lbl4;
	public JLabel lbl5;

	/**
	 * Create the frame.
	 */
	public AwesomeScript() {
		setTitle("AwesomeScript Mac");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToAwesomescript = new JLabel("Welcome to AwesomeScript Mac Edition\r\n");
		lblWelcomeToAwesomescript.setBounds(10, 11, 249, 21);
		contentPane.add(lblWelcomeToAwesomescript);
		
		JLabel lblPleaseConsiderThat = new JLabel("Please consider that this is just a test version");
		lblPleaseConsiderThat.setBounds(10, 30, 276, 14);
		contentPane.add(lblPleaseConsiderThat);
		
		chkbxNautsRunning = new JCheckBox("Awesomenauts running");
		chkbxNautsRunning.setEnabled(false);
		chkbxNautsRunning.setBounds(10, 51, 160, 24);
		contentPane.add(chkbxNautsRunning);
		
		JLabel lblNoCooldown = new JLabel("No Cooldown");
		lblNoCooldown.setBounds(20, 82, 81, 16);
		contentPane.add(lblNoCooldown);
		
		lbl1 = new JLabel("Off");
		lbl1.setForeground(Color.RED);
		lbl1.setBounds(111, 82, 23, 16);
		contentPane.add(lbl1);
		
		btnToggleNoCooldown = new JButton("Toggle (F1)");
		btnToggleNoCooldown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateThread.Hacks[0].Toggle();
			}
		});
		btnToggleNoCooldown.setEnabled(false);
		btnToggleNoCooldown.setBounds(146, 77, 98, 26);
		contentPane.add(btnToggleNoCooldown);
		
		btnToggleNoCharge = new JButton("Toggle (F2)");
		btnToggleNoCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateThread.Hacks[1].Toggle();
			}
		});
		btnToggleNoCharge.setEnabled(false);
		btnToggleNoCharge.setBounds(146, 104, 98, 26);
		contentPane.add(btnToggleNoCharge);
		
		lbl2 = new JLabel("Off");
		lbl2.setForeground(Color.RED);
		lbl2.setBounds(111, 109, 23, 16);
		contentPane.add(lbl2);
		
		JLabel lblNoCharge = new JLabel("No Charge");
		lblNoCharge.setBounds(20, 109, 81, 16);
		contentPane.add(lblNoCharge);
		
		btnToggleNoIncrease = new JButton("Toggle (F3)");
		btnToggleNoIncrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateThread.Hacks[2].Toggle();
			}
		});
		btnToggleNoIncrease.setEnabled(false);
		btnToggleNoIncrease.setBounds(146, 130, 98, 26);
		contentPane.add(btnToggleNoIncrease);
		
		lbl3 = new JLabel("Off");
		lbl3.setForeground(Color.RED);
		lbl3.setBounds(111, 135, 23, 16);
		contentPane.add(lbl3);
		
		JLabel lblNoIncrease = new JLabel("No Increase");
		lblNoIncrease.setBounds(20, 135, 81, 16);
		contentPane.add(lblNoIncrease);
		
		btnToggleMegataunt = new JButton("Toggle (F4)");
		btnToggleMegataunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateThread.Hacks[3].Toggle();
			}
		});
		btnToggleMegataunt.setEnabled(false);
		btnToggleMegataunt.setBounds(146, 156, 98, 26);
		contentPane.add(btnToggleMegataunt);
		
		lbl4 = new JLabel("Off");
		lbl4.setForeground(Color.RED);
		lbl4.setBounds(111, 161, 23, 16);
		contentPane.add(lbl4);
		
		JLabel lblMegataunt = new JLabel("Megataunt");
		lblMegataunt.setBounds(20, 161, 81, 16);
		contentPane.add(lblMegataunt);
		
		btnToggleNoBotAI = new JButton("Toggle (F5)");
		btnToggleNoBotAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateThread.Hacks[4].Toggle();
			}
		});
		btnToggleNoBotAI.setEnabled(false);
		btnToggleNoBotAI.setBounds(146, 182, 98, 26);
		contentPane.add(btnToggleNoBotAI);
		
		lbl5 = new JLabel("Off");
		lbl5.setForeground(Color.RED);
		lbl5.setBounds(111, 187, 23, 16);
		contentPane.add(lbl5);
		
		JLabel lblNoBotAi = new JLabel("No Bot AI");
		lblNoBotAi.setBounds(20, 187, 81, 16);
		contentPane.add(lblNoBotAi);
	}
}


