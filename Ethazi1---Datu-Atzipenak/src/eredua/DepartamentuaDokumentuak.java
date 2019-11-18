package eredua;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import groovy.io.EncodingAwareBufferedWriter;
import kontroladorea.Ardura;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;

public class DepartamentuaDokumentuak {

	public static void departamentuaXMLSartu() {

		final String xmlFilePath = ".\\src\\Departamentuak.xml";
		
		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();
		
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("ElorrietaErrekamari");
			document.appendChild(root);

			for (int i = 0; i <= d1.size() - 1; i++) {

				Element enplegatua = document.createElement("Departamentua");
				root.appendChild(enplegatua);

				Attr attr = document.createAttribute("ID");
				attr.setValue(Integer.toString(d1.get(i).getIdDepartamentua()));
				enplegatua.setAttributeNode(attr);

				Element izena = document.createElement("Izena");
				izena.appendChild(document.createTextNode(d1.get(i).getIzena()));
				enplegatua.appendChild(izena);

				Element kokapena = document.createElement("Kokapena");
				kokapena.appendChild(document.createTextNode(d1.get(i).getKokapena()));
				enplegatua.appendChild(kokapena);

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

	public static void departamentuaCSVSartu() {
		
		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();

		try (PrintWriter writer = new PrintWriter(new File(".\\src\\Departamentuak.csv"))) {
			StringBuilder sb = new StringBuilder();

			sb.append("ID Departamentua");
			sb.append(',');
			sb.append("Izena");
			sb.append(',');
			sb.append("Kokapena");
			sb.append('\n');

			for (int i = 0; i <= d1.size() - 1; i++) {

				sb.append(d1.get(i).getIdDepartamentua());
				sb.append(',');
				sb.append(d1.get(i).getIzena());
				sb.append(',');
				sb.append(d1.get(i).getKokapena());
				sb.append('\n');

			}
			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void departamentuaJSONSartu() {
		
		ArrayList<Enplegatua> e1 = Kontsultak.EnplegatuakIkusi();
		ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();
		ArrayList<Ardura> a1 = Kontsultak.ArdurakIkusi();
		
		JSONObject deptObj = new JSONObject();
		JSONObject enpObj = new JSONObject();
		JSONArray enplegatua = new JSONArray();

		try (FileWriter file = new FileWriter(".\\src\\Departamentuak.json")) {
			file.flush();

			for (int i = 0; i <= d1.size() - 1; i++) {

				deptObj.put("ID Departamentua", d1.get(i).getIdDepartamentua());
				deptObj.put("Deptartamentu izena", d1.get(i).getIzena());
				deptObj.put("Kokapena", d1.get(i).getKokapena());

				for (int j = 0; j <= e1.size() - 1; j++) {
					if (e1.get(j).getDepartamentua_idDepartamentua() == d1.get(i).getIdDepartamentua()) {

						enpObj.put("ID Enplegatua", e1.get(j).getIdEnplegatua());
						enpObj.put("Izena", e1.get(j).getIzena());
						enpObj.put("Soldata", e1.get(j).getSoldata());
						enpObj.put("Alta data", e1.get(j).getAltaData());
						enpObj.put("Alta data", e1.get(j).getAltaOrdua());
						enpObj.put("Zuzendaria", e1.get(j).getZuzendari());
						for (int x = 0; x <= a1.size() - 1; x++) {
							if (a1.get(x).getIdArdura() == e1.get(j).getArdura_idArdura()) {
								enpObj.put("Departamentua", a1.get(x).getIzenArdura());
							}
						}

						enplegatua.add(enpObj);
						deptObj.put("Enplegatua", enplegatua);

					}

				}
				file.write(deptObj.toString());
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
