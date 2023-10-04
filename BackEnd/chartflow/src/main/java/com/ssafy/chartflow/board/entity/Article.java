package com.ssafy.chartflow.board.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ArticleTag tag;

    @Column(name = "views")
    private int views;

    @Column(name = "registertime")
    private LocalDateTime registerTime;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "article")
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Likes> likes = new ArrayList<>();
    // getters, setters, etc.

    // 양방향 맵핑
    public void setUser(User user) {
        if(user!=null){
            user.getArticles().remove(this);
        }
        this.user = user;
        assert user != null;
        user.getArticles().add(this);
    }
}