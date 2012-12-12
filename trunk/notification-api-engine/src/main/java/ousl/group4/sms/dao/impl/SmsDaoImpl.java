package ousl.group4.sms.dao.impl;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ousl.group4.sms.dao.SmsDao;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsSchedule;
import ousl.group4.util.HibernateUtil;

import java.util.List;

public class SmsDaoImpl implements SmsDao{
    /**
     * @param sms
     * @return
     */
    @Override
    public Sms saveSms(Sms sms) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(sms);
            session.getTransaction().commit();
            return sms;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param sms
     * @return
     */
    @Override
    public Sms updateSms(Sms sms) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(sms);
            session.getTransaction().commit();
            return sms;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param smsId
     * @return
     */
    @Override
    public Sms getSmsById(Long smsId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Sms sms = (Sms) session.get(Sms.class, smsId);
            session.getTransaction().commit();
            return sms;
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
    public List<Sms> getPendingSmsNotifications() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Sms> pendingSmsList = session.createCriteria(Sms.class)
                    .add(Restrictions.isNull("smsSchedule"))
                    .add(Restrictions.eq("queue", false))
                    .list();
            session.getTransaction().commit();
            return pendingSmsList;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param smsSchedule
     * @return
     */
    @Override
    public List<Sms> getScheduleMailNotificationByMailSchedule(SmsSchedule smsSchedule) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Sms> pendingSmsList = session.createCriteria(Sms.class)
                    .add(Restrictions.isNotNull("smsSchedule"))
                    .setFetchMode("smsSchedule", FetchMode.JOIN)
                    .add(Restrictions.eq("queue", false))
                    .add(Restrictions.eq("smsSchedule", smsSchedule))
                    .list();
            session.getTransaction().commit();
            return pendingSmsList;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }
}
