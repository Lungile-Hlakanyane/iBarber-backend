package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.UserDTO;
import com.ibarber.ibarber_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto, String encodedPassword, String otp) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(dto.getRole());
        user.setBanned(dto.isBanned());
        user.setOtp(otp);
        user.setVerified(false);
        return user;
    }

    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setBanned(user.isBanned());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setVerified(user.isVerified());
        return dto;
    }
}
