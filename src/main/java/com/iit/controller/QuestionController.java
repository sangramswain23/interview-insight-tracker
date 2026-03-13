package com.iit.controller;

import com.iit.dto.QuestionRequest;
import com.iit.entity.Question;
import com.iit.entity.User;
import com.iit.exception.ResourceNotFoundException;
import com.iit.repository.QuestionRepository;
import com.iit.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionController(QuestionRepository questionRepository,
                              UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Question createQuestion(@Valid @RequestBody QuestionRequest request,
                                   Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCompany(request.getCompany());
        question.setTopic(request.getTopic());
        question.setDifficulty(request.getDifficulty());
        question.setCreatedBy(user);

        return questionRepository.save(question);
    }
}
