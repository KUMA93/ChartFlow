    package com.ssafy.chartflow.user.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.ssafy.chartflow.board.entity.Article;
    import com.ssafy.chartflow.board.entity.Comments;
    import com.ssafy.chartflow.board.entity.Likes;
    import com.ssafy.chartflow.board.entity.ReComments;
    import com.ssafy.chartflow.emblem.entity.UserEmblem;
    import com.ssafy.chartflow.game.entity.GameHistory;
    import com.ssafy.chartflow.quiz.entity.UserQuiz;
    import com.ssafy.chartflow.security.entity.RefreshToken;
    import jakarta.persistence.*;
    import lombok.*;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;

    @Entity
    @Data
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

        @Column(name = "name")
        private String name;

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

        @Column(name = "cancel")
        private int cancel;

        @Column(name = "selected_emblem")
        private Long selected_emblem=0L;

        @Column(name = "ranking")
        private Long ranking=0L;

        @OneToMany(mappedBy = "user")
        private final List<UserEmblem> emblems = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        @JsonIgnore
        private final List<GameHistory> gameHistories = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private final List<Likes> likes = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private final List<Comments> comments = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private final List<Article> articles = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private final List<ReComments> reComments = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private final List<UserQuiz> Quizs = new ArrayList<>();

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

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", role=" + role +
                    ", coin=" + coin +
                    ", budget=" + budget +
                    ", social=" + social +
                    ", cancel=" + cancel +
                    ", selected_emblem=" + selected_emblem +
                    '}';
        }
    }
