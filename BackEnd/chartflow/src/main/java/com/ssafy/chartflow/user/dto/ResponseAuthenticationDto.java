package com.ssafy.chartflow.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuthenticationDto {
    private String accessToken;
    private String refreshToken;
}

