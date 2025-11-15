package com.ibarber.ibarber_backend.serviceImp;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPStoreService {
    private static class OtpDetails {
        String otp;
        LocalDateTime expiry;
    }
    private final ConcurrentHashMap<String, OtpDetails> otpMap = new ConcurrentHashMap<>();
    public void saveOtp(String email, String otp) {
        OtpDetails details = new OtpDetails();
        details.otp = otp;
        details.expiry = LocalDateTime.now().plusMinutes(10);
        otpMap.put(email, details);
    }
    public boolean validateOtp(String email, String otp) {
        OtpDetails details = otpMap.get(email);
        if (details == null) return false;
        if (details.expiry.isBefore(LocalDateTime.now())) return false;
        return details.otp.equals(otp);
    }
    public void removeOtp(String email) {
        otpMap.remove(email);
    }
}
