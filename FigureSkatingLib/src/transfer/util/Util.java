/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Bojana
 */
public interface Util {

    // sistemske operacije
    
    public static final int OPERACIJA_PROVERI_PODATKE = 1;
    public static final int OPERACIJA_VRATI_LISTU_TIPOVA_TAKMICENJA = 2;
    public static final int OPERACIJA_VRATI_LISTU_KLIZACA = 3;
    public static final int OPERACIJA_KREIRAJ_TAKMICENJE = 4;
    public static final int OPERACIJA_ZAPAMTI_UCESCE = 5;
    public static final int OPERACIJA_VRATI_LISTU_TAKMICENJA = 6;
    public static final int OPERACIJA_PRONADJI_TAKMICENJA = 7;
    public static final int OPERACIJA_PRONADJI_TAKMICENJE = 8;
    public static final int OPERACIJA_KREIRAJ_KLIZACA = 9;
    public static final int OPERACIJA_PRONADJI_KLIZACE = 10;
    public static final int OPERACIJA_VRATI_KLIZACA = 11;
    public static final int OPERACIJA_IZMENI_KLIZACA = 12;
    public static final int OPERACIJA_OBRISI_KLIZACA = 13;
    public static final int OPERACIJA_VRATI_LISTU_DRZAVA = 14;
    public static final int OPERACIJA_ZAPAMTI_REZULTATE = 15;
    public static final int OPERACIJA_VRATI_LISTU_UCESCA = 16;
    public static final int OPERACIJA_PRONADJI_DRZAVU = 17;
    public static final int OPERACIJA_TAKMICENJA_PO_USLOVU = 18;
    public static final int OPERACIJA_VRATI_KLIZACE_PO_USLOVU = 19;

    // *********************************************************
    public static final int ODJAVI_SE = 20;
    public static final int ODJAVI_SVE_KLIJENTE = 404;

    // **********************************************************
    public static final String IP_ADRESA = "ipadresa";
    public static final String BROJ_PORTA = "brojPorta";
    public static final String MAP_KEY_SOCKET = "socket";

    // *********************************************************
    public static String OPERACIJA_KREIRAJ = "k2";
    public static String USLOV_PRONADJI_TAKMICENJE = "t1";
    public static String USLOV_VRATI_KLIZACA = "k3";
    public static String USLOV_PRONADJI_KORISNIKA = "K4";
    public static String USLOV_PRONADJI_DRZAVU = "d1";

}
