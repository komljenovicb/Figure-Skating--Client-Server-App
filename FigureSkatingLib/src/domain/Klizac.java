/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class Klizac extends OpstiDomenskiObjekat {

    private String klizacID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private EnumPol pol;
    private Drzava drzava;

    public Klizac() {
    }

    public Klizac(String idKlizaca, String ime, String prezime, Date datumRodjenja, EnumPol pol, Drzava drzava) {
        this.klizacID = idKlizaca;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.drzava = drzava;
    }

    public Klizac(String ime, String prezime, Date datum, EnumPol pol, Drzava drzava) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datum;
        this.pol = pol;
        this.drzava = drzava;
    }

    public String getIdKlizaca() {
        return klizacID;
    }

    public void setIdKlizaca(String idKlizaca) {
        this.klizacID = idKlizaca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public EnumPol getPol() {
        return pol;
    }

    public void setPol(EnumPol pol) {
        this.pol = pol;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klizac";
    }

    @Override
    public String vratiFrom() {
        return "klizac k join drzava d on (k.drzavaID = d.drzavaID)";
    }

    @Override
    public String vratiVrednostiAtributa() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "('" + klizacID + "','" + ime + "','"
                + prezime + "','" + sdf.format(datumRodjenja)
                + "','" + pol + "','" + drzava.getDrzavaID() + "')";
    }

    @Override
    public String vratiUslov() {
        
        if (klizacID != null || prezime != null) {

            return "where klizacID=" + String.format("%s",
                    "'" + klizacID + "' or prezime like '" + prezime + "%'");
        }

        return "";
    }

    @Override
    public String vratiVrednostiAtributaZaSet() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "ime = '" + ime + "', prezime = '" + prezime
                + "' , datumRodjenja = '" + sdf.format(datumRodjenja)
                + "', pol = '" + pol + "', drzavaID = '"
                + drzava.getDrzavaID() + "'";
    }

    @Override
    public String vratiUslovZaSet() {
        if (mapaUslov == null) {
            return "";
        }

        if (klizacID != null) {

            return "where klizacID=" + String.format("%s",
                    "'" + klizacID + "'");
        }

        return "";
    }

    //
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> klizaci = new ArrayList<>();
        try {
            while (rs.next()) {
                Klizac k = new Klizac();
                k.setIdKlizaca(rs.getString("klizacID"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setDatumRodjenja(rs.getDate("datumRodjenja"));
                k.setPol(EnumPol.valueOf(rs.getString("pol")));
                k.setDrzava(new Drzava(rs.getString("drzavaID"),
                        rs.getString("skraceniNaziv"),
                        rs.getString("punNaziv")));
                klizaci.add(k);
            }
            rs.close();
            return klizaci;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return klizaci;
    }

    @Override
    public String uslovZaSortiranje() {
        return "order by prezime asc, klizacID asc";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Klizac other = (Klizac) obj;
        if (!Objects.equals(this.klizacID, other.klizacID)) {
            return false;
        }
        return true;
    }

}
