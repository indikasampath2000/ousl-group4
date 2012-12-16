package ousl.group4.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ousl.group4.email.model.Mail;
import ousl.group4.email.service.MailSender;
import ousl.group4.sms.model.Sms;
import ousl.group4.sms.service.SmsSender;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private SmsSender smsSender;

    @RequestMapping(value = "/sentEmailReport.html", method = RequestMethod.GET)
    public String sentEmailReportHandler(ModelMap modelMap){
        List<Mail> sentMails = mailSender.getFinishedMailNotifications();
        System.out.println(sentMails.size());
        modelMap.addAttribute("sentMails", sentMails);

        return "sent-email-report";
    }

    @RequestMapping(value = "/sentSmsReport.html", method = RequestMethod.GET)
    public String sentSmsReportHandler(ModelMap modelMap){
        List<Sms> sentSmses = smsSender.getFinishedSmsNotifications();
        System.out.println(sentSmses.size());
        modelMap.addAttribute("sentSmses", sentSmses);

        return "sent-email-report";
    }



}
