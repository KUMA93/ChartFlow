package com.ssafy.chartflow.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.chartflow.board.entity.Likes;
import com.ssafy.chartflow.board.entity.QLikes;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomLikeRepositoryImp implements CustomLikeRepository {

    private final EntityManager em;

    @Override
    public Likes findLikesByUserIdAndArticleId(Long userId, Long articleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QLikes like = QLikes.likes;

        return queryFactory
                .selectFrom(like)
                .where(like.user.id.eq(userId), like.article.id.eq(articleId))
                .fetchOne();
    }
}
