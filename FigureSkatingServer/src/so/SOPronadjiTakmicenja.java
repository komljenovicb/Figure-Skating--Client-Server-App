/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Klizac;
import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOPronadjiTakmicenja extends OpstaSO {

    private List<OpstiDomenskiObjekat> takmicenja;
    Map<String, Object> mapaUslova;

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage((Takmicenje) odo);
        odo.setMapaUslov(mapaUslova);
        takmicenja = dbb.vratiSve(odo);
    }

    private Map<String, Object> kreirajKriterijumPretrage(Takmicenje t) {
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_PRONADJI_TAKMICENJE, t.getNaziv());
        return mapaUslova;
    }

    public List<OpstiDomenskiObjekat> getLista() {
        return takmicenja;
    }

}
