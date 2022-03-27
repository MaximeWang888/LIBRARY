package bean.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ADocument implements Document {

    private int idDocument;
    private String title;
    private String auteur;
    private Boolean isDisponible;
    private int dateSorti;
    private String emprunteur;
    private Connection connection;

    public ADocument(int idDocument, String title, String auteur, Boolean isDisponible, int dateSorti)
            throws SQLException {
        this.idDocument = idDocument;
        this.title = title;
        this.auteur = auteur;
        this.isDisponible = isDisponible;
        this.emprunteur = "null";
        this.dateSorti = dateSorti;
        String dbUrl = "jdbc:mysql://localhost:3306/appserv";
        String dbUser = "root";
        String dbPassword = "rootsql";
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); // Connection à la db
    }

    @Override
    public boolean disponible() {
        return this.isDisponible;
    }

    @Override
    public void emprunt(Utilisateur u) {
        this.emprunteur = u.name();
        this.isDisponible = false;
        String query = "UPDATE document SET isDisponible = 0, emprunteur = ? WHERE idDocument = ?";

        try {
            PreparedStatement dynStatement = connection.prepareStatement(query);
            dynStatement.setString(1, this.emprunteur);
            dynStatement.setInt(2, this.idDocument);
            dynStatement.executeUpdate();
            System.out.println("Le document a bien ete emprunter par l'abonne");
            dynStatement.close();
        } catch (SQLException | NullPointerException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void retour() {
        this.emprunteur = null;
        this.isDisponible = true;
        String query = "UPDATE document SET isDisponible = 1, emprunteur = null WHERE idDocument = ?";

        try {
            PreparedStatement dynStatement = connection.prepareStatement(query);
            dynStatement.setInt(1, this.idDocument);

            dynStatement.executeUpdate();
            System.out.println("Le document a bien ete retourner a la mediatheque");
            dynStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
}
