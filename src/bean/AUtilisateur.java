package bean;

import mediatek2022.Utilisateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AUtilisateur implements Utilisateur {

    private String name;
    private String login;
    private String password;
    private boolean isBibliothecaire;

    private List<String> roles;

    public AUtilisateur(String name, String login, String password, boolean isBibliothecaire, String... roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.isBibliothecaire = isBibliothecaire;

        this.roles = new ArrayList<>();
        if (roles != null) {
            this.roles.addAll(Arrays.asList(roles));
        }
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

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
