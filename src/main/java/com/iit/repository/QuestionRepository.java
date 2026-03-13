package com.iit.repository;


import com.iit.entity.Question;
import com.iit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCreatedBy(User user);
}
