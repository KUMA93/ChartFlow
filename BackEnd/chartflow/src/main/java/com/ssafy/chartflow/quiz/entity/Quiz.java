package com.ssafy.chartflow.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user_quiz")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;
    // getters, setters, etc.
}