package com.ssafy.chartflow.board.dto.request;

import lombok.Data;

@Data
public class RequestWriteArticleDto {
    String email;
    String title;
    String content;
}
