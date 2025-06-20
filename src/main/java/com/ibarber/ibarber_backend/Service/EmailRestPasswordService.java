package com.ibarber.ibarber_backend.Service;

public interface EmailRestPasswordService {
    void sendEmail(String to, String subject, String body);
}
