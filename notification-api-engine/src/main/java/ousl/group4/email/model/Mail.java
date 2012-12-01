package ousl.group4.email.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "mail")
public class Mail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")
    private Long mailId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mail", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "mail_recipient_id")
    private List<MailRecipients> mailRecipients = new ArrayList<MailRecipients>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mail", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "mail_attachment_id")
    private List<MailAttachments> mailAttachments = new ArrayList<MailAttachments>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mail", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "mail_inline_image_id")
    private List<MailInlineImages> mailInlineImages = new ArrayList<MailInlineImages>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mail_schedule_id")
    private MailSchedule mailSchedule;
    @Column(name = "mail_body")
    private String mailBody;
    @Column(name = "mail_subject")
    private String mailSubject;
    @Column(name = "mail_sender")
    private String mailSender;
    @Column(name = "mail_sent_time")
    private Date mailSentTime;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "fail_count")
    private Integer failCount;
    @Column(name = "queue")
    private Boolean queue;
    @Column(name = "velocity")
    private Boolean velocity;

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public List<MailRecipients> getMailRecipients() {
        return mailRecipients;
    }

    public void setMailRecipients(List<MailRecipients> mailRecipients) {
        this.mailRecipients = mailRecipients;
    }

    public List<MailAttachments> getMailAttachments() {
        return mailAttachments;
    }

    public void setMailAttachments(List<MailAttachments> mailAttachments) {
        this.mailAttachments = mailAttachments;
    }

    public List<MailInlineImages> getMailInlineImages() {
        return mailInlineImages;
    }

    public void setMailInlineImages(List<MailInlineImages> mailInlineImages) {
        this.mailInlineImages = mailInlineImages;
    }

    public MailSchedule getMailSchedule() {
        return mailSchedule;
    }

    public void setMailSchedule(MailSchedule mailSchedule) {
        this.mailSchedule = mailSchedule;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    public Date getMailSentTime() {
        return mailSentTime;
    }

    public void setMailSentTime(Date mailSentTime) {
        this.mailSentTime = mailSentTime;
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

    public Boolean getVelocity() {
        return velocity;
    }

    public void setVelocity(Boolean velocity) {
        this.velocity = velocity;
    }
}
