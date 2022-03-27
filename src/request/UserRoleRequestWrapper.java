package request;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private Boolean isBibliothecaire;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, Boolean isBibliothecaire, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.isBibliothecaire = isBibliothecaire;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (isBibliothecaire == null) {
            return this.realRequest.isUserInRole(role);
        }
        return true;
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

        // Make an anonymous implementation to just return our user
        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }

}
