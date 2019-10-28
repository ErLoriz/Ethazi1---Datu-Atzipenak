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
	private JButton btnArdurakKudeatu = new JButton("Ardurak Kudeatu");

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Menua() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnDepartamentuakKudeatu.setBounds(95, 37, 221, 23);
		contentPane.add(btnDepartamentuakKudeatu);
		
		
		btnEnplegatuakKudeatu.setBounds(95, 111, 221, 23);
		contentPane.add(btnEnplegatuakKudeatu);
		
		
		btnArdurakKudeatu.setBounds(95, 188, 221, 23);
		contentPane.add(btnArdurakKudeatu);
	}

}
