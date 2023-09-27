package com.ssafy.chartflow.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.chartflow.board.entity.QLikes;
import com.ssafy.chartflow.user.entity.QUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImp implements CustomUserRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    private final QUser user = QUser.user;
    @Override
    public void decreaseCoinByUserId(long userId) {
        queryFactory
                .update(user)
                .set(user.coin, user.coin.subtract(1))
                .where(user.id.eq(userId))
                .execute();
    }

    @Override
    public void increaseCoinByUserId(long userId) {
        queryFactory
                .update(user)
                .set(user.coin, user.coin.add(1))
                .where(user.id.eq(userId))
                .execute();
    }

    @Override
    public void increaseAll() {
        queryFactory
                .update(user)
                .set(user.coin, user.coin.add(1))
                .execute();
    }
}
