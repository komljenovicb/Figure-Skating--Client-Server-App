/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.EnumStatus;

/**
 *
 * @author Bojana
 */
public class TransferObjekatOdgovor implements Serializable {

    private EnumStatus status;
    private Object rezultat;
    private Object greska;
    private String poruka;
    private int operacija;

    public TransferObjekatOdgovor() {
    }

    public TransferObjekatOdgovor(EnumStatus status, Object rezultat, Object greska, String poruka, int operacija) {
        this.status = status;
        this.rezultat = rezultat;
        this.greska = greska;
        this.poruka = poruka;
        this.operacija = operacija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Object getGreska() {
        return greska;
    }

    public void setGreska(Object greska) {
        this.greska = greska;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public int getOperacija() {
        return operacija;
    }

}
