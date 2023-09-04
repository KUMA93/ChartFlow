package com.ssafy.chartflow.board.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "recomment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReComments{
    @Id
    @Column(name = "recomment_id")
    private String recommentId;

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
}