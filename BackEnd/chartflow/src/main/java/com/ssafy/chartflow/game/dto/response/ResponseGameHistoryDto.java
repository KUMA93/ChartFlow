package com.ssafy.chartflow.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGameHistoryDto {

    private long gameHistoryId;
    private LocalDate chartDate;
    private int turn;
    private int price;
    private int quantity;
    private long initialBudget;
    private long cashBudget;
    private double rate;


}
