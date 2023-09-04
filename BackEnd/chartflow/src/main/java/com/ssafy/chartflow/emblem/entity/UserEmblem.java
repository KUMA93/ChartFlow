package com.ssafy.chartflow.emblem.entity;

import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private long userEmblemId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emblemId")
    private Emblem emblem;
}