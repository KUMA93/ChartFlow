package com.ssafy.chartflow.quiz.controller;

import com.ssafy.chartflow.quiz.dto.RequestQuizDTO;
import com.ssafy.chartflow.quiz.dto.ResponseQuizDto;
import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import com.ssafy.chartflow.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "quiz", description = "퀴즈기능 API")
@RequestMapping("/quiz")
@CrossOrigin("*")
@Slf4j
public class QuizController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String AUTH = "Authorization";

    @Autowired
    QuizService quizService;

    // 오늘의 퀴즈 목록 3개 불러오기
    @GetMapping("")
    public ResponseEntity<List<ResponseQuizDto>> getTodayQuiz() {
        List<Quiz> todayQuizzes = quizService.getTodayQuizzes();
        List<ResponseQuizDto> quizzes = todayQuizzes.stream()
                .map(quiz -> {
                    QuizChoices[] choices = quizService.getChoices(quiz.getId()); // 보기 정보를 가져오는 방법에 따라 수정 필요
                    return new ResponseQuizDto(quiz, choices);
                })
                .toList();

        if (quizzes.size() < 3) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Not enough quizzes available");

            // 정상 응답 상태 코드(200 OK)와 함께 응답 헤더에 메시지 추가
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(quizzes);
        } else
            return ResponseEntity
                    .ok()
                    .body(quizzes);
    }

    // 퀴즈 정답 여부 리턴
    @PostMapping("")
    public ResponseEntity<Boolean> getQuizResult(@RequestBody RequestQuizDTO requestQuizDTO) {
        boolean result = quizService.quizResult(requestQuizDTO.getUserId(), requestQuizDTO.getQuizId(), requestQuizDTO.getChoice());
        return ResponseEntity
                .ok()
                .body(result);
    }


}
