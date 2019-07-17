/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domain.Drzava;
import domain.EnumPol;
import domain.Klizac;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class TableModelKlizac extends AbstractTableModel {

    List<Klizac> klizaci;
    String[] kolone = new String[]{"klizacID", "ime", "prezime",
        "pol", "datumRodjenja", "drzavaID"};

    public TableModelKlizac(List<Klizac> klizaci) {
        this.klizaci = klizaci;
    }

    @Override
    public int getRowCount() {
        if (klizaci == null) {
            return 0;
        }
        return klizaci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return klizaci.get(rowIndex).getIdKlizaca();
            case 1:
                return klizaci.get(rowIndex).getIme();
            case 2:
                return klizaci.get(rowIndex).getPrezime();
            case 3:
                return klizaci.get(rowIndex).getDatumRodjenja();
            case 4:
                return klizaci.get(rowIndex).getPol();
            case 5:
                return klizaci.get(rowIndex).getDrzava();

            default:
                return "N/A";

        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        try {
            Klizac k = klizaci.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    String sif = (String) aValue;
                    k.setIdKlizaca(sif);
                    break;
                case 1:
                    String ime = (String) aValue;
                    k.setIme(ime);
                    break;
                case 2:
                    String prezime = (String) aValue;
                    k.setPrezime(prezime);
                    break;
                case 3:
                    String dat = (String) aValue;
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                    Date datum = s.parse(dat);
                    k.setDatumRodjenja(datum);
                    break;
                case 4:
                    EnumPol pol = (EnumPol) aValue;
                    k.setPol(pol);
                    break;
                case 5:
                    Drzava d = (Drzava) aValue;
                    k.setDrzava(d);
                    break;

            }
        } catch (ParseException ex) {
            Logger.getLogger(TableModelKlizac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sacuvajKlizaca() {
        klizaci.add(new Klizac());
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        klizaci.remove(red);
        fireTableDataChanged();
    }

    public List<Klizac> getKlizaci() {
        return klizaci;
    }

    public Klizac vratiIzabranogKlizaca(int red) {
        return klizaci.get(red);
    }

    public Klizac vratiKlizacaIzTabele(Klizac klizac) {
        for (Klizac kl : klizaci) {
            if (kl.getIdKlizaca().equals(klizac.getIdKlizaca())) {
                return kl;
            }
        }
        return null;
    }

    public void obrisiKlizaca(Klizac klizac) {
        klizaci.remove(klizac);
        fireTableDataChanged();
    }

}
