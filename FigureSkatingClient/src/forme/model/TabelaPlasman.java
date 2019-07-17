/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domain.Ucesce;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class TabelaPlasman extends AbstractTableModel {

    ArrayList<Ucesce> ucesca;
    String[] header = new String[]{"Klizač", "Disciplina", "Tehnički deo",
        "Prezentacija", "Ukupno poena", "Plasman"};

    public TabelaPlasman() {
        ucesca = new ArrayList<>();
    }

    public TabelaPlasman(ArrayList<Ucesce> ucesca) {
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
            case 4:
                return ucesce.getUkupnoOstvarenoPoena();
            case 5:
                return ucesce.getPlasman();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

}
