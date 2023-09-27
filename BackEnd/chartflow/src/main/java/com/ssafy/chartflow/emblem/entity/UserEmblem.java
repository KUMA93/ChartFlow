package com.ssafy.chartflow.emblem.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user_emblem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class UserEmblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "equiped")
    private boolean equiped;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emblemId")
    private Emblem emblem;

    // UserEmblem 엔터티 클래스 내부에 추가
    public void setUser(User user) {
        if (this.user != null) {
            this.user.getEmblems().remove(this);
        }
        this.user = user;
        user.getEmblems().add(this);
    }

    public void setEmblem(Emblem emblem) {
        if (this.emblem != null) {
            this.emblem.getUserEmblems().remove(this);
        }
        this.emblem = emblem;
        emblem.getUserEmblems().add(this);
    }

}