package bean.documents;

import java.sql.SQLException;

public class CDs extends ADocument {

    public CDs(int idDocument, String title, String auteur, Boolean isDisponible, int dateSorti) throws SQLException {
        super(idDocument, title, auteur, isDisponible, dateSorti);
    }

}
