/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class SOProveriPodatke extends OpstaSO {

    private Korisnik korisnik;
    Map<String, Object> mapaUslova;
    
    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterumPretrage((Korisnik) odo);
        odo.setMapaUslov(mapaUslova);
        List<OpstiDomenskiObjekat> lista = dbb.vratiSve((Korisnik) odo);
        if (lista.isEmpty()) {
            korisnik = null;
        } else {
            korisnik = (Korisnik)lista.get(0);
        }
    }

    private Map<String, Object> kreirajKriterumPretrage(OpstiDomenskiObjekat odo) {
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_PRONADJI_KORISNIKA, 1);
        return mapaUslova;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

}
