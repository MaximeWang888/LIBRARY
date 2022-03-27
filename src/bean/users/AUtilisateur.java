package bean.users;

import mediatek2022.Utilisateur;

import java.util.List;

public abstract class AUtilisateur implements Utilisateur {

    private String name;
    private boolean isBibliothecaire;

    public AUtilisateur(String name, boolean isBibliothecaire) {
        this.name = name;
        this.isBibliothecaire = isBibliothecaire;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isBibliothecaire() {
        return this.isBibliothecaire;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }

}
