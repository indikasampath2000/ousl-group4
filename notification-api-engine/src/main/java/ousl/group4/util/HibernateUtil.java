package ousl.group4.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
            sessionFactory = annotationConfiguration.configure().buildSessionFactory();
            return sessionFactory;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
