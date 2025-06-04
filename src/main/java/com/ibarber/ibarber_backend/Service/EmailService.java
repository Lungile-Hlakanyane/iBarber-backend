package com.ibarber.ibarber_backend.Service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
