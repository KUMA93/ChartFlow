package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Page<Article> findAllByTitleLike(String keyword,Pageable pageable);
    Page<Article> findAll(Pageable pageable);
    Article findArticleById(Long articleId);
    List<Article> findAllByUserId(long userId);
}
