package bean;

public class Abonne extends AUtilisateur {

    public Abonne(String name, String login, String password, Boolean isBibliothecaire, String... roles) {
        super(name, login, password, isBibliothecaire, roles);
    }
}
