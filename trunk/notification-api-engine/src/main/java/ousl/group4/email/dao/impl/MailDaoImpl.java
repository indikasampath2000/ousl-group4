package ousl.group4.email.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ousl.group4.email.dao.MailDao;
import ousl.group4.email.model.Mail;
import ousl.group4.email.model.MailSchedule;
import ousl.group4.util.HibernateUtil;

public class MailDaoImpl implements MailDao {

    /**
     * @param mail
     * @return
     */
    @Override
    public Mail saveMail(Mail mail) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(mail);
            session.getTransaction().commit();
            return mail;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param mail
     * @return
     */
    @Override
    public Mail updateMail(Mail mail) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(mail);
            session.getTransaction().commit();
            return mail;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param mailId
     * @return
     */
    @Override
    public Mail getMailById(Long mailId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Mail mail = (Mail) session.get(Mail.class, mailId);
            session.getTransaction().commit();
            return mail;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @return
     */
    @Override
    public List<Mail> getPendingMailNotifications() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Mail> pendingMailList = session.createCriteria(Mail.class)
                    .add(Restrictions.isNull("mailSchedule"))
                    .add(Restrictions.eq("queue", false))
                    .list();
            session.getTransaction().commit();
            return pendingMailList;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param mailSchedule
     * @return
     */
    @Override
    public List<Mail> getScheduleMailNotificationByMailSchedule(MailSchedule mailSchedule) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Mail> pendingMailList = session.createCriteria(Mail.class)
                    .add(Restrictions.isNotNull("mailSchedule"))
                    .setFetchMode("mailSchedule", FetchMode.JOIN)
                    .add(Restrictions.eq("queue", false))
                    .add(Restrictions.eq("mailSchedule", mailSchedule))
                    .list();
            session.getTransaction().commit();
            return pendingMailList;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }
}
