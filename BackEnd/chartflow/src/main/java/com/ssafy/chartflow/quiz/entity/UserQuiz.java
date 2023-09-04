package com.ssafy.chartflow.quiz.entity;

import com.ssafy.chartflow.user.entity.User;
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
public class UserQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quiz_id")
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Quiz quiz;
}
