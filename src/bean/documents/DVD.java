package bean.documents;

public class DVD extends ADocument {

    private int capaciteGiga;

    public DVD(String title, String auteur, Boolean isDisponible, int capaciteGiga) {
        super(title, auteur, isDisponible);
        this.capaciteGiga = capaciteGiga;
    }
}
