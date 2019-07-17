/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Drzava;
import domain.OpstiDomenskiObjekat;
import java.util.HashMap;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOPronadjiDrzavu extends OpstaSO {

    private OpstiDomenskiObjekat drzava;
    Map<String, Object> mapaUslova;
    
    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo)
            throws Exception {

        mapaUslova = kreirajKriterijumPretrage((Drzava) odo);
        odo.setMapaUslov(mapaUslova);
        drzava = dbb.pronadji(odo);

    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {

        Drzava d = (Drzava) odo;
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_PRONADJI_DRZAVU, d.getDrzavaID());
        return mapaUslova;

    }

    public OpstiDomenskiObjekat getDrzava() {
        return drzava;
    }

}
