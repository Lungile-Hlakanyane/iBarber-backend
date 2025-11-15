package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {
}
