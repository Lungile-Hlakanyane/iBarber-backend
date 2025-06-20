package com.ibarber.ibarber_backend.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtpDTO {
    private String email;

    private String otp;

    private LocalDateTime expiryTime;

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
