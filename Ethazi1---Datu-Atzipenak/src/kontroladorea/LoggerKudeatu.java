package kontroladorea;

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
        

        try {  

            // This block configure the logger with handler and formatter  
            fh = new FileHandler(".\\src\\MyLogFile.log",true);  
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

    }
    
}
