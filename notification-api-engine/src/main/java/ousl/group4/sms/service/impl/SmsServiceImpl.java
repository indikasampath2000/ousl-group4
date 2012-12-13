package ousl.group4.sms.service.impl;

import ousl.group4.sms.dao.SmsDao;
import ousl.group4.sms.dao.impl.SmsDaoImpl;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.model.SmsSchedule;
import ousl.group4.sms.service.SmsService;

import java.util.List;

public class SmsServiceImpl implements SmsService{

    private SmsDao smsDao = new SmsDaoImpl();

    /**
     * @param sms
     * @return
     */
    @Override
    public Sms saveSms(Sms sms) {
        return smsDao.saveSms(sms);
    }

    /**
     * @param sms
     * @return
     */
    @Override
    public Sms updateSms(Sms sms) {
        return smsDao.updateSms(sms);
    }

    /**
     * @param smsId
     * @return
     */
    @Override
    public Sms getSmsById(Long smsId) {
        return smsDao.getSmsById(smsId);
    }

    /**
     * @return
     */
    @Override
    public List<Sms> getPendingSmsNotifications() {
        return smsDao.getPendingSmsNotifications();
    }

    /**
     * @param smsSchedule
     * @return
     */
    @Override
    public List<Sms> getScheduleMailNotificationByMailSchedule(SmsSchedule smsSchedule) {
        return smsDao.getScheduleMailNotificationByMailSchedule(smsSchedule);
    }

    /**
     * @return
     */
    @Override
    public List<Sms> getFinishedSmsNotifications() {
        return smsDao.getFinishedSmsNotifications();
    }
}
