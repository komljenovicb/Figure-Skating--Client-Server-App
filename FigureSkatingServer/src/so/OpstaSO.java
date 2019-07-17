/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import java.util.ArrayList;
import storage.IDBBroker;
import storage.database.DBBroker;
import storage.database.connection.DBConnection;

/**
 *
 * @author Bojana
 */
public abstract class OpstaSO {

    protected IDBBroker dbb;

    public OpstaSO() {
        this.dbb = new DBBroker();
    }

    public void izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            DBConnection.getInstanca().otvoriKonekciju();
            proveriPreduslov(odo);
            izvrsiKonkretnuOperaciju(odo);
            DBConnection.getInstanca().commit();
            DBConnection.getInstanca().zatvoriKonekciju();
        } catch (Exception ex) {
            DBConnection.getInstanca().rollback();
            DBConnection.getInstanca().zatvoriKonekciju();
            ex.printStackTrace();
            throw new Exception("Greška prilikom izvrsenja so" + ex.getMessage());
        }

    }

    public boolean uspesnost() {
        return false;
    }

    protected void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }

    protected abstract void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception;

}
