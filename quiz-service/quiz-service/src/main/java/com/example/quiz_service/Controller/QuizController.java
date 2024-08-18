package com.example.quiz_service.Controller;


import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.QuizDto;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto) {
        return quizservice.createQuiz(quizdto.getCategory(), quizdto.getNumOfQuestions(), quizdto.getTitle());
    }
@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizservice.getQuizQuestion(id);

    }
@PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizservice.calculateResult(id, response);

    }

}
