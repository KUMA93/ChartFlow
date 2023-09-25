package com.ssafy.chartflow.game.entity;

import com.ssafy.chartflow.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@Table(name = "game_turns")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class GameTurns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_turns_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_history_id")
    private GameHistory gameHistory;

    @Column(name = "turn")
    private int turn;

    @Column(name = "behavior")
    private int behavior;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "current_stocks")
    private long currentStocks;

    @Column(name = "cash_budget")
    private long cashBudget;

    @Column(name = "total_assets")
    private long totalAssets;

    @Column(name = "rate")
    private double rate;

    @Column(name = "today_price")
    private int todayPrice;

    public void setGameHistory(GameHistory gameHistory) {
        if (gameHistory != null) {
            gameHistory.getGameTurns().remove(this);
        }
        this.gameHistory = gameHistory;
        assert gameHistory != null;
        gameHistory.getGameTurns().add(this);
    }

}
