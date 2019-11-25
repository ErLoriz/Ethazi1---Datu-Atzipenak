package eredua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kontroladorea.*;
import javax.swing.JOptionPane;

import kontroladorea.LoggerKudeatu;

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

}
