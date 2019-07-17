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

/**
 *
 * @author Bojana
 */
public class Ucesce extends OpstiDomenskiObjekat implements Comparable<Ucesce> {

    Takmicenje takmicenje;
    Klizac klizac;
    String disciplina;
    double tehnickiDeo;
    double prezentacija;
    double ukupnoOstvarenoPoena;
    int plasman;

    public Ucesce() {
    }

    public Ucesce(Klizac klizac, String disciplina, double tehnickiDeo, double prezentacija, double ukupnoOstvarenoPoena, int plasman) {
        this.klizac = klizac;
        this.disciplina = disciplina;
        this.tehnickiDeo = tehnickiDeo;
        this.prezentacija = prezentacija;
        this.ukupnoOstvarenoPoena = ukupnoOstvarenoPoena;
        this.plasman = plasman;
    }

    public Ucesce(Takmicenje takmicenje, Klizac klizac, String disciplina, double tehnickiDeo, double prezentacija, double ukupnoOstvarenoPoena, int plasman) {
        this.takmicenje = takmicenje;
        this.klizac = klizac;
        this.disciplina = disciplina;
        this.tehnickiDeo = tehnickiDeo;
        this.prezentacija = prezentacija;
        this.ukupnoOstvarenoPoena = ukupnoOstvarenoPoena;
        this.plasman = plasman;
    }

    public Takmicenje getTakmicenje() {
        return takmicenje;
    }

    public void setTakmicenje(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
    }

    public Klizac getKlizac() {
        return klizac;
    }

    public void setKlizac(Klizac klizac) {
        this.klizac = klizac;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public double getTehnickiDeo() {
        return tehnickiDeo;
    }

    public void setTehnickiDeo(double tehnickiDeo) {
        this.tehnickiDeo = tehnickiDeo;
    }

    public double getPrezentacija() {
        return prezentacija;
    }

    public void setPrezentacija(double prezentacija) {
        this.prezentacija = prezentacija;
    }

    public double getUkupnoOstvarenoPoena() {
        return ukupnoOstvarenoPoena;
    }

    public void setUkupnoOstvarenoPoena(double ukupnoOstvarenoPoena) {
        this.ukupnoOstvarenoPoena = ukupnoOstvarenoPoena;
    }

    public int getPlasman() {
        return plasman;
    }

    public void setPlasman(int plasman) {
        this.plasman = plasman;
    }

    @Override
    public String toString() {
        return disciplina;
    }

    @Override
    public String vratiNazivTabele() {
        return "ucesce";
    }

    @Override
    public String vratiFrom() {
        return "ucesce u join takmicenje t on(u.takmicenjeID = t.takmicenjeID) "
                + "join klizac k on(u.klizacID = k.klizacID) "
                + "join drzava d on (k.drzavaID = d.drzavaID)"
                + " join tiptakmicenja tt on (t.tipID = tt.tipID)";
    }

    @Override
    public String vratiUslov() {
        return "where t.takmicenjeID=" + String.format("%s",
                "'" + takmicenje.getTakmicenjeID() + "'");
    }

    @Override
    public String vratiVrednostiAtributaZaSet() {
        return "disciplina = '" + disciplina
                + "', tehnickiDeo=" + tehnickiDeo
                + ", prezentacija=" + prezentacija
                + ", ukupnoOstvarenoPoena="
                + ukupnoOstvarenoPoena + ", plasman="
                + plasman;

    }

    @Override
    public String vratiUslovZaSet() {
        return "where takmicenjeID=" + String.format("%s",
                "'" + takmicenje.getTakmicenjeID() + "'")
                + " and klizacID = " + String.format("%s",
                        "'" + klizac.getIdKlizaca() + "'");
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "('" + takmicenje.getTakmicenjeID()
                + "','" + klizac.getIdKlizaca()
                + "','" + disciplina
                + "','" + tehnickiDeo
                + "','" + prezentacija
                + "','" + ukupnoOstvarenoPoena
                + "','" + plasman
                + "')";
    }
    
    @Override
    public String uslovZaSortiranje() { 
        return "order by plasman asc";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> ucesca = new ArrayList<>();
        try {
            while (rs.next()) {

                Ucesce u = new Ucesce();
                Drzava d = new Drzava();
                Klizac k = new Klizac();

                k.setIdKlizaca(rs.getString("klizacID"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setDatumRodjenja(rs.getDate("datumRodjenja"));
                k.setPol(EnumPol.valueOf(rs.getString("pol")));

                d.setDrzavaID("drzavaID");
                d.setSkraceniNaziv("skraceniNaziv");
                d.setPunNaziv("punNaziv");

                k.setDrzava(d);
                u.setKlizac(k);

                u.setDisciplina(rs.getString("disciplina"));
                u.setTehnickiDeo(rs.getDouble("tehnickiDeo"));
                u.setPrezentacija(rs.getDouble("prezentacija"));
                u.setUkupnoOstvarenoPoena(rs.getDouble("ukupnoOstvarenoPoena"));
                u.setPlasman(rs.getInt("plasman"));

                ucesca.add(u);
            }

        } catch (SQLException ex) {
            System.out.println("Greska: " + ex.getMessage());
            ex.printStackTrace();
        }
        return ucesca;
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
        final Ucesce other = (Ucesce) obj;
        if (!Objects.equals(this.klizac, other.klizac)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ucesce u) {
        return (ukupnoOstvarenoPoena < u.ukupnoOstvarenoPoena) ? 1
                : (ukupnoOstvarenoPoena > u.ukupnoOstvarenoPoena) ? -1 : 0;
    }

}
