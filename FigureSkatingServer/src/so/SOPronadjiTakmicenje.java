/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import java.util.HashMap;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOPronadjiTakmicenje extends OpstaSO {

    private OpstiDomenskiObjekat takmicenje;
    Map<String, Object> mapaUslova;
    
    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage((Takmicenje) odo);
        odo.setMapaUslov(mapaUslova);
        takmicenje = dbb.pronadji(odo);
    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        Takmicenje t = (Takmicenje) odo;
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_PRONADJI_TAKMICENJE, t.getNaziv());
        return mapaUslova;
    }

    public OpstiDomenskiObjekat getTakmicenje() {
        return takmicenje;
    }

}
