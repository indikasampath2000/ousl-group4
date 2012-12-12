package ousl.group4.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ousl.group4.email.model.*;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.sms.model.SmsSchedule;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory
			Configuration configuration = new Configuration()
					.setProperty("hibernate.connection.driver_class", "org.h2.Driver")
					.setProperty("hibernate.connection.url",
							"jdbc:h2:tcp://localhost/~/data/notification_api;MVCC=TRUE")
					.setProperty("hibernate.connection.username", "sa")
					.setProperty("hibernate.connection.password", "")
					.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
					.setProperty("hibernate.current_session_context_class", "thread")
                    .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")
					// .setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.c3p0.acquire_increment", "1")
					.setProperty("hibernate.c3p0.idle_test_period", "15")
                    .setProperty("hibernate.c3p0.max_size", "50")
					.setProperty("hibernate.c3p0.max_statements", "0")
                    .setProperty("hibernate.c3p0.min_size", "10")
					.setProperty("hibernate.c3p0.timeout", "15")
                    .addAnnotatedClass(Mail.class)
					.addAnnotatedClass(MailAttachments.class)
                    .addAnnotatedClass(MailRecipients.class)
					.addAnnotatedClass(MailInlineImages.class)
                    .addAnnotatedClass(MailSchedule.class)
					.addAnnotatedClass(Sms.class)
                    .addAnnotatedClass(SmsRecipients.class)
					.addAnnotatedClass(SmsSchedule.class);
			final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
