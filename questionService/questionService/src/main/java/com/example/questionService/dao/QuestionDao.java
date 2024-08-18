package com.example.questionService.dao;


import com.example.questionService.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
  List<Question> findByCategory(String category);
  @Query(value = "SELECT q.id FROM questions q Where q.category=:category ORDER BY RANDOM() LIMIT :numsQ", nativeQuery = true)
  List<Integer> findRandomQuestionsByCategory(String category, int numsQ);


}
