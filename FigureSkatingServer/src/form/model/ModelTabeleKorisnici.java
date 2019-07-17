/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.model;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kontroler.Kontroler;

public class ModelTabeleKorisnici extends AbstractTableModel {

    ArrayList<OpstiDomenskiObjekat> listaKorisnika;
    String[] kolone = new String[]{"ID", "Korisnik", "Korisničko ime"};

    public ModelTabeleKorisnici() {
        listaKorisnika = new ArrayList<>();
    }

    public ModelTabeleKorisnici(ArrayList<OpstiDomenskiObjekat> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public ArrayList<OpstiDomenskiObjekat> getListaKorisnika() {
        return listaKorisnika;
    }

    @Override
    public int getRowCount() {
        if (listaKorisnika == null) {
            return 0;
        }
        return listaKorisnika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Korisnik korisnik = (Korisnik) listaKorisnika.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return korisnik.getId();
            case 1:
                return korisnik.getIme() + " " + korisnik.getPrezime();
            case 2:
                return korisnik.getKorisnickoIme();
            default:
                return "";
        }

    }

    @Override
    public String getColumnName(int column
    ) {
        return kolone[column];
    }

    public OpstiDomenskiObjekat vratiKorisnika(int rowIndex) {
        return listaKorisnika.get(rowIndex);
    }

    public boolean dodajKorisnika(Korisnik korisnik) {
        if(korisnik == null || !Kontroler.getInstance().daLiJePokrenut()) {
            return false;
        }
        if (!listaKorisnika.contains(korisnik)) {
            listaKorisnika.add(korisnik);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    public void obrisiKorisnika(Korisnik korisnik) {
        listaKorisnika.remove(korisnik);
        fireTableDataChanged();
    }

    public void obrisiSveIzTabele() {
        listaKorisnika.removeAll(listaKorisnika);
    }

}
