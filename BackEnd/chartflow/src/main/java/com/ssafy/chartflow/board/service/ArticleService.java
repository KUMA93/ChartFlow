package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;

public interface ArticleService {
    //글 작성
    public ArticleResponseDto writeArticle(String email,String title, String content);
    //글 수정
    public ArticleResponseDto modifyArticle(long articleId, long userId,String title,String content);
    //글 삭제
    public void deleteArticle(long articleId);

    public void likeArticle(long userId, long articleId);

    public void withdrawLike(long likeId);
}
