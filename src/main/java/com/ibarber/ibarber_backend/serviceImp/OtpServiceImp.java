package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.OtpService;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.entity.Otp;
import com.ibarber.ibarber_backend.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpServiceImp implements OtpService {

    @Autowired
    private OtpRepository otpRepository;
    @Override
    public boolean verifyOtp(String email, String otp) {
        Optional<Otp> storedOtp = otpRepository.findByEmail(email);
        if (storedOtp.isPresent()) {
            Otp otpRecord = storedOtp.get();
            boolean isValid = otpRecord.getOtp().equals(otp) &&
                    otpRecord.getExpiryTime().isAfter(LocalDateTime.now());
            if (isValid) {
                otpRepository.delete(otpRecord);
                return true;
            }
        }
        return false;
    }
}
