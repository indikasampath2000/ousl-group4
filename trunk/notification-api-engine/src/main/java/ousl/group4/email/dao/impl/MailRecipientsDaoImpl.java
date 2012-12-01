package ousl.group4.email.dao.impl;

import java.util.List;

import org.hibernate.Session;

import ousl.group4.email.dao.MailRecipientsDao;
import ousl.group4.email.model.MailRecipients;
import ousl.group4.util.HibernateUtil;

public class MailRecipientsDaoImpl implements MailRecipientsDao {

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailRecipients> getMailRecipientsByMailId(Long mailId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<MailRecipients> recipientsList = session
                    .createQuery("from MailRecipients m where m.mail.id = :mailId and m.status = false")
                    .setParameter("mailId", mailId).list();
            session.getTransaction().commit();
            return recipientsList;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * @param mailRecipients
     */
    @Override
    public void updateMailRecipients(MailRecipients mailRecipients) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(mailRecipients);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
