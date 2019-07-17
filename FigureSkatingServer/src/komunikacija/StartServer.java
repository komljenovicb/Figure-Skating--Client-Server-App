/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import form.FrmPokretanjeServera;
import niti.NitKlijent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;
import kontroler.Kontroler;

/**
 *
 * @author Bojana
 */
public class StartServer extends Thread {

    private final ServerSocket ss;
    private boolean online = false;
    JTextArea txt;

    public StartServer(int port) throws IOException {
        online = true;
        ss = new ServerSocket(port);
        System.out.println("Server je pokrenut!");
    }

    @Override
    public void run() {
        while (online) {
            try {
                Socket socket = ss.accept();
                System.out.println("Klijent se povezao sa serverom");
                NitKlijent nit = new NitKlijent(socket);
                Kontroler.getInstance().getKlijenti().add(nit);
                nit.start();
            } catch (Exception ex) {
                online = false;
                System.out.println("Zaustavljen server...");
            }
        }
    }

    public void zaustaviServer() throws IOException {
        ss.close();
        for (int i = 0; i < Kontroler.getInstance().getKlijenti().size(); i++) {
            NitKlijent nitKlijenta = Kontroler.getInstance().getKlijenti().get(i);
            if (nitKlijenta.getK() != null) {
                nitKlijenta.krajRada();
                nitKlijenta.getSocket().close();
            }

        }
    }

    public boolean isPokrenut() {
        return online;
    }

    public static void main(String[] args) throws IOException {
        new FrmPokretanjeServera().setVisible(true);
    }

}
