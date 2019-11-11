package eredua;

import java.io.File;
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

public class DepartamentuaXML {

	public static final String xmlFilePath = "C:\\Users\\Admin1\\git\\Ethazi1---Datu-Atzipenak\\Ethazi1---Datu-Atzipenak\\src\\Departamentuak.xml";

	public static void departamentuaSartu() {

		ArrayList<Departamentua> d1 = new ArrayList();

		d1 = Kontsultak.DepartamentuakIkusi();

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("ElorrietaErrekamari");
			document.appendChild(root);

			for (int i = 0; i <= d1.size() - 1; i++) {

				// employee element
				Element enplegatua = document.createElement("Departamentua");
				root.appendChild(enplegatua);

				// set an attribute to staff element
				Attr attr = document.createAttribute("ID");
				attr.setValue(Integer.toString(d1.get(i).getIdDepartamentua()));
				enplegatua.setAttributeNode(attr);

				// you can also use staff.setAttribute("id", "1") for this

				// firstname element
				Element izena = document.createElement("Izena");
				izena.appendChild(document.createTextNode(d1.get(i).getIzena()));
				enplegatua.appendChild(izena);

				// lastname element
				Element kokapena = document.createElement("Kokapena");
				kokapena.appendChild(document.createTextNode(d1.get(i).getKokapena()));
				enplegatua.appendChild(kokapena);


			}
			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
