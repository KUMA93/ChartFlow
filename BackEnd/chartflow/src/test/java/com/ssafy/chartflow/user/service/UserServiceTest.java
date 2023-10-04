package com.ssafy.chartflow.user.service;

import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.repository.EmblemRepository;
import com.ssafy.chartflow.emblem.repository.UserEmblemRepository;
import com.ssafy.chartflow.info.service.CoinService;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserEmblemRepository userEmblemRepository;
    @Autowired
    EmblemRepository emblemRepository;

    @Autowired
    CoinService coinService;
    @Test
    public void 마이페이지테스트(){
        User user = userRepository.findUserById(8);
        Emblem emblem = emblemRepository.findByName("한강의아들");
        UserEmblem userEmblem = UserEmblem.builder()
                .emblem(emblem)
                .user(user)
                .equiped(true)
                .build();

        userEmblemRepository.save(userEmblem);

        log.info("userEmblem = {}", userEmblemRepository.findUserEmblemByUserAndEquipedIsTrue(user));
    }

    @Test
    public void 코인테스트(){
        User user = userRepository.findUserById(8);
        log.info("coin = {}",user.getCoin());
        coinService.decreaseCoin(8);
        log.info("coin = {}",user.getCoin());
    }
}