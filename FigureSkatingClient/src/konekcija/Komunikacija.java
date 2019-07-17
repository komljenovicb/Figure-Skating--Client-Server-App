/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konekcija;

import forme.FormLogIn;
import forme.FormMain;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import kontroler.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class Komunikacija {

    private FormLogIn frm;
    private FormMain glavnaForma;
    private Socket vezaSaServerom;
    private static Komunikacija instanca;

    private Komunikacija() {

        try {

            vezaSaServerom
                    = new Socket("localhost", 9000);
            Kontroler.getInstance().getMapa().
                    put(Util.MAP_KEY_SOCKET, vezaSaServerom);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frm,
                    "Server još uvek nije pokrenut, pokušajte kasnije!");
            System.exit(0);
        }

    }

    public static Komunikacija getInstanca() throws IOException {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public Socket getVezaSaServerom() {
        return vezaSaServerom;
    }

    public FormLogIn getFrm() {
        return frm;
    }

    public void setFrm(FormLogIn frm) {
        this.frm = frm;
    }

    public FormMain getGlavnaForma() {
        return glavnaForma;
    }

    public void setGlavnaForma(FormMain glavnaForma) {
        this.glavnaForma = glavnaForma;
    }

    public void posaljiZahtev(TransferObjekatZahtev zahtev) {
        try {
            ObjectOutputStream outKaServeru 
                    = new ObjectOutputStream(vezaSaServerom.getOutputStream());
            outKaServeru.writeObject(zahtev);
        } catch (IOException ex) {
            System.out.println("Greska: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Server je prestao sa radom!");
            System.exit(0);
        }
    }

    public TransferObjekatOdgovor primiOdgovor() {

        TransferObjekatOdgovor so = new TransferObjekatOdgovor();

        try {
            ObjectInputStream ois = new ObjectInputStream(vezaSaServerom.getInputStream());
            so = (TransferObjekatOdgovor) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Greska: " + ex.getMessage());
            try {
                vezaSaServerom.close();
            } catch (IOException ex1) {
                System.out.println("Greska: " + ex1.getMessage());
            }
            System.out.println("Soket zatvoren...");
        }
        return so;
    }

}
