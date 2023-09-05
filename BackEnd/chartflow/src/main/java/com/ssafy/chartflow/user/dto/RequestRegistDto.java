package com.ssafy.chartflow.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegistDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
}
