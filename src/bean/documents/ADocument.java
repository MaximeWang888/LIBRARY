package bean.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class ADocument implements Document {

    @Override
    public boolean disponible() {
        return false;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {

    }

    @Override
    public void retour() {

    }

}
