package utils;

import bean.documents.CDs;
import bean.documents.DVD;
import bean.documents.Livre;
import bean.users.Abonne;
import bean.users.Bibliothecaire;
import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.PersistentMediatheque;
import mediatek2022.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Classe mono-instance dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque{

	static {
		try {
			Mediatheque.getInstance().setData(new MediathequeData());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private Connection connection;

	private MediathequeData() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/appserv";
		String dbUser = "root";
		String dbPassword = "rootsql";
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); // Connection à la db
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		return null;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String query = "SELECT * from utilisateur";

		try {
			PreparedStatement dynStatement = connection.prepareStatement(query);

			ResultSet resultStatement = dynStatement.executeQuery();

			while (resultStatement.next()) {
				// Depuis la db recuperee les donnees
				String rName = resultStatement.getString("nomUtilisateur");
				String rLogin = resultStatement.getString("login");
				String rPassword = resultStatement.getString("password");
				Boolean rIsAbonne = resultStatement.getBoolean("isAbonne");

				// Comparer les login et password à celui entrer par l'utilisateur
				// Et renvoyer le bon Utilisateur
				if (rLogin.equals(login) && rPassword.equals(password))
					if (rIsAbonne)
						return new Abonne(rName, false);
					else
						return new Bibliothecaire(rName, true);

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String query = "SELECT * from document";

		try {
			PreparedStatement dynStatement = connection.prepareStatement(query);

			ResultSet resultStatement = dynStatement.executeQuery();

			while (resultStatement.next()) {
				// Depuis la db recuperee les donnees d'un document
				int idDocument = resultStatement.getInt("idDocument");
				String title = resultStatement.getString("titre");
				String auteur = resultStatement.getString("auteur");
				Boolean isDisponible = resultStatement.getBoolean("isDisponible");
				Boolean isLivre = resultStatement.getBoolean("isLivre");
				Boolean isDVD = resultStatement.getBoolean("isDVD");
				Boolean isCD = resultStatement.getBoolean("isCD");

				// Comparer les login et password à celui entrer par l'utilisateur
				// Et renvoyer le bon Utilisateur
				if (idDocument == numDocument)
					if (isLivre) {
						int nbPages = resultStatement.getInt("nbPages");
						return new Livre(title, auteur, isDisponible, nbPages);
					}
					else if (isDVD) {
						int capaciteGiga = resultStatement.getInt("capaciteGiga");
						return new DVD(title, auteur, isDisponible, capaciteGiga);
					}
					else if (isCD){
						int anneeSortie = resultStatement.getInt("anneeSortie");
						return new CDs(title, auteur, isDisponible, anneeSortie);
					}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		List<Integer> listInt = new ArrayList<>();
		listInt.add(2); listInt.add(3); listInt.add(4);
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		String query = "INSERT INTO document (idDocument, isLivre, isDVD, isCD, titre, dateSorti, auteur) VALUES " +
				"(?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement dynStatement = connection.prepareStatement(query);
			int idGenerator = (int)(Math.random() * 999999999) + 1;
			// idDOCUMENT
			dynStatement.setInt(1, idGenerator); // 1 -> idDocument
			// TYPE
			for (int typeId:listInt) { // 1
				if (type != typeId)
					dynStatement.setInt(typeId, 0);
				else
					dynStatement.setInt(type, 1);
			}
			// TITRE
			dynStatement.setString(5, (String) args[0]);
			// DATESORTI
			dynStatement.setInt(6, new Integer((String) args[1])); // 6 -> dateSorti
			// AUTEUR
			dynStatement.setString(7, (String) args[2]); // 7 -> auteur

//			System.out.println(getDocument(idGenerator).toString());
			System.out.println("Le document a ete ajoutee a la db");
			dynStatement.executeUpdate();
			dynStatement.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

}
