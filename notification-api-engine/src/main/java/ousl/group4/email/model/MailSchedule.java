package ousl.group4.email.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "mail_schedule")
public class MailSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_schedule_id")
    private Long mailScheduleId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "schedule_type")
    private String scheduleType;
    @Column(name = "schedule_date_time")
    private Date scheduleDateTime;
    @Column(name = "cron_expression")
    private String cronExpression;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mailSchedule", cascade = CascadeType.ALL)
    @Column(name = "mail_id")
    private List<Mail> mails = new ArrayList<Mail>();

    public Long getMailScheduleId() {
        return mailScheduleId;
    }

    public void setMailScheduleId(Long mailScheduleId) {
        this.mailScheduleId = mailScheduleId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Date getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(Date scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }
}
