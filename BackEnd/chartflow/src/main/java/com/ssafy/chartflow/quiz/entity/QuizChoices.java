package com.ssafy.chartflow.quiz.entity;
import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Table(name = "quiz_choices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizChoices {
    @Id
    @Column(name = "Key")
    private String key;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
