package ousl.group4.email.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "mail_attachments")
public class MailAttachments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_attachment_id")
    private Long mailAttachmentId;
    @Column(name = "file_with_path")
    private String fileWithAbsoluteFilePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id")
    private Mail mail;

    public Long getMailAttachmentId() {
        return mailAttachmentId;
    }

    public void setMailAttachmentId(Long mailAttachmentId) {
        this.mailAttachmentId = mailAttachmentId;
    }

    public String getFileWithAbsoluteFilePath() {
        return fileWithAbsoluteFilePath;
    }

    public void setFileWithAbsoluteFilePath(String fileWithAbsoluteFilePath) {
        this.fileWithAbsoluteFilePath = fileWithAbsoluteFilePath;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
