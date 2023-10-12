package com.ssafy.chartflow.board.dto.request;

import com.ssafy.chartflow.board.entity.ArticleTag;
import lombok.Data;

@Data
public class RequestWriteArticleDto {
    ArticleTag tag;
    String title;
    String content;
}
