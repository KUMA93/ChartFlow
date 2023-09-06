package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;

public interface BoardService {
    //글 작성
    public ArticleResponseDto getArticle(String email,String content);
    //글 수정
    public ArticleResponseDto modifyArticle(long articleId,String content);
    //글 삭제
    public void deleteArticle(long articleId);
}
