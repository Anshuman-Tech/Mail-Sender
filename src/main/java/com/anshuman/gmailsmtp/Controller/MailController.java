package com.anshuman.gmailsmtp.Controller;

import com.anshuman.gmailsmtp.DAO.EmailService;
import com.anshuman.gmailsmtp.Service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {


    @Value("${email.to}")
    private String to;

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendMail")
    public String sendSimpleMail(){
        emailService.simpleEmail(to,"This is the mail I am sending","The Mail");
        return "Mail Sent successfully";
    }

    @GetMapping("/sendSingleAttachmentMail")
    public String emailWithSingleAttachment() throws MessagingException {
        emailService.emailWithSingleAttachment(to,"This is the mail I am sending","The Attachment Mail","C:\\Users\\anshu\\OneDrive\\Pictures\\000000000.png");
        return "Mail Sent successfully";
    }

    @GetMapping("/sendMultipleAttachmentsMail")
    public String emailWithMultipleAttachments() throws MessagingException {
        String[] attachments = {"C:\\Users\\anshu\\OneDrive\\Pictures\\1.1.a.png","C:\\Users\\anshu\\OneDrive\\Pictures\\1.1.b.png","C:\\Users\\anshu\\OneDrive\\Pictures\\4.B.3.b.png"};
        emailService.emailWithMultipleAttachments(to,"This is the mail I am sending","The Mail with Multiple Attachments",attachments);
        return "Mail Sent successfully";
    }
}
