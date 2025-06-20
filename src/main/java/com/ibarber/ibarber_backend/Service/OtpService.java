package com.ibarber.ibarber_backend.Service;

public interface OtpService {
    boolean verifyOtp(String email, String otp);
}
