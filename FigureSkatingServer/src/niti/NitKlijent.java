/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domain.Drzava;
import domain.Klizac;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import kontroler.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import transfer.util.EnumStatus;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class NitKlijent extends Thread {

    private final Socket socket;
    private Korisnik k;
    private Takmicenje t;
    private boolean kraj = false;

    public NitKlijent(Socket socket) {
        this.socket = socket;
        k = new Korisnik();
        t = new Takmicenje();
    }

    public Socket getSocket() {
        return socket;
    }

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }

    @Override
    public void run() {

        try {
            while (!kraj) {

                ObjectInputStream inSocket
                        = new ObjectInputStream(socket.getInputStream());
                TransferObjekatZahtev zahtev
                        = (TransferObjekatZahtev) inSocket.readObject();
                TransferObjekatOdgovor too = new TransferObjekatOdgovor();
                int operacija = zahtev.getOperacija();
                System.out.println("Operacija: " + operacija);
                boolean signal;
                switch (operacija) {
                    case Util.OPERACIJA_PROVERI_PODATKE: {

                        OpstiDomenskiObjekat korisnik
                                = Kontroler.getInstance().
                                        proveraPodataka((OpstiDomenskiObjekat) zahtev.getParametar());

                        k = (Korisnik) korisnik;

                        boolean dodat = Kontroler.getInstance().getFrmServer().vratiTabeluKorisnika().dodajKorisnika(k);

                        if (k != null && dodat) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                            too.setRezultat(k);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_KLIZACA: {
                        OpstiDomenskiObjekat klizac
                                = Kontroler.getInstance().
                                        vratiKlizacaPoKriterijumu(new Klizac((String) zahtev
                                                .getParametar(),
                                                null, null, null,
                                                null, null));
                        too
                                = new TransferObjekatOdgovor();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(klizac);
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_LISTU_DRZAVA: {
                        List<OpstiDomenskiObjekat> drzave
                                = Kontroler.getInstance().vratiListuDrzava();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(drzave);
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_LISTU_KLIZACA: {
                        List<OpstiDomenskiObjekat> klizaci
                                = Kontroler.getInstance().dajSveKlizace();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(klizaci);
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_LISTU_TAKMICENJA: {
                        List<OpstiDomenskiObjekat> takmicenja
                                = Kontroler.getInstance().ucitajTakmicenja();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(takmicenja);
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_LISTU_TIPOVA_TAKMICENJA: {
                        List<OpstiDomenskiObjekat> tipoviTakmicenja
                                = Kontroler.getInstance().ucitajTipoveTakmicenja();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(tipoviTakmicenja);
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_PRONADJI_TAKMICENJE: {
                        OpstiDomenskiObjekat takmicenje
                                = Kontroler.getInstance().
                                        pronadjiTakmicenje(new Takmicenje(null,
                                                (String) zahtev.getParametar(),
                                                null,
                                                null,
                                                null,
                                                null));

                        too
                                = new TransferObjekatOdgovor();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(takmicenje);
                        posalji(too);
                        break;
                    }

                    case Util.OPERACIJA_KREIRAJ_KLIZACA: {
                        signal = Kontroler.getInstance().
                                kreirajKlizaca((Klizac) zahtev.
                                        getParametar());
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_KREIRAJ_TAKMICENJE: {

                        signal
                                = Kontroler.getInstance().
                                        sacuvajTakmicenje((Takmicenje) zahtev.
                                                getParametar());
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_ZAPAMTI_UCESCE: {

                        t = (Takmicenje) zahtev.getParametar();
                        signal
                                = Kontroler.getInstance().
                                        kreirajUcesce(t);
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_OBRISI_KLIZACA: {
                        signal
                                = Kontroler.getInstance().
                                        obrisiKlizaca((Klizac) zahtev.
                                                getParametar());
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_IZMENI_KLIZACA: {
                        signal
                                = Kontroler.getInstance().
                                        izmeniKlizaca((Klizac) zahtev.
                                                getParametar());
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                    }
                    case Util.OPERACIJA_VRATI_LISTU_UCESCA: {
                        Takmicenje t = (Takmicenje) zahtev.getParametar();
                        List<OpstiDomenskiObjekat> ucesca
                                = Kontroler.getInstance().vratiListuUcesca(t);
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(ucesca);
                        posalji(too);
                        break;
                    }

                    case Util.OPERACIJA_PRONADJI_DRZAVU: {

                        OpstiDomenskiObjekat drzava
                                = Kontroler.getInstance().
                                        pronadjiDrzavu(new Drzava((String) zahtev.getParametar(), null,
                                                null));
                        too
                                = new TransferObjekatOdgovor();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(drzava);
                        posalji(too);
                        break;
                    }

                    case Util.OPERACIJA_TAKMICENJA_PO_USLOVU: {

                        List<OpstiDomenskiObjekat> takmicenja
                                = Kontroler.getInstance().pronadjiTakmicenja((Takmicenje) zahtev.getParametar());

                        too
                                = new TransferObjekatOdgovor();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(takmicenja);
                        posalji(too);
                        break;
                    }

                    case Util.OPERACIJA_VRATI_KLIZACE_PO_USLOVU: {

                        List<OpstiDomenskiObjekat> klizaci
                                = Kontroler.getInstance().pronadjiKlizace((Klizac) zahtev.getParametar());

                        too
                                = new TransferObjekatOdgovor();
                        too.setStatus(EnumStatus.SVE_U_REDU);
                        too.setRezultat(klizaci);
                        posalji(too);
                        break;
                    }

                    case Util.OPERACIJA_ZAPAMTI_REZULTATE: {
                        System.out.println("Stiglo ovde!");
                        
                        t = (Takmicenje) zahtev.getParametar();
                        signal
                                = Kontroler.getInstance().zapamtiRezultateNaTakmicenju(t);
                        if (signal) {
                            too.setStatus(EnumStatus.SVE_U_REDU);
                        } else {
                            too.setStatus(EnumStatus.GRESKA);
                        }
                        posalji(too);
                        break;
                        
                    }

                    case Util.ODJAVI_SE: {
                        too.setPoruka("Uspešno ste se odjavili!");
                        Kontroler.getInstance().getFrmServer().vratiTabeluKorisnika().obrisiKorisnika(k);
                        too.setPoruka("Uspešno ste se odjavili.");
                        posalji(too);
                        break;
                    }

                }
            }

        } catch (SocketException ex) {

            try {
                Kontroler.getInstance().getFrmServer().vratiTabeluKorisnika().obrisiKorisnika(k);
                Kontroler.getInstance().getKlijenti().remove(this);
                System.out.println("Klijent je odjavljen!");
                socket.close();
            } catch (IOException e) {
                System.out.println("Greska: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception ex) {
            kraj = true;
            System.out.println("Soket zatvoren!");
        }

    }

    public void posalji(TransferObjekatOdgovor odgovor) throws IOException {
        ObjectOutputStream outKaKlijentu
                = new ObjectOutputStream(socket.getOutputStream());
        outKaKlijentu.writeObject(odgovor);
    }

    public void krajRada() throws IOException {
        socket.close();
    }
}
