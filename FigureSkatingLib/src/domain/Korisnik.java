/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class Korisnik extends OpstiDomenskiObjekat {

    private String id;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;

    public Korisnik() {
    }

    public Korisnik(String id, String korisnickoIme, String lozinka,
            String ime, String prezime) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = getSHA512(lozinka);
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

    @Override
    public String toString() {
        return "'" + ime + "' '" + prezime + "' ";

    }

    @Override
    public String vratiNazivTabele() {
        return "korisnici";
    }

    @Override
    public String vratiFrom() {
        return "korisnici";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "(" + korisnickoIme + ","
                + getSHA512(lozinka) + "," + ime + "," + prezime + ")";
    }

    @Override
    public String vratiUslov() {
        if (mapaUslov == null) {
            return "";
        }
        if ((Integer) mapaUslov.get(Util.USLOV_PRONADJI_KORISNIKA) == 1) {
            return "where korisnickoIme='" + korisnickoIme + "' and lozinka='" + lozinka + "'";
        }
        return "";
    }
     
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> korisnici = new ArrayList<>();
        try {
            while (rs.next()) {
                Korisnik k = new Korisnik();
                k.setId(rs.getString("id"));
                k.setKorisnickoIme(rs.getString("korisnickoIme"));
                k.setLozinka(rs.getString("lozinka"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                korisnici.add(k);
            }
            rs.close();
            return korisnici;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return korisnici;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return true;
    }

    public String getSHA512(String input){

	String toReturn = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    digest.reset();
	    digest.update(input.getBytes("utf8"));
	    toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return toReturn;
    }
}
