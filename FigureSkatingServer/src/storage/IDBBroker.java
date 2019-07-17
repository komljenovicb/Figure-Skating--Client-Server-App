/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bojana
 */
public interface IDBBroker {
    
    boolean kreiraj(OpstiDomenskiObjekat odo);

    boolean izmeni(OpstiDomenskiObjekat odo);

    boolean obrisi(OpstiDomenskiObjekat odo);

    OpstiDomenskiObjekat pronadji(OpstiDomenskiObjekat odo) throws SQLException;

    List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException;
    
}
