package com.ssafy.chartflow.emblem.service;

import com.ssafy.chartflow.emblem.dto.ResponseEmblemDto;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.repository.EmblemRepository;
import com.ssafy.chartflow.emblem.repository.UserEmblemRepository;
import com.ssafy.chartflow.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEmblemService {
    private final UserEmblemRepository userEmblemRepository;
    private final EmblemRepository emblemRepository;
    public ResponseEmblemDto saveEmblem(UserGameDto userGameDto, String title){
        Emblem emblem = emblemRepository.findByName(title);
        User user = userGameDto.getUser();

        UserEmblem userEmblem = new UserEmblem();
        userEmblem.setUser(user);
        userEmblem.setEmblem(emblem);

        userEmblemRepository.save(userEmblem);
        ResponseEmblemDto emblemDto = ResponseEmblemDto.builder()
                .name(emblem.getName())
                .description(emblem.getDescription())
                .build();
        return emblemDto;
    }
}
