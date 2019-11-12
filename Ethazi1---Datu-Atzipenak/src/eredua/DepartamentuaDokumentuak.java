package eredua;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import kontroladorea.Ardura;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;

public class DepartamentuaDokumentuak {

	public static final String xmlFilePath = ".\\src\\Departamentuak.xml";

	public static ArrayList<Departamentua> d1 = Kontsultak.DepartamentuakIkusi();

	public static void departamentuaXMLSartu() {

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

			System.out.println("Hola");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
