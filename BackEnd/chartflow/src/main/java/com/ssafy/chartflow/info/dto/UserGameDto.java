package com.ssafy.chartflow.info.dto;

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

    private Long userId;
    private int type;

    private class Turn{
        
    }

    private class Game{

    }
}
