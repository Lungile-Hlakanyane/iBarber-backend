package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.OtpService;
import com.ibarber.ibarber_backend.Service.UserService;
import com.ibarber.ibarber_backend.dto.ForgotPasswordRequest;
import com.ibarber.ibarber_backend.dto.LoginRequestDTO;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.dto.OtpVerificationRequest;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.repository.UserRepository;
import com.ibarber.ibarber_backend.serviceImp.EmailServiceImp;
import com.ibarber.ibarber_backend.serviceImp.OTPStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailServiceImp emailServiceImp;
    @Autowired
    private OTPStoreService otpStoreService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
        User user = optionalUser.get();
        if (user.isBanned()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "You cannot log in. Your account has been banned for 30 days."));
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("username", user.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Email not found.");
        }
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStoreService.saveOtp(request.getEmail(), otp);
        emailServiceImp.sendOtpEmail(request.getEmail(), otp);
        return ResponseEntity.ok("OTP sent successfully.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpDTO otpDTO) {
        String result = userService.verifyOtp(otpDTO);
        if (result.equals("OTP verified successfully!")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("User not found!")) {
            return ResponseEntity.status(404).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }
}
