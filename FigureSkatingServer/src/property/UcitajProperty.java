/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bojana
 */
public class UcitajProperty {

    private Properties properties;
    
    public UcitajProperty() {
        properties = new Properties();
        ucitaj();
    }

    
    private void ucitaj() {
        try {
            FileInputStream fajl
                    = new FileInputStream("db.properties");
            properties.load(fajl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UcitajProperty.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UcitajProperty.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String ucitajVrednostZaKljuc(String kljuc) {
        return properties.getProperty(kljuc);
    }
    
}
