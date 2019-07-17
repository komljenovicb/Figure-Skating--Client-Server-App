/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import property.Konstante;
import property.UcitajProperty;

/**
 *
 * @author Bojana
 */
public class DBConnection {

    private Connection konekcija;
    private UcitajProperty property;
    private static DBConnection instanca;

    private DBConnection() throws SQLException {
        property = new UcitajProperty();
    }

    public static DBConnection getInstanca() throws SQLException {
        if (instanca == null) {
            instanca = new DBConnection();
        }
        return instanca;
    }

    public void otvoriKonekciju() throws SQLException {
        konekcija = DriverManager.getConnection(
                property.ucitajVrednostZaKljuc(Konstante.URL),
                property.ucitajVrednostZaKljuc(Konstante.USER),
                property.ucitajVrednostZaKljuc(Konstante.PASS));
        konekcija.setAutoCommit(false);
        System.out.println("***Otvorena konekcija***");
    }

    public Connection getKonekcija() {
        return konekcija;
    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            System.out.println("Greska: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println("Greska: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
  
}
