package eredua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

import kontroladorea.Ardura;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;

public class EnplegatuaDokumentuak {


	public static void enplegatuaXMLSartu() {
		
		final String xmlFilePath = ".\\src\\Enplegatuak.xml";
		
		ArrayList<Enplegatua> e1 = Kontsultak.EnplegatuakIkusi();
		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();
		ArrayList<Ardura> a1 = Kontsultak.ArdurakIkusi();		
		

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("ElorrietaErrekamari");
			document.appendChild(root);

			for (int i = 0; i <= e1.size() - 1; i++) {

				Element enplegatua = document.createElement("Enplegatua");
				root.appendChild(enplegatua);

				Attr attr = document.createAttribute("ID");
				attr.setValue(Integer.toString(e1.get(i).getIdEnplegatua()));
				enplegatua.setAttributeNode(attr);

				Element izena = document.createElement("Izena");
				izena.appendChild(document.createTextNode(e1.get(i).getIzena()));
				enplegatua.appendChild(izena);

				Element soldata = document.createElement("Soldata");
				soldata.appendChild(document.createTextNode(Double.toString(e1.get(i).getSoldata())));
				enplegatua.appendChild(soldata);

				Element altaData = document.createElement("AltaData");
				altaData.appendChild(document.createTextNode(e1.get(i).getAltaData()));
				enplegatua.appendChild(altaData);

				Element altaOrdua = document.createElement("AltaOrdua");
				altaOrdua.appendChild(document.createTextNode(e1.get(i).getAltaOrdua()));
				enplegatua.appendChild(altaOrdua);

				Element zuzendaria = document.createElement("Zuzendaria");
				zuzendaria.appendChild(document.createTextNode(e1.get(i).getZuzendari()));
				enplegatua.appendChild(zuzendaria);

				for (int x = 0; x <= d1.size() - 1; x++) {
					if (d1.get(x).getIdDepartamentua() == e1.get(i).getDepartamentua_idDepartamentua()) {
						Element departamentua = document.createElement("Departamentua");
						departamentua.appendChild(document.createTextNode(d1.get(x).getIzena()));
						enplegatua.appendChild(departamentua);
					}
				}

				for (int x = 0; x <= a1.size() - 1; x++) {
					if (a1.get(x).getIdArdura() == e1.get(i).getArdura_idArdura()) {
						Element ardura = document.createElement("Ardura");
						ardura.appendChild(document.createTextNode(a1.get(x).getIzenArdura()));
						enplegatua.appendChild(ardura);
					}
				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void enplegatuaCSVSartu() {
		
		ArrayList<Enplegatua> e1 = Kontsultak.EnplegatuakIkusi();
		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();
		ArrayList<Ardura> a1 = Kontsultak.ArdurakIkusi();	

		try (PrintWriter writer = new PrintWriter(new File(".\\src\\Enplegatuak.csv"))) {
			StringBuilder sb = new StringBuilder();

			sb.append("ID Enplegatua");
			sb.append(',');
			sb.append("Izena");
			sb.append(',');
			sb.append("Soldata");
			sb.append(',');
			sb.append("Alta data");
			sb.append(',');
			sb.append("Alta ordua");
			sb.append(',');
			sb.append("Zuzendaria");
			sb.append(',');
			sb.append("Departametua");
			sb.append(',');
			sb.append("Ardura");
			sb.append('\n');

			for (int i = 0; i <= e1.size() - 1; i++) {

				sb.append(e1.get(i).getIdEnplegatua());
				sb.append(',');
				sb.append(e1.get(i).getIzena());
				sb.append(',');
				sb.append(e1.get(i).getSoldata());
				sb.append(',');
				sb.append(e1.get(i).getAltaData());
				sb.append(',');
				sb.append(e1.get(i).getAltaOrdua());
				sb.append(',');
				sb.append(e1.get(i).getZuzendari());
				sb.append(',');

				for (int x = 0; x <= d1.size() - 1; x++) {
					if (d1.get(x).getIdDepartamentua() == e1.get(i).getDepartamentua_idDepartamentua()) {
						sb.append(d1.get(x).getIzena());
						sb.append(',');
					}
				}

				for (int x = 0; x <= a1.size() - 1; x++) {
					if (a1.get(x).getIdArdura() == e1.get(i).getArdura_idArdura()) {
						sb.append(a1.get(x).getIzenArdura());
						sb.append('\n');

					}
				}

			}
			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
