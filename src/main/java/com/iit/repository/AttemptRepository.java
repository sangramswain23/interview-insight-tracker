package com.iit.repository;

import com.iit.entity.Attempt;
import com.iit.entity.User;
import com.iit.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    List<Attempt> findByUser(User user);

    List<Attempt> findByQuestion(Question question);
    
    @Query("""
    		SELECT 
    		    COUNT(a) * 1.0 / (SELECT COUNT(a2) FROM Attempt a2 WHERE a2.user = :user)
    		FROM Attempt a
    		WHERE a.user = :user AND a.correct = true
    		""")
    		Double findOverallAccuracy(User user);
    
    @Query("""
    		SELECT q.topic, 
    		       COUNT(a) * 1.0 / COUNT(a)
    		FROM Attempt a
    		JOIN a.question q
    		WHERE a.user = :user AND a.correct = true
    		GROUP BY q.topic
    		""")
    		List<Object[]> findAccuracyByTopic(User user);


}
