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

/**
 *
 * @author Bojana
 */
public class TipTakmicenja extends OpstiDomenskiObjekat {

    String idTipaTakmicenja;
    String nazivTipaTakmicenja;

    public TipTakmicenja() {
    }

    public TipTakmicenja(String idTipaTakmicenja, String nazivTipaTakmicenja) {
        this.idTipaTakmicenja = idTipaTakmicenja;
        this.nazivTipaTakmicenja = nazivTipaTakmicenja;
    }

    public String getIdTipaTakmicenja() {
        return idTipaTakmicenja;
    }

    public void setIdTipaTakmicenja(String idTipaTakmicenja) {
        this.idTipaTakmicenja = idTipaTakmicenja;
    }

    public String getNazivTipaTakmicenja() {
        return nazivTipaTakmicenja;
    }

    public void setNazivTipaTakmicenja(String nazivTipaTakmicenja) {
        this.nazivTipaTakmicenja = nazivTipaTakmicenja;
    }

    @Override
    public String toString() {
        return nazivTipaTakmicenja;
    }

    @Override
    public String vratiNazivTabele() {
        return "tiptakmicenja";
    }

    @Override
    public String vratiFrom() {
        return "tiptakmicenja";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return nazivTipaTakmicenja;
    }

    @Override
    public String vratiUslov() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> tipoviTakmicenja = new ArrayList<>();
        try {
            while(rs.next()) { 
                TipTakmicenja tip = new TipTakmicenja();
                tip.setIdTipaTakmicenja(rs.getString("tipID"));
                tip.setNazivTipaTakmicenja(rs.getString("nazivTipaTakmicenja"));
                tipoviTakmicenja.add(tip);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tipoviTakmicenja;
    }

}
