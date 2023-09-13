package com.ssafy.chartflow.game.entity;

import com.ssafy.chartflow.stocks.entity.Stocks;
import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    private LocalDate chartDate;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "initial_budget")
    private long initialBudget;

    @Column(name = "rate")
    private double rate;

    @Column(name = "result")
    private int result;

    @OneToMany(mappedBy = "gameHistory")
    private final List<GameTurns> gameTurns = new ArrayList<>();

    @OneToMany(mappedBy = "gameHistory")
    private final List<GameHistoryStocks> gameHistoryStocks = new ArrayList<>();


    // getters, setters, etc.
    public void setUser(User user) {
        if (user != null) {
            user.getGameHistories().remove(this);
        }
        this.user = user;
        assert user != null;
        user.getGameHistories().add(this);
    }
}
