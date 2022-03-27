package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import mediatek2022.Utilisateur;

public class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_url_map = new HashMap<>();
    private static final Map<String, Integer> url_id_map = new HashMap<>();

    // Stockez l'information d'utilisateur dans Session.
    public static void storeLoginedUser(HttpSession session, Utilisateur loginedUser) {
        // Sur JSP il est possible d'accéder via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Obtenez des informations de l'utilisateur stockée dans Session.
    public static Utilisateur getLoginedUser(HttpSession session) {
        Utilisateur loginedUser = (Utilisateur) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = url_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            url_id_map.put(requestUri, id);
            id_url_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = id_url_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

}