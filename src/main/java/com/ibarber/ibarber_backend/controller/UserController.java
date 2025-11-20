package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.UserService;
import com.ibarber.ibarber_backend.dto.EmailDTO;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.dto.ResetPasswordDTO;
import com.ibarber.ibarber_backend.dto.UserDTO;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody EmailDTO dto) {
        String result = userService.sendResetLink(dto.getEmail());
        return ResponseEntity.ok(result);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
        String result = userService.resetPassword(dto.getToken(), dto.getNewPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload-profile-image/{userId}")
    public ResponseEntity<?> uploadProfileImage(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            User user = optionalUser.get();
            String uploadDir = "/home/ec2-user/uploads/";
            Files.createDirectories(Paths.get(uploadDir));
            String fileName = "user_" + userId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());
            user.setProfileImage(fileName);
            userRepository.save(user);
            return ResponseEntity.ok("Profile image uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/profile-image/{filename}")
    public ResponseEntity<?> getProfileImage(@PathVariable String filename) {
        try {
            Path path = Paths.get("user-profile-images/" + filename);
            if (!Files.exists(path)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
            byte[] image = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")
                    .body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        UserDTO updated = userService.updateUser(id, updatedUserDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
