package com.ssafy.chartflow.info.repository;

import com.ssafy.chartflow.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisRankingRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void initializeRanking(List<User> users) {
        for (User user : users) {
            log.info(user.getUsername());
            redisTemplate.opsForZSet().add("userRanking", String.valueOf(user.getId()), user.getBudget());
        }
    }
    public void addUserToRanking(User user) {
        redisTemplate.opsForZSet().add("userRanking", String.valueOf(user.getId()), user.getBudget());
    }

    public Long getUserRank(long userId) {
        // 내림차순으로 정렬된 경우의 랭킹
        Long rank = redisTemplate.opsForZSet().reverseRank("userRanking", userId);

        // 랭킹은 0-based index이므로, 실제 랭킹은 rank + 1이 됩니다.
        return (rank != null) ? rank + 1 : null;
    }

    public Set<Object> getTopUsers(int topN) {
        return redisTemplate.opsForZSet().reverseRange("userRanking", 0, topN - 1);
    }

    public void updateUserBudget(User user) {
        long userId= user.getId();
        long newBudget = user.getBudget();
        // 기존 랭킹 정보를 삭제
        redisTemplate.opsForZSet().remove("userRanking", String.valueOf(user.getId()));

        // 새로운 budget으로 랭킹 정보를 업데이트
        redisTemplate.opsForZSet().add("userRanking", String.valueOf(user.getId()), newBudget);
    }
}