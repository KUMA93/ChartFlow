package com.ssafy.chartflow.user.dto;

import com.ssafy.chartflow.info.dto.ResponseAssetsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMyPageDto {
    private ResponseUserInfoDto userInfoDto;
    private ResponseAssetsDto userAssetsDto;
}
