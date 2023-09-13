package com.ssafy.chartflow.emblem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGameDto {
    private final int TURN=0;
    private final int GAME=1;

    private long userId;
    private int type;
    private double rate;
}
