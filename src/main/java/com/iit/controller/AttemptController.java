package com.iit.controller;

import com.iit.dto.AttemptRequest;
import com.iit.entity.Attempt;
import com.iit.entity.Question;
import com.iit.entity.User;
import com.iit.exception.ResourceNotFoundException;
import com.iit.repository.AttemptRepository;
import com.iit.repository.QuestionRepository;
import com.iit.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attempts")
public class AttemptController {

    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AttemptController(AttemptRepository attemptRepository,
                             QuestionRepository questionRepository,
                             UserRepository userRepository) {
        this.attemptRepository = attemptRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Attempt submitAttempt(@Valid @RequestBody AttemptRequest request,
                                 Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        Attempt attempt = new Attempt();
        attempt.setUser(user);
        attempt.setQuestion(question);
        attempt.setCorrect(request.getCorrect());
        attempt.setTimeTakenSeconds(request.getTimeTakenSeconds());

        return attemptRepository.save(attempt);
    }
}
