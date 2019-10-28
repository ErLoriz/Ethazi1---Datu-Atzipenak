package ikuspegia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class Hasiera extends JFrame {

	private JPanel contentPane;
	private JButton btnOngiEtorri = new JButton("ONGI ETORRI");

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnOngiEtorri.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnOngiEtorri.setBounds(0, 0, 434, 261);
		contentPane.add(btnOngiEtorri);
	}
}
