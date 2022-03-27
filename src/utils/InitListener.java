package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// Injection de dépendances des classes au moment de l'ouverture de tomcat
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("DEBUT DE L'INJECTION DE DEPENDENCE ....");
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        try {
            Class.forName("utils.MediathequeData");
            System.out.println("LA CLASSE MEDIATHEQUE_DATA A BIEN ETE INSTANCIEE");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}