package com.whizstudios.spessark.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String toEmail, String messageBody) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(Utils.SUBJECT);
            message.setText(Utils.BODY_HEADER + messageBody);
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            return false;
        }
    }
}
