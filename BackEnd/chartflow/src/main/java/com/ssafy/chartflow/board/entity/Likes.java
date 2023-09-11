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
    private long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setArticle(Article article) {
        this.article = article;
        article.getLikes().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getLikes().add(this);
    }
}