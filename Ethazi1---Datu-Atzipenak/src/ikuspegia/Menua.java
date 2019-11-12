package ikuspegia;

import java.awt.BorderLayout;
import kontroladorea.*;
import eredua.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnDepartamentuakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MetodoakIkuspegia.pasatuDepartamentura();
				dispose();
			}
		});
		
		
		btnDepartamentuakKudeatu.setBounds(156, 52, 282, 49);
		contentPane.add(btnDepartamentuakKudeatu);
		btnEnplegatuakKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MetodoakIkuspegia.pasatuEnplegatuetara();
				dispose();
			}
		});
		
		
		btnEnplegatuakKudeatu.setBounds(156, 147, 282, 49);
		contentPane.add(btnEnplegatuakKudeatu);
		
		JButton btnDepartamentuakIkusi = new JButton("Departamentuak dokumentatu\r\n");
		btnDepartamentuakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				eredua.DepartamentuaDokumentuak.departamentuaXMLSartu();
				eredua.DepartamentuaDokumentuak.departamentuaCSVSartu();
				
			}
		});
		btnDepartamentuakIkusi.setBounds(156, 239, 282, 49);
		contentPane.add(btnDepartamentuakIkusi);
		
		JButton btnEnplegatuakIkusi = new JButton("Enplegatuak dokumentatu");
		btnEnplegatuakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				eredua.EnplegatuaDokumentuak.enplegatuaXMLSartu();
				eredua.EnplegatuaDokumentuak.enplegatuaCSVSartu();
				
			}
		});
		btnEnplegatuakIkusi.setBounds(156, 325, 282, 49);
		contentPane.add(btnEnplegatuakIkusi);
	}
}
