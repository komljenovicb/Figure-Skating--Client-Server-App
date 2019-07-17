 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Bojana
 */
public class UcitajParametreZaKonekciju {

    Properties property;
    private static UcitajParametreZaKonekciju instanca;

    private UcitajParametreZaKonekciju() {
        property = new Properties();
        ucitaj();
    }

    public static UcitajParametreZaKonekciju getInstanca() {
        if (instanca == null) {
            instanca = new UcitajParametreZaKonekciju();
        }
        return instanca;
    }

    private void ucitaj() {
        try {
            FileInputStream fis = new FileInputStream("ip.config");
            property.load(fis);
        } catch (Exception ex) {
            System.out.println("Greska prilikom ucitavanja fajla...");
            ex.printStackTrace();
        } 

    }

    public String vratiVrednostZaKljuc(String kljuc) {
        return property.getProperty(kljuc);
    }

}
