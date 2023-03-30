package com.anshuman.gmailsmtp.Service;

import com.anshuman.gmailsmtp.DAO.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.from}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void simpleEmail(String to, String body, String subject){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setText(body);
        mail.setSubject(subject);

        javaMailSender.send(mail);
        System.out.println("Mail has been sent successfully");
    }

    @Override
    public void emailWithSingleAttachment(String to, String body, String subject, String attachment) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail,true);

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

        javaMailSender.send(mail);
    }

    @Override
    public void emailWithMultipleAttachments(String to, String body, String subject, String[] attachment) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail,true);

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        int attachmentCount = attachment.length;
        for(int i=0;i<attachmentCount;i++){
            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment[i]));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        }
        javaMailSender.send(mail);
    }
}
