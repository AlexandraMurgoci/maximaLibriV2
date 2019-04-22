package com.maximaLibri.maximaLibriV2.sendEmailTest;

import com.maximaLibri.maximaLibriV2.service.EmailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(value = "com.maximaLibri.maximaLibriV2")
public class sendEmailTest {

    @Autowired
    EmailSenderService emailSenderService;

    @Test
    public void sendEmailTest() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("alexa_murgoci@yahoo.com");
        mailMessage.setSubject("Test emailSender from MaximaLibri!");
        mailMessage.setFrom("alexa.murgoci@gmail.com");
        mailMessage.setText("Test emailSender from MaximaLibri!");

        emailSenderService.sendEmail(mailMessage);
    }
}
