package ikuspegia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Menua extends JFrame {

	private JPanel contentPane;
	private JButton btnDepartamentuakKudeatu = new JButton("Departamentuak Kudeatu");
	private JButton btnEnplegatuakKudeatu = new JButton("Enplegatuak Kudeatu");

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Menua() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnDepartamentuakKudeatu.setBounds(95, 37, 221, 23);
		contentPane.add(btnDepartamentuakKudeatu);
		
		
		btnEnplegatuakKudeatu.setBounds(95, 111, 221, 23);
		contentPane.add(btnEnplegatuakKudeatu);
	}

}
