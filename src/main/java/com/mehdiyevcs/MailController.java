package com.mehdiyevcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class MailController {

    @Autowired
    MailCnfg mailCnfg;

    @RequestMapping(value="/sendMail")
    public String sendMail(){
        return "Message sent!!!";
    }

    @RequestMapping(value="/sendMailTo")
    public String sendMailTo(){

        //Cnfg Mail Sender
        JavaMailSenderImpl mailSender =  new JavaMailSenderImpl();
        mailSender.setHost(mailCnfg.getHost());
        mailSender.setPort(mailCnfg.getPort());
        mailSender.setUsername(mailCnfg.getUsername());
        mailSender.setPassword(mailCnfg.getPassword());

        //Gmail needs tls commant first so we need additional properties
        Properties prp = mailSender.getJavaMailProperties();
        prp.put("mail.transport.protocol","smtp");
        prp.put("mail.smtp.auth","true");
        prp.put("mail.smtp.starttls.enable","true");
        //prp.put("mail.debug","true");

        //Simple mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("djalal.mekhtiyeb@gmail.com");
        mailMessage.setTo("djalal1228@gmail.com");
        mailMessage.setSubject("MailAPI");
        mailMessage.setText("Hello World!!!");
        mailSender.send(mailMessage);

        return "Message is sent!!!";
    }
}
