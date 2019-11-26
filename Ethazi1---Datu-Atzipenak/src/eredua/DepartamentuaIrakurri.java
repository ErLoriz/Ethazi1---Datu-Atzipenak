package eredua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kontroladorea.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DepartamentuaIrakurri {

	public static void CSVDepartamentuaIrakurri() {

		// Bariableak

		String idDept = null;
		String izena = null;
		String kokapena = null;
		Boolean ondo = true;

		String csvFile = ".\\src\\Departamentuak.csv";
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
				idDept = datos[0];
				izena = datos[1];
				kokapena = datos[2];

				if(!"".equals(idDept) & !"".equals(izena) & !"".equals(kokapena)) {
					try {
						Integer.parseInt(idDept);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ID departamentua zenbakia izan behar da", "Txarto",
								JOptionPane.INFORMATION_MESSAGE);
						ondo = false;
						LoggerKudeatu.idatziLog("ID departamentua zenbakia izan behar da.");
					}
				}else {
					ondo = false;
					LoggerKudeatu.idatziLog("Eremuak falta dira.");
				}
				

				if (ondo == true) {
					Departamentua d1 = new Departamentua(Integer.parseInt(idDept), izena, kokapena);
					Insertak.SartuDepartamentua(d1);
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

	public static void XMLDepartamentuaIrakurri() {

		String idDept = null;
		String izena = null;
		String kokapena = null;
		
		try {
			File archivo = new File(".\\src\\Departamentuak.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
			NodeList listaEmpleados = document.getElementsByTagName("Departamentua");
			for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
				Node nodo = listaEmpleados.item(temp);
				System.out.println("Elemento:" + nodo.getNodeName());
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;

					idDept = element.getAttribute("ID");
					izena = element.getElementsByTagName("Izena").item(0).getTextContent();
					kokapena = element.getElementsByTagName("Kokapena").item(0).getTextContent();
					
					Departamentua d1 = new Departamentua(Integer.parseInt(idDept), izena, kokapena);
					Insertak.SartuDepartamentua(d1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
