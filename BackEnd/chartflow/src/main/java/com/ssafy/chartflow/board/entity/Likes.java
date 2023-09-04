package com.ssafy.chartflow.board.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long likeId;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}