package kontroler;

import domain.Drzava;
import domain.Klizac;
import domain.Korisnik;
import domain.Takmicenje;
import domain.TipTakmicenja;
import domain.Ucesce;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import konekcija.Komunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import transfer.util.EnumStatus;
import transfer.util.Util;

/**
 *
 * @author Bojana
 */
public class Kontroler {

    private static Kontroler instance;
    private Map<String, Object> mapa;
    private Takmicenje takmicenje;
    private String operacija = "";

    private Kontroler() {
        mapa = new HashMap<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public String getOperacija() {
        return operacija;
    }

    public void setOperacija(String operacija) {
        this.operacija = operacija;
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, Object> mapa) {
        this.mapa = mapa;
    }

    public Takmicenje getTakmicenje() {
        return takmicenje;
    }

    public void setTakmicenje(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
    }

    // 1
    public Korisnik ProveriPodatke(Korisnik korisnik) {

        try {
			
            TransferObjekatZahtev toz = new TransferObjekatZahtev();

            toz.setOperacija(Util.OPERACIJA_PROVERI_PODATKE);
            toz.setParametar(korisnik);

            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                return (Korisnik) too.getRezultat();
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;

    }

    // 2
    public Klizac getKlizac(String id) {

        Klizac k = null;

        try {
			
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_KLIZACA);
            toz.setParametar(id);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                k = (Klizac) too.getRezultat();
                return k;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return k;
    }

    // 3
    public boolean sacuvajTakmicenje(Takmicenje takmicenje) {

        boolean sacuvano = false;

        try {
			
            TransferObjekatZahtev toz = new TransferObjekatZahtev();

            toz.setOperacija(Util.OPERACIJA_KREIRAJ_TAKMICENJE);
            toz.setParametar(takmicenje);

            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return sacuvano;
    }

    // 4
    public boolean kreirajKlizaca(Klizac klizac) {

        boolean sacuvano = false;

        try {

            TransferObjekatZahtev toz = new TransferObjekatZahtev();

            toz.setOperacija(Util.OPERACIJA_KREIRAJ_KLIZACA);
            toz.setParametar(klizac);

            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                sacuvano = true;
                return sacuvano;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return sacuvano;
    }

    // 5
    public boolean azurirajKlizaca(Klizac klizac) {

        boolean sacuvano = false;

        try {

            TransferObjekatZahtev toz = new TransferObjekatZahtev();

            toz.setOperacija(Util.OPERACIJA_IZMENI_KLIZACA);
            toz.setParametar(klizac);

            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();

            if (too.getRezultat() == EnumStatus.SVE_U_REDU) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return sacuvano;
    }

    // 6
    public boolean obrisiKlizac(Klizac klizac) {
        boolean obrisan = false;
        try {

            TransferObjekatZahtev toz = new TransferObjekatZahtev();

            toz.setOperacija(Util.OPERACIJA_OBRISI_KLIZACA);
            toz.setParametar(klizac);

            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();

            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return obrisan;
    }

    // 7
    public List<Klizac> dajSveKlizace() {
        List<Klizac> klizaci = new ArrayList<>();
        try {

            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_LISTU_KLIZACA);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                klizaci = (List<Klizac>) too.getRezultat();
                return klizaci;
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return klizaci;
    }

    // 8
    public List<Drzava> vratiListuDrzava() {

        List<Drzava> drzave = new ArrayList<>();

        try {
			
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_LISTU_DRZAVA);
            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                drzave = (List<Drzava>) too.getRezultat();
                return drzave;
            }
            return drzave;

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return drzave;
    }

    // 9
    public List<TipTakmicenja> ucitajTipoveTakmicenja() {
        List<TipTakmicenja> tipovi = new ArrayList<>();
        try {

            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_LISTU_TIPOVA_TAKMICENJA);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                tipovi = (List<TipTakmicenja>) too.getRezultat();
                return tipovi;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return tipovi;
    }

    // 10
    public List<Korisnik> vratiListuKorisnika() {
        List<Korisnik> korisnici = new ArrayList<>();
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                korisnici = (List<Korisnik>) too.getRezultat();
                return korisnici;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return korisnici;
    }

    // 11
    public boolean zapamtiUcesceNaTakmicenju(Takmicenje takmicenje) {
        boolean sacuvano = false;
        try {
            TransferObjekatZahtev toz
                    = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_ZAPAMTI_UCESCE);
            toz.setParametar(takmicenje);
            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();

            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return sacuvano;
    }

    // 12
    public List<Takmicenje> ucitajTakmicenja() {
        List<Takmicenje> takmicenja = new ArrayList<>();
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_LISTU_TAKMICENJA);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                takmicenja = (List<Takmicenje>) too.getRezultat();
                return takmicenja;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return takmicenja;
    }

    public Takmicenje pronadjiTakmicenje(String naziv) {
        Takmicenje t = null;
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_PRONADJI_TAKMICENJE);
            toz.setParametar(naziv);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                t = (Takmicenje) too.getRezultat();
                return t;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return t;
    }

    public List<Ucesce> vratiUcescaNaTakmicenju(Takmicenje takmicenje) {

        List<Ucesce> ucesca = new ArrayList<>();
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_LISTU_UCESCA);
            toz.setParametar(takmicenje);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                ucesca = (List<Ucesce>) too.getRezultat();
                return ucesca;
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;

    }

    public Drzava pronadjiDrzavu(String drzavaID) {
        Drzava d = null;
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_PRONADJI_DRZAVU);
            toz.setParametar(drzavaID);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = (TransferObjekatOdgovor) Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                d = (Drzava) too.getRezultat();
                return d;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return d;
    }

    public ArrayList<Takmicenje> ucitajTakmicenja(Takmicenje t) {
        ArrayList<Takmicenje> takmicenja = new ArrayList<>();
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_TAKMICENJA_PO_USLOVU);
            toz.setParametar(t);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                takmicenja = (ArrayList<Takmicenje>) too.getRezultat();
                return takmicenja;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return takmicenja;
    }

    public ArrayList<Klizac> vratiKlizace(Klizac klizac) {
        ArrayList<Klizac> klizaci = new ArrayList<>();
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_VRATI_KLIZACE_PO_USLOVU);
            toz.setParametar(klizac);
            Komunikacija.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();
            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                klizaci = (ArrayList<Klizac>) too.getRezultat();
                return klizaci;
            }
        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }
        return klizaci;
    }
    
    public boolean zapamtiRezultateNaTakmicenju(Takmicenje takmicenje) {
        boolean sacuvano = false;
        try {
            TransferObjekatZahtev toz
                    = new TransferObjekatZahtev();
            toz.setOperacija(Util.OPERACIJA_ZAPAMTI_REZULTATE);
            toz.setParametar(takmicenje);
            Komunikacija.getInstanca().posaljiZahtev(toz);

            TransferObjekatOdgovor too
                    = Komunikacija.getInstanca().primiOdgovor();

            if (too.getStatus() == EnumStatus.SVE_U_REDU) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Greška! " + ex.getMessage());
            ex.printStackTrace();
        }

        return sacuvano;
    }

}
