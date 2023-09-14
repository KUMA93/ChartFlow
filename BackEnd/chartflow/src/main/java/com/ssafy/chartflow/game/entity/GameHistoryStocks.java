package com.ssafy.chartflow.game.entity;

import com.ssafy.chartflow.stocks.entity.Stocks;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "game_history_stocks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class GameHistoryStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_history_stocks_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_history_id")
    private GameHistory gameHistory;

    @ManyToOne
    @JoinColumn(name = "stocks_id")
    private Stocks stocks;

    public void setGameHistory(GameHistory gameHistory) {
        if (gameHistory != null) {
            gameHistory.getGameHistoryStocks().remove(this);
        }
        this.gameHistory = gameHistory;
        assert gameHistory != null;
        gameHistory.getGameHistoryStocks().add(this);
    }

    public void setStocks(Stocks stocks) {
        if (stocks != null) {
            stocks.getGameHistoryStocks().remove(this);
        }
        this.stocks = stocks;
        assert stocks != null;
        stocks.getGameHistoryStocks().add(this);
    }

}
