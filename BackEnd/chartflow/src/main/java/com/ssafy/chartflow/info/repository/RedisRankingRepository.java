package com.ssafy.chartflow.info.repository;

import com.ssafy.chartflow.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisRankingRepository {

    private final StringRedisTemplate redisTemplate;

    public void initializeRanking(List<User> users) {
        redisTemplate.delete("userRanking");
        for (User user : users) {
            log.info(user.getUsername());
            redisTemplate.opsForZSet().add("userRanking", String.valueOf(user.getNickname()), user.getBudget());
        }
    }
    public void addUserToRanking(User user) {
        redisTemplate.opsForZSet().add("userRanking", String.valueOf(user.getId()), user.getBudget());
    }

    public Long getUserRank(long userId) {
        
        try {
            // 내림차순으로 정렬된 경우의 랭킹
            // Redis는 기본적으로 string으로 처리하기에 userId를 String으로 변환하여 조회하기
            String userIdStr = String.valueOf(userId);
            Long rank = redisTemplate.opsForZSet().reverseRank("userRanking", userIdStr);

            // 랭킹은 0-based index이므로, 실제 랭킹은 rank + 1이 됩니다.
            return (rank != null) ? rank + 1 : null;
        }catch (Exception e){
            log.info("RedisRankingRepository getUserRank오류 발생 : " + e.getMessage());
        }
        return null;
    }

    public Map<?,?> getTopUsers(int topN) {
        Set<ZSetOperations.TypedTuple<String>> topUsers = redisTemplate.opsForZSet().reverseRangeWithScores("userRanking", 0, topN - 1);
        assert topUsers != null;
        TreeMap<String,Double> userwithScore = new TreeMap<>();
        for (ZSetOperations.TypedTuple<String> user : topUsers) {
            String userName = user.getValue();
            double asset = user.getScore();
            userwithScore.put(userName, asset);
        }

        return userwithScore;
        //return redisTemplate.opsForZSet().range("userRanking", 0, topN - 1);
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