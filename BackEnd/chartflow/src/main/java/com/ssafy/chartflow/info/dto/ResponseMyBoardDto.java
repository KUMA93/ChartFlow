package com.ssafy.chartflow.info.dto;

import com.ssafy.chartflow.board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMyBoardDto {
    private List<Article> myArticles;
    private List<Article> likes;
}
