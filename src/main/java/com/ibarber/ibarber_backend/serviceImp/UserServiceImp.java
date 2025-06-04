package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.EmailService;
import com.ibarber.ibarber_backend.Service.UserService;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.dto.UserDTO;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.mapper.UserMapper;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email already registered!";
        }
        String otp = String.format("%06d", new Random().nextInt(999999));
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = userMapper.toEntity(userDTO, encodedPassword, otp);
        userRepository.save(user);
        emailService.sendEmail(userDTO.getEmail(), "Your iBarber OTP Code", "Your OTP is: " + otp);
        return "Registration successful. OTP sent to email.";
    }

    @Override
    public String verifyOtp(OtpDTO otpDTO) {
        Optional<User> userOpt = userRepository.findByEmail(otpDTO.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found!";
        }
        User user = userOpt.get();
        if (user.getOtp() == null) {
            return "OTP already verified or not generated!";
        }

        if (user.getOtp().equals(otpDTO.getOtp())) {
            user.setVerified(true);
            //user.setOtp(null);
            userRepository.save(user);
            return "OTP verified successfully!";
        } else {
            return "Invalid OTP!";
        }
    }

    @Override
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            dto.setUsername(user.getUsername());
            dto.setVerified(user.isVerified());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserDTO> getAllBarbers() {
        List<User> barbers = userRepository.findByRole("barber");
        return barbers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
