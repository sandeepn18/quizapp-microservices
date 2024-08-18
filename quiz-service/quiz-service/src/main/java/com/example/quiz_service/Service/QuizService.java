package com.example.quiz_service.Service;


import com.example.quiz_service.Model.Question;
import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.Quiz;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.dao.QuizDao;
import com.example.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizinterface;

//    @Autowired
//    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {

        List<Integer> questions = quizinterface.getQuestionsForQuiz(category, numsQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

//        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numsQ);
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {

        Quiz quiz = quizDao.findById(id).get();

        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizinterface.getQuestionsFromId(questionIds);
        return questions;

//        List<Question> questionFromDB = quiz.get().getQuestions();
        //List<QuestionWrapper> questionForUser = new ArrayList<>();
//        for(Question q : questionFromDB){
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getCategory(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            questionForUser.add(qw);
//        }

        //return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {

      ResponseEntity<Integer> score  =   quizinterface.getQuizScore(response);
      return score;


//    Quiz quiz = quizDao.findById(id).get();
//    List<Question> questions = quiz.getQuestions();
  //  int right = 0;
//    int i = 0;
//    for(Response r : response){
//        if(r.getResponse().equals(questions.get(i).getRightAnswer()))
//            right++;
//
//        i++;
//    }
//    return new ResponseEntity<>(right, HttpStatus.OK);
}
}
