package utils;

import config.SecurityConfig;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    // Vérifiez si cette 'request' est requise de se connecter ou non.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);

        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    // Vérifiez si cette 'request' dont le rôle est validé ou non?
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Boolean currentRoleIsBibliothecaire = AppUtils.getLoginedUser(request.getSession()).isBibliothecaire();
        String roleStr;

        if (currentRoleIsBibliothecaire){
            roleStr = "BIBLIOTHECAIRE";
            return isUrlPatternsContainingUrlPattern(urlPattern, roleStr);
        }else {
            roleStr = "ABONNE";
            return isUrlPatternsContainingUrlPattern(urlPattern, roleStr);
        }

    }

    private static boolean isUrlPatternsContainingUrlPattern(String urlPattern, String roleStr) {
        List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(roleStr);

        if (urlPatterns == null) {
            return false;
        }

        for (String url: urlPatterns) {
            if (url.equals(urlPattern))
                return true;
        }
        return false;
    }

}
