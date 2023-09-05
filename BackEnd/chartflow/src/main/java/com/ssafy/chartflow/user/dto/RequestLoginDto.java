package com.ssafy.chartflow.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLoginDto {
    private String email;
    private String password;
}
