package com.ssafy.chartflow.board.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "recomment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReComments{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recomment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comments comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 추가된 부분

    @Column(name = "content")
    private String content;

    @Column(name = "registertime")
    private LocalDateTime registerTime;

    @Column(name = "cancel")
    private int cancel;

    @Column(name = "modify")
    private int modify;

    public void setComment(Comments comment) {
        if (comment != null) {
            comment.getReComments().remove(this);
        }
        this.comment = comment;
        assert comment != null;
        comment.getReComments().add(this);
    }

    public void setUser(User user) {
        if (user != null) {
            user.getReComments().remove(this);
        }
        this.user = user;
        assert user != null;
        user.getReComments().add(this);
    }
}