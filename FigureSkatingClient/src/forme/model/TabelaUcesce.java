/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domain.Klizac;
import domain.Ucesce;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class TabelaUcesce extends AbstractTableModel {

    ArrayList<Ucesce> ucesca;
    String[] header = new String[]{"Klizač", "Disciplina", "Tehnički deo", "Prezentacija"};

    public TabelaUcesce() {
        ucesca = new ArrayList<>();
    }

    public TabelaUcesce(ArrayList<Ucesce> ucesca) {
        this.ucesca = ucesca;
    }

    public ArrayList<Ucesce> getUcesca() {
        return ucesca;
    }

    @Override
    public int getRowCount() {
        if (ucesca == null) {
            return 0;
        }
        return ucesca.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucesce ucesce = ucesca.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ucesce.getKlizac();
            case 1:
                return ucesce.getDisciplina();
            case 2:
                return ucesce.getTehnickiDeo();
            case 3:
                return ucesce.getPrezentacija();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Ucesce ucesce = ucesca.get(rowIndex);
        switch (columnIndex) {
            case 0:
                Klizac klizac = (Klizac) aValue;
                ucesce.setKlizac(klizac);
                break;
            case 1:
                String disciplina = (String) aValue;
                ucesce.setDisciplina(disciplina);
                break;
            case 2:
                try {
                    String tehnickiDeo = (String) aValue;
                    double td = Double.parseDouble(tehnickiDeo);
                    ucesce.setTehnickiDeo(td);
                } catch (NumberFormatException ex) {
                    System.out.println("Unesite realan broj");
                    ex.printStackTrace();
                }
                break;
            case 3:
                try {
                    String prezentacija = (String) aValue;
                    double p = Double.parseDouble(prezentacija);
                    ucesce.setPrezentacija(p);
                } catch (NumberFormatException ex) {
                    System.out.println("Unesite realan broj");
                    ex.printStackTrace();
                }
                break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 2:
                return false;
            case 3:
                return false;
            default:
                return true;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    public void dodajUcesce(Ucesce ucesce) {
        if (!ucesca.contains(ucesce)) {
            ucesca.add(ucesce);
        }
        fireTableDataChanged();
    }

    public void obrisi(int odabraniRed) {
        ucesca.remove(odabraniRed);
        fireTableDataChanged();
    }

    public void dodajUcesce() {
        ucesca.add(new Ucesce());
        fireTableDataChanged();
    }

    public boolean dodaj(Klizac k) {

        Ucesce u = new Ucesce(k, "", 0.0, 0.0, 0.0, 0);

        if (!ucesca.contains(u)) {

            ucesca.add(u);
            fireTableDataChanged();
            return true;

        }
        return false;
    }
}
