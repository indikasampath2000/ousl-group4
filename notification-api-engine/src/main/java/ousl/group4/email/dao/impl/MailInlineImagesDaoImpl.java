package ousl.group4.email.dao.impl;

import java.util.List;

import org.hibernate.Session;

import ousl.group4.email.dao.MailInlineImagesDao;
import ousl.group4.email.model.MailInlineImages;
import ousl.group4.util.HibernateUtil;

public class MailInlineImagesDaoImpl implements MailInlineImagesDao {

    /**
     * @param mailId
     * @return
     */
    @Override
    public List<MailInlineImages> getMailInlineImagesByMailId(Long mailId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<MailInlineImages> inlineImagesList = session
                    .createQuery("from MailInlineImages m where m.mail.id = :mailId").setParameter("mailId", mailId)
                    .list();
            session.getTransaction().commit();
            return inlineImagesList;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }
}
