package ousl.group4.email.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ousl.group4.exception.NotificationAPIException;
import ousl.group4.email.model.*;
import ousl.group4.email.service.MailScheduleService;
import ousl.group4.email.service.MailSender;
import ousl.group4.email.service.MailService;
import ousl.group4.jobscheduler.ScheduleMailNotificationJob;

public class MailSenderImpl implements MailSender {

    private MailService mailService = new MailServiceImpl();

    /**
     * @param mailMap Must contains the below key names with relevant values
     *                sender : the sender email address. cannot be null
     *                recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                subject : the subject of email. cannot be null
     *                mailBody : the body of email. cannot be null
     *                attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     * @throws ousl.group4.exception.NotificationAPIException
     *
     */
    @Override
    public void send(Map<String, Object> mailMap) throws NotificationAPIException {
        String sender = (String) mailMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[][] recipients = (String[][]) mailMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    if (recipients[i][j].isEmpty()) {
                        throw new NotificationAPIException("recipient is null or empty");
                    }
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                            } else {
                                throw new NotificationAPIException("invalid send type");
                            }
                        }
                    }
                }
            }
        }
        String subject = (String) mailMap.get("subject");
        if (subject == null || subject.isEmpty()) {
            throw new NotificationAPIException("subject is null or empty");
        }
        String mailBody = (String) mailMap.get("mailBody");
        if (mailBody == null) {
            throw new NotificationAPIException("mail body is null");
        }
        String[] attachments = (String[]) mailMap.get("attachments");
        String[][] inlineImages = (String[][]) mailMap.get("inlineImages");

        Mail mail = new Mail();
        mail.setMailSender(sender);
        List<MailRecipients> mailRecipients = new ArrayList<MailRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            MailRecipients receiver = new MailRecipients();
            receiver.setStatus(false);
            receiver.setMail(mail);
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    receiver.setMailAddress(recipients[i][j]);
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                        receiver.setMailSendType(1);
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                            receiver.setMailSendType(2);
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                                receiver.setMailSendType(3);
                            } else {
                            }
                        }
                    }
                }
                mailRecipients.add(receiver);
            }
        }
        mail.setMailRecipients(mailRecipients);
        mail.setMailSubject(subject);
        if (attachments != null && attachments.length > 0) {
            List<MailAttachments> mailAttachments = new ArrayList<MailAttachments>();
            for (int i = 0; i < attachments.length; i++) {
                if (attachments[i] == null || attachments[i].isEmpty()) {
                    throw new NotificationAPIException("attachment is null or empty");
                } else {
                    MailAttachments attach = new MailAttachments();
                    attach.setFileWithAbsoluteFilePath(attachments[i]);
                    attach.setMail(mail);
                    mailAttachments.add(attach);
                }
            }
            mail.setMailAttachments(mailAttachments);
        }
        if (inlineImages != null && inlineImages.length > 0) {
            List<MailInlineImages> mailInlineImages = new ArrayList<MailInlineImages>();
            for (int i = 0; i < inlineImages.length; i++) {
                MailInlineImages image = new MailInlineImages();
                image.setMail(mail);
                for (int j = 0; j <= inlineImages.length; j++) {
                    if (j == 0) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("inline image null or empty");
                        }
                        image.setImageWithAbsoluteFilePath(inlineImages[i][j]);
                    }
                    if (j == 1) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("content id null or empty");
                        }
                        image.setContentId(inlineImages[i][j]);
                    }
                }
                mailInlineImages.add(image);
            }
            mail.setMailInlineImages(mailInlineImages);
        }
        mail.setMailBody(mailBody);
        mail.setCreatedOn(new Date());
        mail.setStatus(false);
        mail.setQueue(false);
        mail.setVelocity(false);
        mailService.saveMail(mail);
    }

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     *                     other key value pair of velocity template's place holders, cannot be null
     * @param templatePath classpath of template. cannot be null
     * @throws Exception
     */
    @Override
    public void send(Map<String, Object> mailMap, String templatePath) throws Exception {
        String sender = (String) mailMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[][] recipients = (String[][]) mailMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    if (recipients[i][j].isEmpty()) {
                        throw new NotificationAPIException("recipient is null or empty");
                    }
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                            } else {
                                throw new NotificationAPIException("invalid send type");
                            }
                        }
                    }
                }
            }
        }
        String subject = (String) mailMap.get("subject");
        if (subject == null || subject.isEmpty()) {
            throw new NotificationAPIException("subject is null or empty");
        }
        String[] attachments = (String[]) mailMap.get("attachments");
        String[][] inlineImages = (String[][]) mailMap.get("inlineImages");

        Mail mail = new Mail();
        mail.setMailSender(sender);
        List<MailRecipients> mailRecipients = new ArrayList<MailRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            MailRecipients receiver = new MailRecipients();
            receiver.setStatus(false);
            receiver.setMail(mail);
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    receiver.setMailAddress(recipients[i][j]);
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                        receiver.setMailSendType(1);
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                            receiver.setMailSendType(2);
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                                receiver.setMailSendType(3);
                            } else {
                            }
                        }
                    }
                }
                mailRecipients.add(receiver);
            }
        }
        mail.setMailRecipients(mailRecipients);
        mail.setMailSubject(subject);
        // Setup velocity engine
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        // Extract template into String object
        Template template = velocityEngine.getTemplate(templatePath);
        VelocityContext context = new VelocityContext(mailMap);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String mailBody = writer.toString();
        mail.setMailBody(mailBody);
        if (attachments != null && attachments.length > 0) {
            List<MailAttachments> mailAttachments = new ArrayList<MailAttachments>();
            for (int i = 0; i < attachments.length; i++) {
                if (attachments[i] == null || attachments[i].isEmpty()) {
                    throw new NotificationAPIException("attachment is null or empty");
                } else {
                    MailAttachments attach = new MailAttachments();
                    attach.setFileWithAbsoluteFilePath(attachments[i]);
                    attach.setMail(mail);
                    mailAttachments.add(attach);
                }
            }
            mail.setMailAttachments(mailAttachments);
        }
        if (inlineImages != null && inlineImages.length > 0) {
            List<MailInlineImages> mailInlineImages = new ArrayList<MailInlineImages>();
            for (int i = 0; i < inlineImages.length; i++) {
                MailInlineImages image = new MailInlineImages();
                image.setMail(mail);
                for (int j = 0; j <= inlineImages.length; j++) {
                    if (j == 0) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("inline image null or empty");
                        }
                        image.setImageWithAbsoluteFilePath(inlineImages[i][j]);
                    }
                    if (j == 1) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("content id null or empty");
                        }
                        image.setContentId(inlineImages[i][j]);
                    }
                }
                mailInlineImages.add(image);
            }
            mail.setMailInlineImages(mailInlineImages);
        }
        mail.setCreatedOn(new Date());
        mail.setStatus(false);
        mail.setQueue(false);
        mail.setVelocity(true);
        mailService.saveMail(mail);
    }

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     mailBody : the body of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     * @param mailSchedule contains schedule job data
     * @throws Exception
     */
    @Override
    public void scheduleMail(Map<String, Object> mailMap, MailSchedule mailSchedule) throws NotificationAPIException {
        String sender = (String) mailMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[][] recipients = (String[][]) mailMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    if (recipients[i][j].isEmpty()) {
                        throw new NotificationAPIException("recipient is null or empty");
                    }
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                            } else {
                                throw new NotificationAPIException("invalid send type");
                            }
                        }
                    }
                }
            }
        }
        String subject = (String) mailMap.get("subject");
        if (subject == null || subject.isEmpty()) {
            throw new NotificationAPIException("subject is null or empty");
        }
        String mailBody = (String) mailMap.get("mailBody");
        if (mailBody == null) {
            throw new NotificationAPIException("mail body is null");
        }
        String[] attachments = (String[]) mailMap.get("attachments");
        String[][] inlineImages = (String[][]) mailMap.get("inlineImages");

        Mail mail = new Mail();
        mail.setMailSender(sender);
        List<MailRecipients> mailRecipients = new ArrayList<MailRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            MailRecipients receiver = new MailRecipients();
            receiver.setStatus(false);
            receiver.setMail(mail);
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    receiver.setMailAddress(recipients[i][j]);
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                        receiver.setMailSendType(1);
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                            receiver.setMailSendType(2);
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                                receiver.setMailSendType(3);
                            } else {
                            }
                        }
                    }
                }
                mailRecipients.add(receiver);
            }
        }
        mail.setMailRecipients(mailRecipients);
        mail.setMailSubject(subject);
        if (attachments != null && attachments.length > 0) {
            List<MailAttachments> mailAttachments = new ArrayList<MailAttachments>();
            for (int i = 0; i < attachments.length; i++) {
                if (attachments[i] == null || attachments[i].isEmpty()) {
                    throw new NotificationAPIException("attachment is null or empty");
                } else {
                    MailAttachments attach = new MailAttachments();
                    attach.setFileWithAbsoluteFilePath(attachments[i]);
                    attach.setMail(mail);
                    mailAttachments.add(attach);
                }
            }
            mail.setMailAttachments(mailAttachments);
        }
        if (inlineImages != null && inlineImages.length > 0) {
            List<MailInlineImages> mailInlineImages = new ArrayList<MailInlineImages>();
            for (int i = 0; i < inlineImages.length; i++) {
                MailInlineImages image = new MailInlineImages();
                image.setMail(mail);
                for (int j = 0; j <= inlineImages.length; j++) {
                    if (j == 0) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("inline image null or empty");
                        }
                        image.setImageWithAbsoluteFilePath(inlineImages[i][j]);
                    }
                    if (j == 1) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("content id null or empty");
                        }
                        image.setContentId(inlineImages[i][j]);
                    }
                }
                mailInlineImages.add(image);
            }
            mail.setMailInlineImages(mailInlineImages);
        }
        if (mailSchedule.getJobName() == null || mailSchedule.getJobName().isEmpty()) {
            throw new NotificationAPIException("job name null or empty");
        }
        if (mailSchedule.getScheduleType() == null || mailSchedule.getScheduleType().isEmpty()) {
            throw new NotificationAPIException("schedule type null or empty");
        }
        if (mailSchedule.getScheduleType() != null) {
            if (mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_ONCE)) {
                if (mailSchedule.getScheduleDateTime() == null) {
                    throw new NotificationAPIException("schedule date null");
                }
            } else {
                if (mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_REPEAT)) {
                    if (mailSchedule.getCronExpression() == null) {
                        throw new NotificationAPIException("cron expression null");
                    }
                } else {
                    throw new NotificationAPIException("Invalid schedule type");
                }
            }
        }

        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // Start scheduler
            if(scheduler.isShutdown()){
                scheduler.start();
            }

            // Get the job details if already exist
            JobKey key = new JobKey(mailSchedule.getJobName(), mailSchedule.getJobName()+"Group");
            JobDetail mailNotificationJob = scheduler.getJobDetail(key);

            // If job details not found then create new job
            if (mailNotificationJob == null) {
                // Initiate JobDetail with job name, job group, and executable job class
                JobDetail jobDetail = JobBuilder.newJob(ScheduleMailNotificationJob.class)
                        .withIdentity(mailSchedule.getJobName(), mailSchedule.getJobName()+"Group")
                        .build();

                // set job to execute
                jobDetail.getJobDataMap().put("mailJobName", mailSchedule.getJobName());

                if(mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_ONCE)){
                    SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                            .withIdentity(mailSchedule.getJobName()+"SimpleTrigger", mailSchedule.getJobName()+"SimpleTriggerGroup")
                            .startAt(mailSchedule.getScheduleDateTime())
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, simpleTrigger);
                }

                if(mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_REPEAT)){
                    // Initiate CronTrigger with its name and group name
                    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                            .withIdentity(mailSchedule.getJobName()+"CronTrigger", mailSchedule.getJobName()+"CronTriggerGroup")
                            .withPriority(1)
                            .withSchedule(CronScheduleBuilder.cronSchedule(mailSchedule.getCronExpression()))
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        mail.setMailSchedule(mailSchedule);
        mail.setMailBody(mailBody);
        mail.setCreatedOn(new Date());
        mail.setStatus(false);
        mail.setQueue(false);
        mail.setVelocity(false);
        mailService.saveMail(mail);
    }

    /**
     * @param mailMap      Must contains the below key names with relevant values
     *                     sender : the sender email address. cannot be null
     *                     recipients : the receiver(s) email addresses and send type (String[][] array object). first array element: email address. second array element: pass one of constant in MailSendType. cannot be null. refer: MailSendType
     *                     subject : the subject of email. cannot be null
     *                     attachments : Attachment(s) if necessary (Optional). must contain absolute path of file(s) (String[] array object).
     *                     inlineImages : Inline image(s) if necessary (Optional). must contain absolute path of image(s) and content id (String[][] array object).
     *                     other key value pair of velocity template's place holders, cannot be null
     * @param templatePath classpath of template. cannot be null
     * @param mailSchedule contains schedule job data
     * @throws ousl.group4.exception.NotificationAPIException
     *
     */
    @Override
    public void scheduleMail(Map<String, Object> mailMap, String templatePath, MailSchedule mailSchedule) throws Exception {

        String sender = (String) mailMap.get("sender");
        if (sender == null || sender.isEmpty()) {
            throw new NotificationAPIException("sender is null or empty");
        }
        String[][] recipients = (String[][]) mailMap.get("recipients");
        if (recipients == null || recipients.length < 1) {
            throw new NotificationAPIException("recipients array is null or empty");
        }
        for (int i = 0; i < recipients.length; i++) {
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    if (recipients[i][j].isEmpty()) {
                        throw new NotificationAPIException("recipient is null or empty");
                    }
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                            } else {
                                throw new NotificationAPIException("invalid send type");
                            }
                        }
                    }
                }
            }
        }
        String subject = (String) mailMap.get("subject");
        if (subject == null || subject.isEmpty()) {
            throw new NotificationAPIException("subject is null or empty");
        }
        String[] attachments = (String[]) mailMap.get("attachments");
        String[][] inlineImages = (String[][]) mailMap.get("inlineImages");

        Mail mail = new Mail();
        mail.setMailSender(sender);
        List<MailRecipients> mailRecipients = new ArrayList<MailRecipients>();
        for (int i = 0; i < recipients.length; i++) {
            MailRecipients receiver = new MailRecipients();
            receiver.setStatus(false);
            receiver.setMail(mail);
            for (int j = 0; j <= recipients.length; j++) {
                if (j == 0) {
                    receiver.setMailAddress(recipients[i][j]);
                }
                if (j == 1) {
                    String sendType = recipients[i][j];
                    if (sendType.equalsIgnoreCase(MailSendType.SEND_TO)) {
                        receiver.setMailSendType(1);
                    } else {
                        if (sendType.equalsIgnoreCase(MailSendType.SEND_CC)) {
                            receiver.setMailSendType(2);
                        } else {
                            if (sendType.equalsIgnoreCase(MailSendType.SEND_BCC)) {
                                receiver.setMailSendType(3);
                            } else {
                            }
                        }
                    }
                }
                mailRecipients.add(receiver);
            }
        }
        mail.setMailRecipients(mailRecipients);
        mail.setMailSubject(subject);
        // Setup velocity engine
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        // Extract template into String object
        Template template = velocityEngine.getTemplate(templatePath);
        VelocityContext context = new VelocityContext(mailMap);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String mailBody = writer.toString();
        mail.setMailBody(mailBody);
        if (attachments != null && attachments.length > 0) {
            List<MailAttachments> mailAttachments = new ArrayList<MailAttachments>();
            for (int i = 0; i < attachments.length; i++) {
                if (attachments[i] == null || attachments[i].isEmpty()) {
                    throw new NotificationAPIException("attachment is null or empty");
                } else {
                    MailAttachments attach = new MailAttachments();
                    attach.setFileWithAbsoluteFilePath(attachments[i]);
                    attach.setMail(mail);
                    mailAttachments.add(attach);
                }
            }
            mail.setMailAttachments(mailAttachments);
        }
        if (inlineImages != null && inlineImages.length > 0) {
            List<MailInlineImages> mailInlineImages = new ArrayList<MailInlineImages>();
            for (int i = 0; i < inlineImages.length; i++) {
                MailInlineImages image = new MailInlineImages();
                image.setMail(mail);
                for (int j = 0; j <= inlineImages.length; j++) {
                    if (j == 0) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("inline image null or empty");
                        }
                        image.setImageWithAbsoluteFilePath(inlineImages[i][j]);
                    }
                    if (j == 1) {
                        if (inlineImages[i][j] == null || inlineImages[i][j].isEmpty()) {
                            throw new NotificationAPIException("content id null or empty");
                        }
                        image.setContentId(inlineImages[i][j]);
                    }
                }
                mailInlineImages.add(image);
            }
            mail.setMailInlineImages(mailInlineImages);
        }
        if (mailSchedule.getJobName() == null || mailSchedule.getJobName().isEmpty()) {
            throw new NotificationAPIException("job name null or empty");
        }
        if (mailSchedule.getScheduleType() == null || mailSchedule.getScheduleType().isEmpty()) {
            throw new NotificationAPIException("schedule type null or empty");
        }
        if (mailSchedule.getScheduleType() != null) {
            if (mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_ONCE)) {
                if (mailSchedule.getScheduleDateTime() == null) {
                    throw new NotificationAPIException("schedule date null");
                }
            } else {
                if (mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_REPEAT)) {
                    if (mailSchedule.getCronExpression() == null) {
                        throw new NotificationAPIException("cron expression null");
                    }
                } else {
                    throw new NotificationAPIException("Invalid schedule type");
                }
            }
        }

        // Retrieve a scheduler from schedule factory
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // Start scheduler
            if(scheduler.isShutdown()){
                scheduler.start();
            }

            // Get the job details if already exist
            JobKey key = new JobKey(mailSchedule.getJobName(), mailSchedule.getJobName()+"Group");
            JobDetail mailNotificationJob = scheduler.getJobDetail(key);

            // If job details not found then create new job
            if (mailNotificationJob == null) {
                // Initiate JobDetail with job name, job group, and executable job class
                JobDetail jobDetail = JobBuilder.newJob(ScheduleMailNotificationJob.class)
                        .withIdentity(mailSchedule.getJobName(), mailSchedule.getJobName()+"Group")
                        .build();

                // set job to execute
                jobDetail.getJobDataMap().put("mailJobName", mailSchedule.getJobName());

                if(mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_ONCE)){
                    SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                            .withIdentity(mailSchedule.getJobName()+"SimpleTrigger", mailSchedule.getJobName()+"SimpleTriggerGroup")
                            .startAt(mailSchedule.getScheduleDateTime())
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, simpleTrigger);
                }

                if(mailSchedule.getScheduleType().equalsIgnoreCase(MailKeyBox.FIRE_REPEAT)){
                    // Initiate CronTrigger with its name and group name
                    CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                            .withIdentity(mailSchedule.getJobName()+"CronTrigger", mailSchedule.getJobName()+"CronTriggerGroup")
                            .withPriority(1)
                            .withSchedule(CronScheduleBuilder.cronSchedule(mailSchedule.getCronExpression()))
                            .build();

                    // schedule a job with JobDetail and Trigger
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        mail.setMailSchedule(mailSchedule);
        mail.setCreatedOn(new Date());
        mail.setStatus(false);
        mail.setQueue(false);
        mail.setVelocity(true);
        mailService.saveMail(mail);
    }

    /**
     * This method return List object of finished mail notification
     *
     * @return
     */
    @Override
    public List<Mail> getFinishedMailNotifications() {
        return mailService.getFinishedMailNotifications();
    }

    /**
     * @return
     */
    @Override
    public List<Mail> getAllScheduleMailNotifications() {
        return mailService.getAllScheduleMailNotifications();
    }


}
