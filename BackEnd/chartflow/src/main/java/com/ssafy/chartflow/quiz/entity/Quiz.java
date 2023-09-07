package com.ssafy.chartflow.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "quiz")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long quizId;

    @OneToMany(mappedBy = "quiz")
    private final List<QuizChoices> quizChoices = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private final List<UserQuiz> userQuiz = new ArrayList<>();

    @Column(name = "answer")
    private String answer;
}