package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.OtpDTO;
import com.ibarber.ibarber_backend.dto.UserDTO;
import com.ibarber.ibarber_backend.entity.User;
import java.util.List;

public interface UserService {
    String registerUser(UserDTO userDTO);
    String verifyOtp(OtpDTO otpDTO);
    User authenticateUser(String email, String password);
    public List<UserDTO> getAllUsers();
    boolean deleteUserById(Long id);
    List<UserDTO> getAllBarbers();

    long countUsers();
}
