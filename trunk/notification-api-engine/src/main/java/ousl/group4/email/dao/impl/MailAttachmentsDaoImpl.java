package ousl.group4.email.dao.impl;

import java.util.List;

import org.hibernate.Session;

import ousl.group4.email.dao.MailAttachmentsDao;
import ousl.group4.email.model.MailAttachments;
import ousl.group4.util.HibernateUtil;

public class MailAttachmentsDaoImpl implements MailAttachmentsDao {

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailAttachments> getMailAttachmentsByMailId(Long mailId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<MailAttachments> mailAttachmentsList = session
                    .createQuery("from MailAttachments m where m.mail.id = :mailId").setParameter("mailId", mailId)
                    .list();
            session.getTransaction().commit();
            return mailAttachmentsList;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }
}
