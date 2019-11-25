package eredua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.LoggerKudeatu;

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

				if (!"".equals(idEnp) & !"".equals(izena) & !"".equals(soldata) & !"".equals(altaData) & !"".equals(altaOrdua) & !"".equals(zuzendari) & !"".equals(departamentua) & !"".equals(ardura)) {
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
					
					
				}else {
					ondo = false;
					LoggerKudeatu.idatziLog("Eremuak falta dira.");
				}
				
				if (ondo == true) {
					Enplegatua e1 = new Enplegatua(Integer.parseInt(idEnp), izena, Double.parseDouble(soldata), altaData, altaOrdua,  idDept, idArd, zuzendari);
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
}
