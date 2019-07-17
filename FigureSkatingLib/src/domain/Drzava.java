/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class Drzava extends OpstiDomenskiObjekat {

    private String drzavaID;
    private String skraceniNaziv;
    private String punNaziv;

    public Drzava() {
    }

    public Drzava(String drzavaID, String skraceniNaziv, String punNaziv) {
        this.drzavaID = drzavaID;
        this.skraceniNaziv = skraceniNaziv;
        this.punNaziv = punNaziv;
    }

    public String getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(String drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    public String getPunNaziv() {
        return punNaziv;
    }

    public void setPunNaziv(String punNaziv) {
        this.punNaziv = punNaziv;
    }

    @Override
    public String toString() {
        return skraceniNaziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "drzava";
    }

    @Override
    public String vratiFrom() {
        return "drzava";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "(" + skraceniNaziv + "," + punNaziv + ")";
    }

    @Override
    public String vratiUslov() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> drzave = new ArrayList<>();
        try {
            while (rs.next()) {
                Drzava drzava = new Drzava();
                drzava.setDrzavaID(rs.getString("drzavaID"));
                drzava.setSkraceniNaziv(rs.getString("skraceniNaziv"));
                drzava.setPunNaziv(rs.getString("punNaziv"));
                drzave.add(drzava);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Drzava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return drzave;
    }

    @Override
    public String uslovZaSortiranje() {
        return "order by skraceniNaziv asc";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drzava other = (Drzava) obj;
        if (!Objects.equals(this.skraceniNaziv, other.skraceniNaziv)) {
            return false;
        }
        return true;
    }

}
