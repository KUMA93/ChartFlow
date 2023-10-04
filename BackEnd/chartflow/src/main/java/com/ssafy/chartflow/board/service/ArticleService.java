package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.ArticleTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    //키워드 글 목록 조회
    Page<Article> getAllKeywordArticles(String keyword,Pageable pageable);
    //글 목록 조회
    public Page<Article> getAllArticles(Pageable pageable);
    //글 작성
    public void writeArticle(long userId, ArticleTag tag, String title, String content);
    //글 수정
    public void modifyArticle(long articleId, String title,String content);
    //글 삭제
    public void deleteArticle(long articleId);
    //유저 아이디 -> 유저의 게시물 리스트 가져오기
    List<Article> findAllArticleByUserId(long userId);

    public void likeArticle(long userId, long articleId);

    public void withdrawLike(long userId, long articleId);

    public Article findArticleByArticleId(long articleId);


}
