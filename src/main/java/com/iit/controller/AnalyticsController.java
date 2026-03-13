package com.iit.controller;

import com.iit.entity.User;
import com.iit.repository.AttemptRepository;
import com.iit.repository.UserRepository;
import com.iit.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;

    public AnalyticsController(AttemptRepository attemptRepository,
                               UserRepository userRepository) {
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/accuracy")
    public Double getOverallAccuracy(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Double accuracy = attemptRepository.findOverallAccuracy(user);

        return accuracy == null ? 0.0 : accuracy * 100;
    }
    
    @GetMapping("/accuracy-by-topic")
    public Map<String, Double> accuracyByTopic(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Object[]> data = attemptRepository.findAccuracyByTopic(user);

        Map<String, Double> result = new HashMap<>();

        for (Object[] row : data) {
            String topic = (String) row[0];
            Double accuracy = (Double) row[1];
            result.put(topic, accuracy * 100);
        }

        return result;
    }
    
    @GetMapping("/weak-areas")
    public Map<String, Double> weakAreas(Authentication authentication) {

        Map<String, Double> accuracyMap = accuracyByTopic(authentication);

        return accuracyMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() < 50)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }


}
