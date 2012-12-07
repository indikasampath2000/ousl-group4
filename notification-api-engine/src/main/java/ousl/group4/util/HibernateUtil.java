package ousl.group4.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import ousl.group4.email.model.*;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.sms.model.SmsSchedule;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Properties properties;

    public static SessionFactory getSessionFactory() {
        try {

            properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            properties.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/data/notification_api;MVCC=TRUE");
            properties.setProperty("hibernate.connection.username", "sa");
            properties.setProperty("hibernate.connection.password", "");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            //properties.setProperty("hibernate.hbm2ddl.auto", "update");
            Configuration configuration = new Configuration();
            configuration.configure()
                    .addAnnotatedClass(Mail.class)
                    .addAnnotatedClass(MailAttachments.class)
                    .addAnnotatedClass(MailRecipients.class)
                    .addAnnotatedClass(MailInlineImages.class)
                    .addAnnotatedClass(MailSchedule.class)
                    .addAnnotatedClass(Sms.class)
                    .addAnnotatedClass(SmsRecipients.class)
                    .addAnnotatedClass(SmsSchedule.class);

            serviceRegistry = new ServiceRegistryBuilder().applySettings(properties)
                    .buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
