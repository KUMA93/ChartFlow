package com.ssafy.chartflow.quiz.dto;

import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import lombok.Data;

@Data
public class ResponseQuizDto {
    private Long quizId;
    private String question;
    private String answer;
    private String[] contents;  // 보기들

    public ResponseQuizDto(Quiz quiz, QuizChoices[] choices) {
        this.quizId = quiz.getId();
        this.question = quiz.getQuestion();
        this.answer = quiz.getAnswer();
        this.contents = new String[choices.length];
        for(int i=0;i<choices.length;i++)
            contents[i]=choices[i].getContent();
    }
}
