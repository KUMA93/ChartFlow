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
    private int articleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "views")
    private int views;

    @Column(name = "registertime")
    private LocalDateTime registerTime;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "article")
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Likes> likes = new ArrayList<>();
    // getters, setters, etc.
}