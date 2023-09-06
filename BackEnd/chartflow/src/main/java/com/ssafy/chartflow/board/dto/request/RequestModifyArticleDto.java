package com.ssafy.chartflow.board.dto.request;

import lombok.Data;

@Data
public class RequestModifyArticleDto {
    Long articleId;
    String title;
    String content;
}
