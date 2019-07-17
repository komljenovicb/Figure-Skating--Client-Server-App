/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Bojana
 */
public class TransferObjekatZahtev implements Serializable {

    private int operacija;
    private Object parametar;

    public TransferObjekatZahtev() {
    }

    public TransferObjekatZahtev(int operacija, Object podatak) {
        this.operacija = operacija;
        this.parametar = podatak;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object podatak) {
        this.parametar = podatak;
    }

}
