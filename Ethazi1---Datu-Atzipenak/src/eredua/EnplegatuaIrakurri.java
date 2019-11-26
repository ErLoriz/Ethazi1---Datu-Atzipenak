package eredua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kontroladorea.Ardura;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.LoggerKudeatu;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnplegatuaIrakurri {

	public static void CSVEnplegatuaIrakurri() {

		// Bariableak

		String idEnp = null;
		String izena = null;
		String soldata = null;
		String altaData = null;
		String altaOrdua = null;
		String zuzendari = null;
		String departamentua = null;
		String ardura = null;
		Boolean ondo = true;
		int idDept = 0;
		int idArd = 0;

		String csvFile = ".\\src\\Enplegatuak.csv";
		BufferedReader br = null;
		String line = "";
		// Se define separador ","
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			// Lehengo linea ez irakurtzeko
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(cvsSplitBy);
				idEnp = datos[0];
				izena = datos[1];
				soldata = datos[2];
				altaData = datos[3];
				altaOrdua = datos[4];
				zuzendari = datos[5];
				departamentua = datos[6];
				ardura = datos[7];

				if (!"".equals(idEnp) & !"".equals(izena) & !"".equals(soldata) & !"".equals(altaData)
						& !"".equals(altaOrdua) & !"".equals(zuzendari) & !"".equals(departamentua)
						& !"".equals(ardura)) {
					try {
						Integer.parseInt(idEnp);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ID enplegatua zenbakia izan behar da", "Txarto",
								JOptionPane.INFORMATION_MESSAGE);
						ondo = false;
						LoggerKudeatu.idatziLog("ID enplegatua zenbakia izan behar da.");
					}
					try {
						Double.parseDouble(soldata);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Soldata zenbakia izan behar da", "Txarto",
								JOptionPane.INFORMATION_MESSAGE);
						ondo = false;
						LoggerKudeatu.idatziLog("Soldata zenbakia izan behar da.");
					}

					idArd = Kontsultak.ArduraIdLortu(ardura);
					idDept = Kontsultak.DepartamentuIdLortu(departamentua);

				} else {
					ondo = false;
					LoggerKudeatu.idatziLog("Eremuak falta dira.");
				}

				if (ondo == true) {
					Enplegatua e1 = new Enplegatua(Integer.parseInt(idEnp), izena, Double.parseDouble(soldata),
							altaData, altaOrdua, idDept, idArd, zuzendari);
					Insertak.sartuErabiltzailea(e1);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void XMLEnplegatuaIrakurri() {

		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();
		ArrayList<Ardura> a1 = Kontsultak.ArdurakIkusi();

		String idEnp = null;
		String izena = null;
		String soldata = null;
		String altaData = null;
		String altaOrdua = null;
		String zuzendari = null;
		int departamentua = 0;
		int ardura = 0;
		
		try {
			File archivo = new File(".\\src\\Enplegatuak.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
			NodeList listaEmpleados = document.getElementsByTagName("Enplegatua");
			for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
				Node nodo = listaEmpleados.item(temp);
				System.out.println("Elemento:" + nodo.getNodeName());
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;

					idEnp = element.getAttribute("ID");
					izena = element.getElementsByTagName("Izena").item(0).getTextContent();
					soldata = element.getElementsByTagName("Soldata").item(0).getTextContent();
					altaData = element.getElementsByTagName("AltaData").item(0).getTextContent();
					altaOrdua = element.getElementsByTagName("AltaOrdua").item(0).getTextContent();
					zuzendari = element.getElementsByTagName("Zuzendaria").item(0).getTextContent();

					for (int x = 0; x <= d1.size() - 1; x++) {
						if (d1.get(x).getIzena() == element.getElementsByTagName("Departamentua").item(0).getTextContent()) {
							departamentua = d1.get(x).getIdDepartamentua();
						}
					}

					for (int x = 0; x <= a1.size() - 1; x++) {
						if (a1.get(x).getIzenArdura() == element.getElementsByTagName("Ardura").item(0).getTextContent()) {
							ardura = a1.get(x).getIdArdura();
						}
					}

					Enplegatua e1 = new Enplegatua(Integer.parseInt(idEnp), izena, Double.parseDouble(soldata),
							altaData, altaOrdua, departamentua, ardura, zuzendari);

					Insertak.sartuErabiltzailea(e1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
