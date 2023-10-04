package com.ssafy.chartflow.quiz.dto;

import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseQuizDto {
    private Long quizId;
    private String question;
    private String answer;
    private List<String> choices;  // 보기들

}
