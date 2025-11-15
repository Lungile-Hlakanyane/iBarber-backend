package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.UserQuestionDTO;
import com.ibarber.ibarber_backend.entity.UserQuestion;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class UserQuestionMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public UserQuestion toEntity(UserQuestionDTO dto) {
        UserQuestion entity = new UserQuestion();
        entity.setName(dto.getName());
        entity.setQuestion(dto.getQuestion());
        return entity;
    }
    public UserQuestionDTO toDto(UserQuestion entity) {
        UserQuestionDTO dto = new UserQuestionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuestion(entity.getQuestion());
        dto.setAnswer(entity.getAnswer());
        dto.setAnswered(entity.isAnswered());
        dto.setCreatedAt(entity.getCreatedAt().format(formatter));
        return dto;
    }
}
