package com.ssafy.chartflow.info.service;

import com.ssafy.chartflow.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinService {
    private final UserRepository userRepository;

    @Transactional
    public void decreaseCoin(long userId){
        userRepository.decreaseCoinByUserId(userId);
    }
    @Transactional
    public void increaseCoin(long userId){
        userRepository.increaseCoinByUserId(userId);
    }

    @Transactional
    public void increaAllCoin(){
        userRepository.increaseAll();
    }


}
