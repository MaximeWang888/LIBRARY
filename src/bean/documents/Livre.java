package bean.documents;

public class Livre extends ADocument {

    private int nbPages;

    public Livre(String title, String auteur, Boolean isDisponible, int nbPages) {
        super(title, auteur, isDisponible);
        this.nbPages = nbPages;
    }
}
