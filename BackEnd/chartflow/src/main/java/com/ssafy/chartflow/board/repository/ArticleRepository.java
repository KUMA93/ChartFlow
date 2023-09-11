package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    public Article findArticleByArticleId(Long articleId);
    List<Article> findAllByUserUserId(long userId);
}
