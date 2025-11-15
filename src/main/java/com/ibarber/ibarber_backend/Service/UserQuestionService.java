package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.UserQuestionDTO;
import java.util.List;

public interface UserQuestionService {
    UserQuestionDTO submitQuestion(UserQuestionDTO questionDTO);
    List<UserQuestionDTO> getAllQuestions();
    UserQuestionDTO answerQuestion(Long id, String answer);
}
