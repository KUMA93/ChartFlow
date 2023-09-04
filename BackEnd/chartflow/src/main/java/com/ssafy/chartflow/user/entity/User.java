package com.ssafy.chartflow.user.entity;

import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.entity.Likes;
import com.ssafy.chartflow.board.entity.ReComments;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.quiz.entity.UserQuiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"emblems", "userGameHistories", "userLikes", "userComments", "userArticle", "userRecomments"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "coin")
    private int coin;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "social")
    private short social;

    @OneToMany(mappedBy = "user")
    private final List<UserEmblem> emblems = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<GameHistory> userGameHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Likes> userLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comments> userComments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Article> userArticle = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<ReComments> userRecomments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<UserQuiz> userQuiz = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() { //인증관련 메서드이기 때문에 return email
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
