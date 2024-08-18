package com.example.quiz_service.feign;

import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions);


    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId
            (@RequestBody List<Integer> QuestionIds);


    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getQuizScore
            (@RequestBody List<Response> responses);



}
