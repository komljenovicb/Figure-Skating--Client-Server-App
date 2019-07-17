/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import domain.Ucesce;

/**
 *
 * @author pc acer
 */
public class SOZapamtiRezultateNaTakmicenju extends OpstaSO {
    
     private boolean signal = false;

    @Override
    public boolean uspesnost() {
        return true;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        Takmicenje t = (Takmicenje) odo;
        for (Ucesce u : t.getUcesca()) {
            signal = dbb.izmeni((Ucesce)odo);
        }
    }
    
}
