package com.ssafy.chartflow.board.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleResponseDto {
    String title;
    String nickName;
    LocalDateTime registerTime;
    int views;
    long id;
    String content;
}
