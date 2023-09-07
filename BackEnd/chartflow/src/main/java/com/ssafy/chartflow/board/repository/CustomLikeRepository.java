package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Likes;

import java.util.List;

public interface CustomLikeRepository {

    public Likes findLikesByUserIdAndArticleId(Long userId, Long articleId);
}
