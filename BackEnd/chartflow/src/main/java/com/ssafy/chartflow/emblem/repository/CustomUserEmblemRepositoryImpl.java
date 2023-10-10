package com.ssafy.chartflow.emblem.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.QUserEmblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.user.entity.QUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserEmblemRepositoryImpl implements CustomUserEmblemRepository {
    private final EntityManager em;


    @Override
    public void equipEmblem(UserEmblem emblem) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUserEmblem qUserEmblem = QUserEmblem.userEmblem;
        long emblemId = emblem.getId();
        queryFactory
                .update(qUserEmblem)
                .set(qUserEmblem.equiped, true)
                .where(qUserEmblem.id.eq(emblemId))
                .execute();
    }

    @Override
    public void unequipEmblem(UserEmblem emblem) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUserEmblem qUserEmblem = QUserEmblem.userEmblem;
        long emblemId = emblem.getId();
        queryFactory
                .update(qUserEmblem)
                .set(qUserEmblem.equiped, false)
                .where(qUserEmblem.id.eq(emblemId))
                .execute();
    }
}
