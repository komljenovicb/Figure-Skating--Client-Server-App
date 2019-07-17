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
public class SOIzmeniKlizaca extends OpstaSO {

    Map<String, Object> mapaUslova;
    private boolean signal = false;

    @Override
    public boolean uspesnost() {
        return true;
    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        Klizac klizac = (Klizac) odo;
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_VRATI_KLIZACA, 12);
        return mapaUslova;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage((Klizac) odo);
        odo.setMapaUslov(mapaUslova);
        signal = dbb.izmeni(odo);
    }

}
