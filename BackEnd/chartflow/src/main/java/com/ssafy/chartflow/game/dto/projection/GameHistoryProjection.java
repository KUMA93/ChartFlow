package com.ssafy.chartflow.game.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GameHistoryProjection {
    private String companyCode;
    private long cashBudget;
}
