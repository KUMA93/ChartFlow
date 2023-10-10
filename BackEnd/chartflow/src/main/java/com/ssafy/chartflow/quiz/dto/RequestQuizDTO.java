package com.ssafy.chartflow.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestQuizDTO {   // 유저가 퀴즈 풀때 주는 request
    private Long quizId;
    private String choice;
}
