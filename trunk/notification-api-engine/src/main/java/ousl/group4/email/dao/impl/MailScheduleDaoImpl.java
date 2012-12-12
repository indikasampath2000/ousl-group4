package ousl.group4.email.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ousl.group4.email.dao.MailScheduleDao;
import ousl.group4.email.model.MailSchedule;
import ousl.group4.util.HibernateUtil;

public class MailScheduleDaoImpl implements MailScheduleDao{
    /**
     * persist mail schedule
     *
     * @param mailSchedule
     */
    @Override
    public MailSchedule saveMailSchedule(MailSchedule mailSchedule) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(mailSchedule);
            session.getTransaction().commit();
            return mailSchedule;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    /**
     * return MailSchedule object if job already exist
     *
     * @param jobName
     * @return
     */
    @Override
    public MailSchedule isScheduleJobExist(String jobName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            MailSchedule mailSchedule = (MailSchedule)session.createCriteria(MailSchedule.class).add(Restrictions.eq("jobName", jobName)).uniqueResult();
            session.getTransaction().commit();
            return mailSchedule;
        } catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        }
    }
}
