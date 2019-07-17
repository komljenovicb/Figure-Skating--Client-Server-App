/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.OpstiDomenskiObjekat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import storage.IDBBroker;
import storage.database.connection.DBConnection;

/**
 *
 * @author Bojana
 */
public class DBBroker implements IDBBroker {


    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "select * from " + odo.vratiFrom() + " " + odo.vratiUslov() + " " + odo.uslovZaSortiranje();
        System.out.println(upit);
        Statement st = DBConnection.getInstanca().getKonekcija().createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.vratiListu(rs);
    }


    @Override
    public boolean izmeni(OpstiDomenskiObjekat odo) {
        try {
            String upit = "update " + odo.vratiNazivTabele()
                    + " set " + odo.vratiVrednostiAtributaZaSet()
                    + " " + odo.vratiUslovZaSet();
            System.out.println(upit);
            Statement stat = DBConnection.getInstanca().getKonekcija().createStatement();
            stat.executeUpdate(upit);
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Objekat " + odo.vratiNazivTabele()
                    + " ne može biti ažuriran!\n" + ex.getMessage());
            return false;
        }
        System.out.println("Objekat " + odo.vratiNazivTabele()
                + " je uspešno ažuriran!");
        return true;
    }

    @Override
    public boolean kreiraj(OpstiDomenskiObjekat odo) {
        try {
            String upit = "insert into " + odo.vratiNazivTabele()
                    + " values " + odo.vratiVrednostiAtributa();

            System.out.println(upit);
            Statement stat = DBConnection.getInstanca().getKonekcija().createStatement();
            stat.executeUpdate(upit);
        } catch (SQLException ex) {
            System.out.println("Objekat " + odo.vratiNazivTabele()
                    + " ne može biti kreiran! \n" + ex.getMessage());
            return false;
        }
        System.out.println("Objekat " + odo.vratiNazivTabele()
                + " je kreiran!");
        return true;
    }

    @Override
    public OpstiDomenskiObjekat pronadji(OpstiDomenskiObjekat odo) throws SQLException {
        ResultSet rs;
        String upit = "select *" + " from " + odo.vratiFrom() + " " + odo.vratiUslov();
        System.out.println(upit);
        Statement stat = DBConnection.getInstanca().getKonekcija().createStatement();
        rs = stat.executeQuery(upit);
        odo = odo.vratiListu(rs).get(0);
        stat.close();
        return odo;
    }

    // 5
    @Override
    public boolean obrisi(OpstiDomenskiObjekat odo) {

        try {
            String upit = "delete from " + odo.vratiNazivTabele() + " " + odo.vratiUslov();
            System.out.println(upit);
            Statement stat = DBConnection.getInstanca().getKonekcija().createStatement();
            stat.executeUpdate(upit);
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Objekat " + odo.vratiNazivTabele()
                    + " nije obrisan!\n" + ex.getMessage());
            return false;
        }
        System.out.println("Objekat " + odo.vratiNazivTabele()
                + " je obrisan!");
        return true;
    }

}
