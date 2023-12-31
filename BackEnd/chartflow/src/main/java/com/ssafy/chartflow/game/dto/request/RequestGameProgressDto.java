package com.ssafy.chartflow.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestGameProgressDto {
    private long gameHistoryId;
    private int mode;
    private int quantity;
}
