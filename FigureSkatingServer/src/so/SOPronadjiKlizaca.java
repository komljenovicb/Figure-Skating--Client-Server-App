/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Klizac;
import domain.OpstiDomenskiObjekat;
import java.util.HashMap;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOPronadjiKlizaca extends OpstaSO {

    private OpstiDomenskiObjekat klizac;
    Map<String, Object> mapaUslova;

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage((Klizac) odo);
        odo.setMapaUslov(mapaUslova);
        klizac = dbb.pronadji(odo);
    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        Klizac k = (Klizac) odo;
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_VRATI_KLIZACA, 11);
        return mapaUslova;
    }

    public OpstiDomenskiObjekat getKlizac() {
        return klizac;
    }
}
