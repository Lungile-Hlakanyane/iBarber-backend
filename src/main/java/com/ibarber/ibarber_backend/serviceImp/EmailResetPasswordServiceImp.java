package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.EmailRestPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailResetPasswordServiceImp implements EmailRestPasswordService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
