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
    @Column(name = "recomment_id")
    private long reCommentId;

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
        this.comment = comment;
        comment.getReComments().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getReComments().add(this);
    }
}