package kontroler;

import domain.Drzava;
import domain.Klizac;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.Takmicenje;
import domain.TipTakmicenja;
import domain.Ucesce;
import form.FrmPokretanjeServera;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.StartServer;
import niti.NitKlijent;
import so.OpstaSO;
import so.SOIzmeniKlizaca;
import so.SOKreirajKlizaca;
import so.SOKreirajTakmicenje;
import so.SOObrisiKlizaca;
import so.SOPronadjiDrzavu;
import so.SOPronadjiTakmicenje;
import so.SOProveriPodatke;
import so.SOVratiKlizaca;
import so.SOPronadjiKlizace;
import so.SOVratiListuDrzava;
import so.SOVratiListuKlizaca;
import so.SOVratiListuTakmicenja;
import so.SOVratiListuTipovaTakmicenja;
import so.SOPronadjiTakmicenja;
import so.SOVratiUcescaNaTakmicenju;
import so.SOZapamtiRezultateNaTakmicenju;
import so.SOZapamtiUcesce;
import storage.database.connection.DBConnection;

/**
 *
 * @author Bojana
 */
public class Kontroler {

    private FrmPokretanjeServera frmServer;
    private static Kontroler instance;
    private StartServer server;
    private final ArrayList<Socket> listaKorisnika;
    private final ArrayList<NitKlijent> klijenti;

    private Kontroler() {
        listaKorisnika = new ArrayList<>();
        klijenti = new ArrayList<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<NitKlijent> getKlijenti() {
        return klijenti;
    }

    public ArrayList<Socket> getListaKorisnika() {
        return listaKorisnika;
    }

    public void dodajKorisnika(Socket socket) {
        listaKorisnika.add(socket);
    }

    public FrmPokretanjeServera getFrmServer() {
        return frmServer;
    }

    public void setFrmServer(FrmPokretanjeServera frmServer) {
        this.frmServer = frmServer;
    }

    public OpstiDomenskiObjekat vratiKlizacaPoKriterijumu(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO so = new SOVratiKlizaca();
        so.izvrsi(odo);
        OpstiDomenskiObjekat klizac = ((SOVratiKlizaca) so).getKlizac();
        return klizac;
    }


    public OpstiDomenskiObjekat proveraPodataka(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO so = new SOProveriPodatke();
        so.izvrsi(odo);
        OpstiDomenskiObjekat k = ((SOProveriPodatke) so).getKorisnik();
        return k;
    }


    public boolean kreirajKlizaca(Klizac odo) throws Exception {
        OpstaSO so = new SOKreirajKlizaca();
        so.izvrsi(odo);
        return so.uspesnost();
    }


    public boolean izmeniKlizaca(Klizac odo) throws Exception {
        OpstaSO so = new SOIzmeniKlizaca();
        so.izvrsi(odo);
        return so.uspesnost();
    }


    public List<OpstiDomenskiObjekat> dajSveKlizace() throws Exception {
        OpstaSO so = new SOVratiListuKlizaca();
        so.izvrsi(new Klizac());
        List<OpstiDomenskiObjekat> klizaci = ((SOVratiListuKlizaca) so).getLista();
        return klizaci;
    }


    public List<OpstiDomenskiObjekat> ucitajTipoveTakmicenja() throws Exception {
        OpstaSO so = new SOVratiListuTipovaTakmicenja();
        so.izvrsi(new TipTakmicenja());
        List<OpstiDomenskiObjekat> lista = ((SOVratiListuTipovaTakmicenja) so).getLista();
        return lista;
    }


    public List<OpstiDomenskiObjekat> vratiListuDrzava() throws Exception {
        OpstaSO so = new SOVratiListuDrzava();
        so.izvrsi(new Drzava());
        List<OpstiDomenskiObjekat> drzave
                = ((SOVratiListuDrzava) so).getLista();
        return drzave;
    }


    public List<OpstiDomenskiObjekat> ucitajTakmicenja() throws Exception {
        OpstaSO so = new SOVratiListuTakmicenja();
        so.izvrsi(new Takmicenje());
        List<OpstiDomenskiObjekat> takmicenja
                = ((SOVratiListuTakmicenja) so).getLista();
        return takmicenja;
    }


    public boolean sacuvajTakmicenje(Takmicenje odo) throws Exception {
        OpstaSO so = new SOKreirajTakmicenje();
        so.izvrsi(odo);
        return so.uspesnost();
    }

    public OpstiDomenskiObjekat vratiKorisnika(Korisnik odo) throws Exception {
        OpstaSO so = new SOProveriPodatke();
        so.izvrsi(odo);
        OpstiDomenskiObjekat korisnik = ((SOProveriPodatke) so).getKorisnik();
        return korisnik;
    }

    public boolean kreirajUcesce(Takmicenje t) throws Exception {
        OpstaSO so = new SOZapamtiUcesce();
        so.izvrsi(t);
        return so.uspesnost();
    }

    public boolean obrisiKlizaca(Klizac odo) throws Exception {
        OpstaSO so = new SOObrisiKlizaca();
        so.izvrsi(odo);
        return so.uspesnost();
    }

    public OpstiDomenskiObjekat pronadjiTakmicenje(Takmicenje odo) throws Exception {
        OpstaSO so = new SOPronadjiTakmicenje();
        so.izvrsi(odo);
        OpstiDomenskiObjekat takmicenje = ((SOPronadjiTakmicenje) so).getTakmicenje();
        return takmicenje;
    }

    public List<OpstiDomenskiObjekat> vratiListuUcesca(Takmicenje t) throws Exception {
        OpstaSO so = new SOVratiUcescaNaTakmicenju();
        Ucesce u = new Ucesce();
        u.setTakmicenje(t);
        so.izvrsi(u);
        List<OpstiDomenskiObjekat> ucesca
                = ((SOVratiUcescaNaTakmicenju) so).getLista();
        return ucesca;

    }

    public OpstiDomenskiObjekat pronadjiDrzavu(Drzava odo) throws Exception {
        OpstaSO so = new SOPronadjiDrzavu();
        so.izvrsi(odo);
        OpstiDomenskiObjekat drzava = ((SOPronadjiDrzavu) so).getDrzava();
        return drzava;
    }

    public List<OpstiDomenskiObjekat> pronadjiTakmicenja(Takmicenje t) throws Exception {
        OpstaSO so = new SOPronadjiTakmicenja();
        so.izvrsi(t);
        return ((SOPronadjiTakmicenja) so).getLista();
    }

    public List<OpstiDomenskiObjekat> pronadjiKlizace(Klizac k) throws Exception {

        OpstaSO so = new SOPronadjiKlizace();
        so.izvrsi(k);
        return ((SOPronadjiKlizace) so).getLista();
    }

    public boolean zapamtiRezultateNaTakmicenju(Takmicenje t) throws Exception {
        OpstaSO so = new SOZapamtiRezultateNaTakmicenju();
        so.izvrsi(t);
        return so.uspesnost();
    }

    public void pokreniServer(int port) {
        try {
            server = new StartServer(port);
            server.start();
            frmServer.pokreniServer();
            frmServer.setVisible(true);
        } catch (Exception ex) {
            frmServer.dispose();
            JOptionPane.showMessageDialog(frmServer, "Broj porta *** " + port + " *** je zauzet!");
            System.exit(0);
        }
    }

    public void zaustaviServer() {
        try {
            frmServer.vratiTabeluKorisnika().obrisiSveIzTabele();
            server.zaustaviServer();
        } catch (IOException ex) {
            System.out.println("Greska: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean daLiJePokrenut() {
        if (server != null) {
            return server.isPokrenut();
        }
        return false;
    }

}
