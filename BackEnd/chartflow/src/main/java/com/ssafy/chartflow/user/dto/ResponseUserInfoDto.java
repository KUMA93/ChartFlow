package com.ssafy.chartflow.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserInfoDto {
    private long id;
    private String name;
    private String nickname;
    private String email;
    private Long selected_emblem;
    private Long ranking;
}
