package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    public Article findArticleById(Long id);
}
