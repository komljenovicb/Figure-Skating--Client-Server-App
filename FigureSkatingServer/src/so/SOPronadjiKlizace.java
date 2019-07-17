/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Klizac;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOPronadjiKlizace extends OpstaSO {

    private List<OpstiDomenskiObjekat> klizaci;
    Map<String, Object> mapaUslova;
    
    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage((Klizac) odo);
        odo.setMapaUslov(mapaUslova);
        klizaci = dbb.vratiSve(odo);
    }
    
    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_VRATI_KLIZACA, 11);
        return mapaUslova;
    }

    public List<OpstiDomenskiObjekat> getLista() {
        return klizaci;
    }

}
