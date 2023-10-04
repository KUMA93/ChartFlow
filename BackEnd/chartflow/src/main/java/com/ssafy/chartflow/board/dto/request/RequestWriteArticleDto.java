package com.ssafy.chartflow.board.dto.request;

import lombok.Data;

@Data
public class RequestWriteArticleDto {
    String tag;
    String title;
    String content;
}
