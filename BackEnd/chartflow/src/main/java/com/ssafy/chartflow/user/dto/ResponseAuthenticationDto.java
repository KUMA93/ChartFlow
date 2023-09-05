package com.ssafy.chartflow.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAuthenticationDto {
    private String accessToken;
    private String refreshToken;
}

