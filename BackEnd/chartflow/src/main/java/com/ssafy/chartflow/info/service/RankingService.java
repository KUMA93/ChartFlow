package com.ssafy.chartflow.info.service;

import com.ssafy.chartflow.info.repository.RedisRankingRepository;
import com.ssafy.chartflow.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RankingService {
    private final RedisRankingRepository redisRankingRepository;

    public void initializeRanking(List<User> users) {
        redisRankingRepository.initializeRanking(users);
    }

    public void addUserToRanking(User user){
        redisRankingRepository.addUserToRanking(user);
    }

    public long getUserRank(long userId){
        return redisRankingRepository.getUserRank(userId);
    }

    public Set<?> getRankers(int range){
        return redisRankingRepository.getTopUsers(range);
    }

    public void updateUserRank(User user){
        redisRankingRepository.updateUserBudget(user);
    }
}
