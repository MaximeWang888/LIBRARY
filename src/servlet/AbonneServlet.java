package servlet;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;
import utils.AppUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/espace-abonne")
public class AbonneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AbonneServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Document> documentList = Mediatheque.getInstance().tousLesDocumentsDisponibles();

        request.setAttribute("documentList", documentList);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/espaceAbonneView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("le debut du post ABONNE ... ");

        String emprunterDoc = request.getParameter("emprunterDoc");
        String rendreDoc = request.getParameter("rendreDoc");
        Utilisateur currentUser = AppUtils.getLoginedUser(request.getSession());

        Mediatheque mediatheque = Mediatheque.getInstance();
        Document documentEmprunter = mediatheque.getDocument(Integer.parseInt(emprunterDoc));
        Document documentRendu = mediatheque.getDocument(Integer.parseInt(rendreDoc));

        try {
            for (Document doc: mediatheque.tousLesDocumentsDisponibles()) {
                if (doc.toString().contains("idDocument=" + emprunterDoc)) {
                    mediatheque.emprunt(documentEmprunter, currentUser);
                }
                else if (doc.toString().contains("idDocument=" + rendreDoc)) {
                    mediatheque.retour(documentRendu, currentUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

}