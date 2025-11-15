package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.UserQuestionService;
import com.ibarber.ibarber_backend.dto.UserQuestionDTO;
import com.ibarber.ibarber_backend.entity.UserQuestion;
import com.ibarber.ibarber_backend.mapper.UserQuestionMapper;
import com.ibarber.ibarber_backend.repository.UserQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQuestionServiceImp implements UserQuestionService {
    private final UserQuestionRepository repository;
    private final UserQuestionMapper mapper;

    public UserQuestionServiceImp(UserQuestionRepository repository, UserQuestionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserQuestionDTO submitQuestion(UserQuestionDTO questionDTO) {
        UserQuestion saved = repository.save(mapper.toEntity(questionDTO));
        return mapper.toDto(saved);
    }
    @Override
    public List<UserQuestionDTO> getAllQuestions() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public UserQuestionDTO answerQuestion(Long id, String answer) {
        UserQuestion question = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setAnswer(answer);
        question.setAnswered(true);
        return mapper.toDto(repository.save(question));
    }
}
