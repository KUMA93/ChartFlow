package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long>, CustomLikeRepository{
    void deleteByUserIdAndArticleId(Long userId,Long articleId);
}
