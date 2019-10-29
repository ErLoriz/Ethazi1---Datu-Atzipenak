package ikuspegia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontroladorea.MetodoakIkuspegia;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		btnOngiEtorri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakIkuspegia.pasatuMenura();
				dispose();
			}
		});
		
		btnOngiEtorri.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnOngiEtorri.setBounds(0, 0, 624, 421);
		contentPane.add(btnOngiEtorri);
	}
}
