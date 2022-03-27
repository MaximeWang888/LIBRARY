package bean.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class ADocument implements Document {

    private int idDocument;
    private String title;
    private String auteur;
    private Boolean isDisponible;
    private int dateSorti;
    private Utilisateur emprunteur;

    public ADocument(int idDocument, String title, String auteur, Boolean isDisponible, int dateSorti) {
        this.idDocument = idDocument;
        this.title = title;
        this.auteur = auteur;
        this.isDisponible = isDisponible;
        this.emprunteur = null;
        this.dateSorti = dateSorti;
    }

    @Override
    public boolean disponible() {
        return isDisponible;
    }

    @Override
    public String toString() {
        return "ADocument{" +
                "idDocument=" + idDocument +
                ", title='" + title + '\'' +
                ", auteur='" + auteur + '\'' +
                ", isDisponible=" + isDisponible +
                ", dateSorti=" + dateSorti +
                ", emprunteur=" + emprunteur +
                '}';
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        this.emprunteur = u;
        this.isDisponible = false;
    }

    @Override
    public void retour() {
        this.emprunteur = null;
        this.isDisponible = true;
    }

}
