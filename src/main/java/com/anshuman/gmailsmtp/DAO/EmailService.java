package com.anshuman.gmailsmtp.DAO;

import javax.mail.MessagingException;

public interface EmailService {

    void simpleEmail(String to,String body,String subject);
    void emailWithSingleAttachment(String to,String body,String subject,String attachment) throws MessagingException;

    void emailWithMultipleAttachments(String to,String body,String subject,String[] attachment) throws MessagingException;
}
