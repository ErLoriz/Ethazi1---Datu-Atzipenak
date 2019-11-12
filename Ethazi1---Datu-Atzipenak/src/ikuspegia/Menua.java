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
import javax.swing.JPanel;
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
				JasperPrint jasperPrint = JasperFillManager.fillReport(".\\src\\DepartamentuaJasper.jasper", null,DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
				JRPdfExporter exp = new JRPdfExporter();
				exp.setExporterInput(new SimpleExporterInput(jasperPrint));
				exp.setExporterOutput(new SimpleOutputStreamExporterOutput("departamentuak.pdf"));
				SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
				exp.setConfiguration(conf);
				exp.exportReport();

				// se muestra en una ventana aparte para su descarga
				JasperPrint jasperPrintWindow = JasperFillManager.fillReport(
				".\\src\\EnplegatuaJasper.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
				JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
				jasperViewer.setVisible(true);
				
				//ENPLEGATUA
				
				JasperPrint jasperPrint2 = JasperFillManager.fillReport(".\\src\\DepartamentuaJasper.jasper", null,DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
				JRPdfExporter exp2 = new JRPdfExporter();
				exp2.setExporterInput(new SimpleExporterInput(jasperPrint));
				exp2.setExporterOutput(new SimpleOutputStreamExporterOutput("departamentuak.pdf"));
				SimplePdfExporterConfiguration conf2 = new SimplePdfExporterConfiguration();
				exp2.setConfiguration(conf2);
				exp2.exportReport();

				// se muestra en una ventana aparte para su descarga
				JasperPrint jasperPrintWindow2 = JasperFillManager.fillReport(
				".\\src\\DepartamentuaJasper.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
				JasperViewer jasperViewer2 = new JasperViewer(jasperPrintWindow2);
				jasperViewer2.setVisible(true);
				} catch (Exception e) {
					System.out.println("Error al generar el reporte" + e.getMessage());
				}
				
				eredua.DepartamentuaDokumentuak.departamentuaXMLSartu();
				eredua.DepartamentuaDokumentuak.departamentuaCSVSartu();
				
			}
		});
		btnDepartamentuakIkusi.setBounds(156, 239, 282, 49);
		contentPane.add(btnDepartamentuakIkusi);
		
		JButton btnEnplegatuakIkusi = new JButton("Enplegatuak dokumentatu");
		btnEnplegatuakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					JasperPrint jasperPrint = JasperFillManager.fillReport(".\\src\\EnplegatuaJasper.jasper", null,DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
					JRPdfExporter exp = new JRPdfExporter();
					exp.setExporterInput(new SimpleExporterInput(jasperPrint));
					exp.setExporterOutput(new SimpleOutputStreamExporterOutput("enplegatuak.pdf"));
					SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
					exp.setConfiguration(conf);
					exp.exportReport();

					// se muestra en una ventana aparte para su descarga
					JasperPrint jasperPrintWindow = JasperFillManager.fillReport(
					".\\src\\EnplegatuaJasper.jasper", null,
					DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
					JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
					jasperViewer.setVisible(true);
					
					//ENPLEGATUA
					
					JasperPrint jasperPrint2 = JasperFillManager.fillReport(".\\src\\EnplegatuaJasper.jasper", null,DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
					JRPdfExporter exp2 = new JRPdfExporter();
					exp2.setExporterInput(new SimpleExporterInput(jasperPrint));
					exp2.setExporterOutput(new SimpleOutputStreamExporterOutput("enplegatuak.pdf"));
					SimplePdfExporterConfiguration conf2 = new SimplePdfExporterConfiguration();
					exp2.setConfiguration(conf2);
					exp2.exportReport();

					// se muestra en una ventana aparte para su descarga
					JasperPrint jasperPrintWindow2 = JasperFillManager.fillReport(
					".\\src\\EnplegatuaJasper.jasper", null,
					DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", ""));
					JasperViewer jasperViewer2 = new JasperViewer(jasperPrintWindow2);
					jasperViewer2.setVisible(true);
					} catch (Exception e) {
						System.out.println("Error al generar el reporte" + e.getMessage());
					}
				
				eredua.EnplegatuaDokumentuak.enplegatuaXMLSartu();
				eredua.EnplegatuaDokumentuak.enplegatuaCSVSartu();
				
			}
		});
		btnEnplegatuakIkusi.setBounds(156, 325, 282, 49);
		contentPane.add(btnEnplegatuakIkusi);
	}
}
