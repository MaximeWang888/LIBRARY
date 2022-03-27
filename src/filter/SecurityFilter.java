package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Utilisateur;
import request.UserRoleRequestWrapper;
import utils.AppUtils;
import utils.SecurityUtils;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // L'information de l'utilisateur est stockée dans Session
        // (Après l'achèvement de connexion).
        Utilisateur loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.name();

            // Role
            Boolean isBibliothecaire = loginedUser.isBibliothecaire();

            // Envelopper l'ancienne demande (request) par une nouvelle demande avec les informations userName et Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, isBibliothecaire, request);
        }

        // Les pages doivent être connectées.
        if (SecurityUtils.isSecurityPage(request)) {

            // Si l'utilisateur n'est pas connecté,
            // Redirect (redirigez) vers la page de connexion
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Stockez la page en cours à rediriger après l'achèvement de la connexion.
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Vérifiez si l'utilisateur a un rôle valide?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
