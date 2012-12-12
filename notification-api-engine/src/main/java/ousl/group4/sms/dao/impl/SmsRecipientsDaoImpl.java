package ousl.group4.sms.dao.impl;

import org.hibernate.Session;
import ousl.group4.sms.dao.SmsRecipientsDao;
import ousl.group4.sms.model.SmsRecipients;
import ousl.group4.util.HibernateUtil;

import java.util.List;

public class SmsRecipientsDaoImpl implements SmsRecipientsDao{
    /**
     * @param smsId
     * @return
     */
    @Override
    public List<SmsRecipients> getSmsRecipientsBySmsId(Long smsId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<SmsRecipients> recipientsList = session
                    .createQuery("from SmsRecipients s where s.sms.id = :smsId and s.status = false")
                    .setParameter("smsId", smsId).list();
            session.getTransaction().commit();
            return recipientsList;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * @param smsRecipients
     */
    @Override
    public void updateSmsRecipients(SmsRecipients smsRecipients) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(smsRecipients);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
        }
    }
}
