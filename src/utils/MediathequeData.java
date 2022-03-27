package utils;

import bean.users.Abonne;
import bean.users.Bibliothecaire;
import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.PersistentMediatheque;
import mediatek2022.Utilisateur;

import java.sql.*;
import java.util.List;


// Classe mono-instance dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {

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
		try {
			return getInDB(login, password);
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
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document

	}

	private Utilisateur getInDB(String login, String password) throws SQLException {
		String query = "SELECT * from utilisateur";

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
		return null;
	}

}
