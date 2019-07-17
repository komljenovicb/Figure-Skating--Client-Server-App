/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import kontroler.Kontroler;
import domain.Takmicenje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class TableModelTakmicenje extends AbstractTableModel {

    List<Takmicenje> takmicenja;
    String[] header = new String[]{"Naziv", "Datum početka",
        "Datum kraja", "Tip takmičenja"};

    public TableModelTakmicenje() {
        takmicenja = Kontroler.getInstance().ucitajTakmicenja();
    }

    public TableModelTakmicenje(List<Takmicenje> takmicenje) {
        this.takmicenja = takmicenje;
    }

    @Override
    public int getRowCount() {
        if (takmicenja == null) {
            return 0;
        }
        return takmicenja.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Takmicenje takmicenje = takmicenja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return takmicenje.getNaziv();
            case 1:
                return takmicenje.getPocetak();
            case 2:
                return takmicenje.getKraj();
            case 3:
                return takmicenje.getTipTakmicenja();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

}
