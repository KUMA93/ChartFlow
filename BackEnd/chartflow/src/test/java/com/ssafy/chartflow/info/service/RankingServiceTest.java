package com.ssafy.chartflow.info.service;

import static org.mockito.Mockito.*;

import com.ssafy.chartflow.info.repository.RedisRankingRepository;
import com.ssafy.chartflow.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class RankingServiceTest {

    @InjectMocks
    private RankingService rankingService;

    @Mock
    private RedisRankingRepository redisRankingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeRanking() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        rankingService.initializeRanking(users);

        verify(redisRankingRepository, times(1)).initializeRanking(users);
    }

    @Test
    void testAddUserToRanking() {
        User user = new User();
        rankingService.addUserToRanking(user);

        verify(redisRankingRepository, times(1)).addUserToRanking(user);
    }

    @Test
    void testGetUserRank() {
        long userId = 1L;

        rankingService.getUserRank(userId);

        verify(redisRankingRepository, times(1)).getUserRank(userId);
    }

    @Test
    void testGetRankers() {
        int range = 10;

        rankingService.getRankers(range);

        verify(redisRankingRepository, times(1)).getTopUsers(range);
    }

    @Test
    void testUpdateUserRank() {
        User user = new User();

        rankingService.updateUserRank(user);

        verify(redisRankingRepository, times(1)).updateUserBudget(user);
    }
}
