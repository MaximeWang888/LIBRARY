package bean.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class ADocument implements Document {

    private String title;
    private String auteur;
    private Boolean isDisponible;
    private Utilisateur emprunteur;

    public ADocument(String title, String auteur, Boolean isDisponible) {
        this.title = title;
        this.auteur = auteur;
        this.isDisponible = isDisponible;
        this.emprunteur = null;
    }

    @Override
    public boolean disponible() {
        return isDisponible;
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
