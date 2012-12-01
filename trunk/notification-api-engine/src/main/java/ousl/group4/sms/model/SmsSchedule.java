package ousl.group4.sms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sms_schedule")
public class SmsSchedule implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sms_schedule_id")
    private Long smsScheduleId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "schedule_type")
    private String scheduleType;
    @Column(name = "schedule_date_time")
    private Date scheduleDateTime;
    @Column(name = "cron_expression")
    private String cronExpression;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "smsSchedule", cascade = CascadeType.ALL)
    @Column(name = "sms_id")
    private List<Sms> sms = new ArrayList<Sms>();

    public Long getSmsScheduleId() {
        return smsScheduleId;
    }

    public void setSmsScheduleId(Long smsScheduleId) {
        this.smsScheduleId = smsScheduleId;
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

    public List<Sms> getSms() {
        return sms;
    }

    public void setSms(List<Sms> sms) {
        this.sms = sms;
    }
}
