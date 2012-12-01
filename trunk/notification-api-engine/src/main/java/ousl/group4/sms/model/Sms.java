package ousl.group4.sms.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sms")
public class Sms implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sms_id")
    private Long smsId;
    @Column(name = "sms_body")
    private String smsBody;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sms", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "sms_recipient_id")
    private List<SmsRecipients> smsRecipients = new ArrayList<SmsRecipients>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sms_schedule_id")
    private SmsSchedule smsSchedule;
    @Column(name = "sms_sender")
    private String smsSender;
    @Column(name = "sms_sent_time")
    private Date smsSentTime;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "fail_count")
    private Integer failCount;
    @Column(name = "queue")
    private Boolean queue;

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public List<SmsRecipients> getSmsRecipients() {
        return smsRecipients;
    }

    public void setSmsRecipients(List<SmsRecipients> smsRecipients) {
        this.smsRecipients = smsRecipients;
    }

    public SmsSchedule getSmsSchedule() {
        return smsSchedule;
    }

    public void setSmsSchedule(SmsSchedule smsSchedule) {
        this.smsSchedule = smsSchedule;
    }

    public String getSmsSender() {
        return smsSender;
    }

    public void setSmsSender(String smsSender) {
        this.smsSender = smsSender;
    }

    public Date getSmsSentTime() {
        return smsSentTime;
    }

    public void setSmsSentTime(Date smsSentTime) {
        this.smsSentTime = smsSentTime;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Boolean getQueue() {
        return queue;
    }

    public void setQueue(Boolean queue) {
        this.queue = queue;
    }
}
