package bean.documents;

import java.sql.SQLException;

public class DVD extends ADocument {

    public DVD(int idDocument, String title, String auteur, Boolean isDisponible, int dateSorti) throws SQLException {
        super(idDocument, title, auteur, isDisponible, dateSorti);
    }

}
