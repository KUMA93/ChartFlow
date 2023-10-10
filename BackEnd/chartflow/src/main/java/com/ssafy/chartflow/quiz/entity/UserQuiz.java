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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "choice")
    private String choice;

    public void setQuiz(Quiz quiz) {
        if (quiz != null) {
            quiz.getUserQuiz().remove(this);
        }
        this.quiz = quiz;
        assert quiz != null;
        quiz.getUserQuiz().add(this);
    }

    public void setUser(User user) {
        if (user != null) {
            user.getQuizs().remove(this);
        }
        this.user = user;
        assert user != null;
        user.getQuizs().add(this);
    }
}
