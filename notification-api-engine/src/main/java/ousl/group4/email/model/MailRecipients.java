package ousl.group4.email.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "mail_recipients")
public class MailRecipients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_recipient_id")
    private Long mailRecipientId;
    @Column(name = "mail_send_type")
    private Integer mailSendType;
    @Column(name = "mail_address")
    private String mailAddress;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id")
    private Mail mail;

    public Long getMailRecipientId() {
        return mailRecipientId;
    }

    public void setMailRecipientId(Long mailRecipientId) {
        this.mailRecipientId = mailRecipientId;
    }

    public Integer getMailSendType() {
        return mailSendType;
    }

    public void setMailSendType(Integer mailSendType) {
        this.mailSendType = mailSendType;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
