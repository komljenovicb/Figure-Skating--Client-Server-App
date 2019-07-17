/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import javax.swing.JOptionPane;

/**
 *
 * @author Bojana
 */
public class ValidacijaPraznihPolja implements IValidacija {

    @Override
    public void izvrsiValidaciju(String tekst) {
        
        if (tekst == null || tekst.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Sva polja su obavezna!",
                    "Poruka",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
