package logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerKudeatu {

	
    public static void idatziLog(String mezua) {  

        Logger logger = Logger.getLogger("MyLog");  
        FileHandler fh;
        String cadena="";
        

        try {  

            // This block configure the logger with handler and formatter  
            fh = new FileHandler(".\\Ethazi1---Datu-Atzipenak/src\\MyLogFile.log");  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

            // the following statement is used to log any messages  
            logger.info(mezua);  

        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        cadena = irakurriLogger();
        idatziLogTxt(cadena);

    }
    
    private static void idatziLogTxt(String mezua) {
		String sFichero = ".\\Ethazi1---Datu-Atzipenak/src\\LogTxt";
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			File file = new File(sFichero);
			// Fitxeroa gainetik ez idazteko
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			bw.write(mezua);
			bw.newLine();

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
    private static String irakurriLogger() {

		String cadena="";
		String cadena2="";
		try(BufferedReader br= new BufferedReader(new FileReader(".\\Ethazi1---Datu-Atzipenak/src\\MyLogFile.log"))) {
			while((cadena = br.readLine())!=null) {
				if(!"".equals(cadena)) {
					System.out.println(cadena);
					cadena2 += cadena + "\n";
					}
				else {
					System.out.println("");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cadena2;
	}
}
