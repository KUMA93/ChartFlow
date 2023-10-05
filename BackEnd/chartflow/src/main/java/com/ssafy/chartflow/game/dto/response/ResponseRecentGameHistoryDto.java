package com.ssafy.chartflow.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecentGameHistoryDto {
    private String companyName;
    private Long initialBudget;
    private Long lastBudget;
}
