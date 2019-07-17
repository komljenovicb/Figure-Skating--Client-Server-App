/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Klizac;
import domain.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Bojana
 */
public class SOVratiListuKlizaca extends OpstaSO {

    List<OpstiDomenskiObjekat> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        lista = dbb.vratiSve(odo);
    }

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

}
