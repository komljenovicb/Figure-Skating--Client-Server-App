/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bojana
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    protected Map<String, Object> mapaUslov;
    public int brojVezanihObjekata;

    public OpstiDomenskiObjekat() {
        mapaUslov = new HashMap<>();
    }

    public Map<String, Object> getMapaUslov() {
        return mapaUslov;
    }

    public void setMapaUslov(Map<String, Object> mapaUslov) {
        this.mapaUslov = mapaUslov;
    }

    public abstract String vratiNazivTabele();

    public abstract String vratiVrednostiAtributa();

    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        return null;
    }

    public String vratiUslov() {
        return "";
    }

    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }

    public List<OpstiDomenskiObjekat> dopuniListu(List<OpstiDomenskiObjekat> lista, ResultSet rs, ResultSet rsVezaniObjekat) {
        return null;
    }

    public String vratiVrednostiZaInsert() {
        return "";
    }

    public String vratiFrom() {
        return "";
    }

    public String uslovZaSortiranje() {
        return "";
    }

    public String vratiVrednostiAtributaZaSet() {
        return "";
    }
    
    public String vratiUslovZaSet() {
        return "";
    }

}
