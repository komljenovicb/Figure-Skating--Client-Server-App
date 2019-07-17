/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;

/**
 *
 * @author Bojana
 */
public class SOKreirajTakmicenje extends OpstaSO {

    private boolean signal = false;

    @Override
    public boolean uspesnost() {
        return true;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        signal = dbb.kreiraj(odo);
    }

}
