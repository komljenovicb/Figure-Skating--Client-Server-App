/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bojana
 */
public class Takmicenje extends OpstiDomenskiObjekat {

    private String takmicenjeID;
    private String naziv;
    private Date pocetak;
    private Date kraj;
    private String mestoOdrzavanja;
    private TipTakmicenja tipTakmicenja;
    private ArrayList<Ucesce> ucesca;

    public Takmicenje() {
    }

    public Takmicenje(String takmicenjeID, String naziv, Date pocetak,
            Date kraj, String mestoOdrzavanja, TipTakmicenja tipTakmicenja) {
        this.takmicenjeID = takmicenjeID;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.mestoOdrzavanja = mestoOdrzavanja;
        this.tipTakmicenja = tipTakmicenja;
    }

    public Takmicenje(String takmicenjeID, String naziv, Date pocetak, Date kraj, String mestoOdrzavanja, TipTakmicenja tipTakmicenja, ArrayList<Ucesce> ucesca) {
        this.takmicenjeID = takmicenjeID;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.mestoOdrzavanja = mestoOdrzavanja;
        this.tipTakmicenja = tipTakmicenja;
        this.ucesca = ucesca;
    }

    public String getTakmicenjeID() {
        return takmicenjeID;
    }

    public void setTakmicenjeID(String takmicenjeID) {
        this.takmicenjeID = takmicenjeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public TipTakmicenja getTipTakmicenja() {
        return tipTakmicenja;
    }

    public void setTipTakmicenja(TipTakmicenja tipTakmicenja) {
        this.tipTakmicenja = tipTakmicenja;
    }

    public String getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setMestoOdrzavanja(String mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    public ArrayList<Ucesce> getUcesca() {
        return ucesca;
    }

    public void setUcesca(ArrayList<Ucesce> ucesca) {
        this.ucesca = ucesca;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "takmicenje";
    }

    @Override
    public String vratiFrom() {
        return "takmicenje t join tiptakmicenja tt on (t.tipID = tt.tipID) ";
    }

    @Override
    public String vratiVrednostiAtributa() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "('" + takmicenjeID + "','" + naziv + "','" + sdf.format(pocetak) + "','" + sdf.format(kraj) + "','" + mestoOdrzavanja + "','" + tipTakmicenja.getIdTipaTakmicenja() + "')";
    }

    @Override
    public String vratiUslov() {

        if (pocetak != null && kraj != null) {
            return "where pocetak between '" + new java.sql.Date(pocetak.getTime())
                    + "'and '" + new java.sql.Date(kraj.getTime()) + "'";
        }

        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> takmicenja = new ArrayList<>();
        try {
            while (rs.next()) {
                Takmicenje t = new Takmicenje();
                t.setTakmicenjeID(rs.getString("takmicenjeID"));
                t.setNaziv(rs.getString("naziv"));
                t.setPocetak(rs.getDate("pocetak"));
                t.setKraj(rs.getDate("kraj"));
                t.setMestoOdrzavanja(rs.getString("mesto"));
                t.setTipTakmicenja(new TipTakmicenja(rs.getString("tipID"),
                        rs.getString("nazivTipaTakmicenja")));
                takmicenja.add(t);
            }
            rs.close();
            return takmicenja;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return takmicenja;
    }

    @Override
    public String uslovZaSortiranje() {
        return "order by naziv asc";
    }

}
