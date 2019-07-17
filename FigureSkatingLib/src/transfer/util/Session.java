/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

import domain.Korisnik;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bojana
 */
public class Session {

    private static Session instance;
    private Korisnik currentUser;
    private int currentUseCase;
    private final Map<String, Object> useCaseParams;

    private Session() {
        this.useCaseParams = new HashMap<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Korisnik getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Korisnik currentUser) {
        this.currentUser = currentUser;
    }

    public int getCurrentUseCase() {
        return currentUseCase;
    }

    public void setCurrentUseCase(int currentUseCase) {
        this.currentUseCase = currentUseCase;
    }

    public Map<String, Object> getUseCaseParams() {
        return useCaseParams;
    }
}
