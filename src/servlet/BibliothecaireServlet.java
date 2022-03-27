package servlet;

import mediatek2022.Mediatheque;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/espace-bibliothecaire")
public class BibliothecaireServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BibliothecaireServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/espaceBibliothecaireView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("le debut du post de l'ajout document BIBLIOTHECAIRE ... ");
        String typeDocument = request.getParameter("typeDocument"); // 2 = livre, 3 = DVD, 4 = CD
        String title = request.getParameter("title");
        String auteur = request.getParameter("auteur");
        String dateSorti = request.getParameter("dateSorti");

        Mediatheque.getInstance().ajoutDocument(Integer.parseInt(typeDocument), title, dateSorti, auteur);

        doGet(request, response);
    }

}