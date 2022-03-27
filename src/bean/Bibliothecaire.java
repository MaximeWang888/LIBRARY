package bean;

public class Bibliothecaire extends AUtilisateur {

    public Bibliothecaire(String name, String login, String password, Boolean isBibliothecaire, String... roles) {
        super(name, login, password, isBibliothecaire, roles);
    }

}
