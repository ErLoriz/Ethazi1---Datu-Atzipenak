package eredua;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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

public class EnplegatuaXML {
	public static final String xmlFilePath = "C:\\Users\\Admin1\\git\\Ethazi1---Datu-Atzipenak\\Ethazi1---Datu-Atzipenak\\src\\Enplegatuak.xml";

	public static void enplegatuaSartu() {

		ArrayList<Enplegatua> e1 = new ArrayList();
		ArrayList<Departamentua> d1 = new ArrayList();
		ArrayList<Ardura> a1 = new ArrayList();

		e1 = Kontsultak.EnplegatuakIkusi();
		d1 = Kontsultak.DepartamentuakIkusi();
		a1 = Kontsultak.ArdurakIkusi();

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("ElorrietaErrekamari");
			document.appendChild(root);

			for (int i = 0; i <= e1.size() - 1; i++) {

				// employee element
				Element enplegatua = document.createElement("Enplegatua");
				root.appendChild(enplegatua);

				// set an attribute to staff element
				Attr attr = document.createAttribute("ID");
				attr.setValue(Integer.toString(e1.get(i).getIdEnplegatua()));
				enplegatua.setAttributeNode(attr);

				// you can also use staff.setAttribute("id", "1") for this

				// firstname element
				Element izena = document.createElement("Izena");
				izena.appendChild(document.createTextNode(e1.get(i).getIzena()));
				enplegatua.appendChild(izena);

				// lastname element
				Element soldata = document.createElement("Soldata");
				soldata.appendChild(document.createTextNode(Double.toString(e1.get(i).getSoldata())));
				enplegatua.appendChild(soldata);

				// email element
				Element altaData = document.createElement("AltaData");
				altaData.appendChild(document.createTextNode(e1.get(i).getAltaData()));
				enplegatua.appendChild(altaData);

				// department elements
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
