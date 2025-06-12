package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.UserService;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.dto.UserDTO;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
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
    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
    @GetMapping("/barbers")
    public ResponseEntity<List<UserDTO>> getAllBarbers() {
        List<UserDTO> barbers = userService.getAllBarbers();
        return ResponseEntity.ok(barbers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        long userCount = userService.countUsers();
        return ResponseEntity.ok(userCount);
    }


}
