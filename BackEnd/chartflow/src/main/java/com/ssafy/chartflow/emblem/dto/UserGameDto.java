package com.ssafy.chartflow.emblem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGameDto {
    private final int TURN=0;
    private final int GAME=1;

    private User user;
    private List<Emblem> emblems;
}
