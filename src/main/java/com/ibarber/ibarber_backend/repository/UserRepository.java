package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findByRole(String role);
    List<User> findByActiveTrue();
}
