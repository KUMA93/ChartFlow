package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import com.ssafy.chartflow.board.entity.Article;

import java.util.List;

public interface ArticleService {
    //글 작성
    public void writeArticle(long userId,String title, String content);
    //글 수정
    public void modifyArticle(long articleId, String title,String content);
    //글 삭제
    public void deleteArticle(long articleId);
    //유저 아이디 -> 유저의 게시물 리스트 가져오기
    List<Article> findAllArticleByUserId(long userId);

    public void likeArticle(long userId, long articleId);

    public void withdrawLike(long likeId);

    public Article findArticleByArticleId(long articleId);
}
