package com.ssafy.chartflow.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.chartflow.user.entity.QUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final EntityManager em;

    @Override
    public void decreaseCoinByUserId(long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser user = QUser.user;
        queryFactory
                .update(user)
                .set(user.coin, user.coin.subtract(1))
                .where(user.id.eq(userId))
                .execute();
    }

    @Override
    public void increaseCoinByUserId(long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser user = QUser.user;
        queryFactory
                .update(user)
                .set(user.coin, user.coin.add(1))
                .where(user.id.eq(userId))
                .execute();
    }

    @Override
    public void increaseAll() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser user = QUser.user;
        queryFactory
                .update(user)
                .set(user.coin, user.coin.add(1))
                .execute();
    }
}
