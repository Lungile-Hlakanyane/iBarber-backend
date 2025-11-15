package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.UserQuestionService;
import com.ibarber.ibarber_backend.dto.UserQuestionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class UserQuestionController {

    private final UserQuestionService questionService;

    public UserQuestionController(UserQuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<UserQuestionDTO> submitQuestion(@RequestBody UserQuestionDTO dto) {
        return ResponseEntity.ok(questionService.submitQuestion(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserQuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
    @PutMapping("/{id}/answer")
    public ResponseEntity<UserQuestionDTO> answerQuestion(@PathVariable Long id, @RequestBody String answer) {
        return ResponseEntity.ok(questionService.answerQuestion(id, answer));
    }
}
