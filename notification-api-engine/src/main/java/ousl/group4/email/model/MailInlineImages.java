package ousl.group4.email.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "mail_inline_images")
public class MailInlineImages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_inline_image_id")
    private Long mailInlineImageId;
    @Column(name = "image_with_path")
    private String imageWithAbsoluteFilePath;
    @Column(name = "content_id")
    private String contentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id")
    private Mail mail;

    public Long getMailInlineImageId() {
        return mailInlineImageId;
    }

    public void setMailInlineImageId(Long mailInlineImageId) {
        this.mailInlineImageId = mailInlineImageId;
    }

    public String getImageWithAbsoluteFilePath() {
        return imageWithAbsoluteFilePath;
    }

    public void setImageWithAbsoluteFilePath(String imageWithAbsoluteFilePath) {
        this.imageWithAbsoluteFilePath = imageWithAbsoluteFilePath;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
