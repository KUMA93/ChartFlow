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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.chartflow.emblem.entity.QUserEmblem.userEmblem;

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
        userEmblem.setEquiped(false);
        userEmblem.setUser(user);
        userEmblem.setEmblem(emblem);

        userEmblemRepository.save(userEmblem);
        ResponseEmblemDto emblemDto = ResponseEmblemDto.builder()
                .name(emblem.getName())
                .description(emblem.getDescription())
                .build();
        return emblemDto;
    }

    public List<UserEmblem> getUserEmblems(User user){
        return userEmblemRepository.findUserEmblemByUser(user);
    }

    public UserEmblem getUserEquipedEmblem(User user){
        return userEmblemRepository.findUserEmblemByUserAndEquipedIsTrue(user);
    }

    @Transactional
    public void equipEmblem(User user, long userEmblemId){
        UserEmblem emblem = userEmblemRepository.findById(userEmblemId).get();
        userEmblemRepository.equipEmblem(emblem);
    }
}
