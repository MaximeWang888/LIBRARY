package bean.documents;

public class Livre extends ADocument {

    public Livre(int idDocument, String title, String auteur, Boolean isDisponible, int dateSorti) {
        super(idDocument, title, auteur, isDisponible, dateSorti);
    }

}
