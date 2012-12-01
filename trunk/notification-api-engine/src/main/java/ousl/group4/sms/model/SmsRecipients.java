package ousl.group4.sms.model;

import javax.persistence.*;

@Entity
@Table(name = "sms_recipients")
public class SmsRecipients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sms_recipient_id")
    private Long smsRecipientId;
    @Column(name = "recipient_mobile_number")
    private String recipientMobileNumber;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sms_id")
    private Sms sms;

    public Long getSmsRecipientId() {
        return smsRecipientId;
    }

    public void setSmsRecipientId(Long smsRecipientId) {
        this.smsRecipientId = smsRecipientId;
    }

    public String getRecipientMobileNumber() {
        return recipientMobileNumber;
    }

    public void setRecipientMobileNumber(String recipientMobileNumber) {
        this.recipientMobileNumber = recipientMobileNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }
}
