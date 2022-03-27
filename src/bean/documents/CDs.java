package bean.documents;

public class CDs extends ADocument {

    private int anneeSortie;

    public CDs(String title, String auteur, Boolean isDisponible, int anneeSortie) {
        super(title, auteur, isDisponible);
        this.anneeSortie = anneeSortie;
    }
}
