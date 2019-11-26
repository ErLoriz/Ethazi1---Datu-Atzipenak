package ikuspegia;

import java.awt.BorderLayout;
import kontroladorea.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import eredua.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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

				try {
					JasperPrint jasperPrint = JasperFillManager.fillReport(".\\src\\DepartamentuaJasper.jasper", null,
							Konexioa.getConnection());
					JRPdfExporter exp = new JRPdfExporter();
					exp.setExporterInput(new SimpleExporterInput(jasperPrint));
					exp.setExporterOutput(new SimpleOutputStreamExporterOutput("departamentuak.pdf"));
					SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
					exp.setConfiguration(conf);
					exp.exportReport();

					JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
					jasperViewer.setVisible(true);

				} catch (Exception e) {
					System.out.println("Error al generar el reporte" + e.getMessage());
				}

				eredua.DepartamentuaDokumentuak.departamentuaXMLSartu();
				eredua.DepartamentuaDokumentuak.departamentuaCSVSartu();
				eredua.DepartamentuaDokumentuak.departamentuaJSONSartu();

			}
		});
		btnDepartamentuakIkusi.setBounds(55, 240, 219, 49);
		contentPane.add(btnDepartamentuakIkusi);

		JButton btnEnplegatuakIkusi = new JButton("Enplegatuak dokumentatu");
		btnEnplegatuakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					JasperPrint jasperPrint = JasperFillManager.fillReport(".\\src\\EnplegatuaJasper.jasper", null,
							Konexioa.getConnection());
					JRPdfExporter exp = new JRPdfExporter();
					exp.setExporterInput(new SimpleExporterInput(jasperPrint));
					exp.setExporterOutput(new SimpleOutputStreamExporterOutput("enplegatuak.pdf"));
					SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
					exp.setConfiguration(conf);
					exp.exportReport();

					JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
					jasperViewer.setVisible(true);

				} catch (Exception e) {
					System.out.println("Error al generar el reporte" + e.getMessage());
				}

				eredua.EnplegatuaDokumentuak.enplegatuaXMLSartu();
				eredua.EnplegatuaDokumentuak.enplegatuaCSVSartu();

			}
		});
		btnEnplegatuakIkusi.setBounds(55, 317, 219, 49);
		contentPane.add(btnEnplegatuakIkusi);

		JButton btnDepartamentuakIrakurri = new JButton("Departamentuak irakurri");
		btnDepartamentuakIrakurri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options1 = { "CSV", "XML", "Ezeztatu" };

				JPanel panel = new JPanel();

				int result = JOptionPane.showOptionDialog(null, panel, "Aukeratu irakurtzeko era.",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
				if (result == JOptionPane.YES_OPTION) {
					DepartamentuaIrakurri.CSVDepartamentuaIrakurri();
				} else if(result == JOptionPane.NO_OPTION) {
					DepartamentuaIrakurri.XMLDepartamentuaIrakurri();
				} 
				


			}
		});
		btnDepartamentuakIrakurri.setBounds(312, 240, 228, 49);
		contentPane.add(btnDepartamentuakIrakurri);

		JButton btnEnplegatuakIrakurri = new JButton("Enplegatuak irakurri");
		btnEnplegatuakIrakurri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] options1 = { "CSV", "XML", "Ezeztatu" };

				JPanel panel = new JPanel();

				int result = JOptionPane.showOptionDialog(null, panel, "Aukeratu irakurtzeko era.",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
				if (result == JOptionPane.YES_OPTION) {
					EnplegatuaIrakurri.CSVEnplegatuaIrakurri();
				} else if(result == JOptionPane.NO_OPTION) {
					EnplegatuaIrakurri.XMLEnplegatuaIrakurri();
				} 
				
				
			}
		});
		btnEnplegatuakIrakurri.setBounds(312, 317, 228, 49);
		contentPane.add(btnEnplegatuakIrakurri);
	}
}
