package com.ssafy.chartflow.game.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "game_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_history_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "turn")
    private int turn;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "chart_date")
    private String chartDate;
    // getters, setters, etc.
}
