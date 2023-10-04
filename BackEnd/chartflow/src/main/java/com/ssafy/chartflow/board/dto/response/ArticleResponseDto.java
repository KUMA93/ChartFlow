package com.ssafy.chartflow.board.dto.response;

import com.ssafy.chartflow.board.entity.ArticleTag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleResponseDto {
    ArticleTag tag;
    String title;
    String nickName;
    LocalDateTime registerTime;
    int views;
    long id;
    String content;
    int likes;

}
