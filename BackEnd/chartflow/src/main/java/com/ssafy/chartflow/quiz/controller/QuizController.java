package com.ssafy.chartflow.quiz.controller;

import com.ssafy.chartflow.quiz.dto.RequestQuizDTO;
import com.ssafy.chartflow.quiz.dto.ResponseQuizDto;
import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import com.ssafy.chartflow.quiz.service.QuizService;
import com.ssafy.chartflow.security.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "quiz", description = "퀴즈기능 API")
@RequestMapping("/quiz")
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
public class QuizController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String AUTH = "Authorization";

    private final QuizService quizService;
    private final JwtService jwtService;

    // 오늘의 퀴즈 목록 3개 불러오기
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getQuiz(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();

        token = token.split(" ")[1];

        try{
            Long userId = jwtService.extractUserId(token);
            Quiz todayQuiz = quizService.getTodayQuizzes(userId);
            response.put("quiz", todayQuiz);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            response.put("httpStatus", FAIL);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 퀴즈 정답 여부 리턴
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> getQuizResult(@RequestHeader("Authorization") String token,
                                                             @RequestBody RequestQuizDTO requestQuizDTO) {

        Map<String, Object> response = new HashMap<>();

        token = token.split(" ")[1];
        try {
            log.info("퀴즈 컨트롤러 - 퀴즈 진행");
            Long userId = jwtService.extractUserId(token);
            boolean result = quizService.quizResult(userId, requestQuizDTO.getQuizId(), requestQuizDTO.getChoice());
            response.put("result", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("퀴즈 컨트롤러 - 퀴즈 진행 실패");
            response.put("httpStatus", FAIL);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
