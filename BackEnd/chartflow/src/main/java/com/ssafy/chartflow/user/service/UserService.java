package com.ssafy.chartflow.user.service;

import com.ssafy.chartflow.info.dto.ResponseAssetsDto;
import com.ssafy.chartflow.info.repository.RedisRankingRepository;
import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.user.dto.RequestLoginDto;
import com.ssafy.chartflow.user.dto.ResponseAuthenticationDto;
import com.ssafy.chartflow.user.dto.ResponseMyPageDto;
import com.ssafy.chartflow.user.dto.ResponseUserInfoDto;
import com.ssafy.chartflow.user.entity.Role;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RedisRankingRepository rankingRepository;

    private static final int IS_CANCELED = 1; // 탈퇴 유저
    private static final int IS_NOT_CANCELED = 0; // 탈퇴 안 한 유저

    public ResponseAuthenticationDto login(RequestLoginDto loginDto) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginDto.getEmail());
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return ResponseAuthenticationDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Boolean checkNickname(String Nickname) {
        log.info("회원 서비스 - 닉네임 중복 체크");
        User user = userRepository.findUserByNickname(Nickname);

        return user == null;
    }

    public User regist(String email, String password, String name, String nickname) {
        log.info("회원가입 서비스 호출 - ");
        // **** 해싱하는 부분 ****
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .nickname(nickname)
                .coin(20)
                .budget(100000000L)
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPass) {
        String encodedPassword = passwordEncoder.encode(newPass);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    public ResponseAssetsDto getAssets(Long userId) {
        User user = userRepository.findUserById(userId);
        return new ResponseAssetsDto(user.getCoin(), user.getBudget());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> getMyPage(Long userId) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findUserById(userId);
        user.setRanking(rankingRepository.getUserRank(userId));
        System.out.println("--------- rank : "+user.getRanking());
        ResponseUserInfoDto userInfoDto = new ResponseUserInfoDto(userId, user.getName(), user.getNickname(), user.getEmail(), user.getSelected_emblem(), user.getRanking());
        ResponseAssetsDto userAssetsDto = new ResponseAssetsDto(user.getCoin(), user.getBudget());
        System.out.println(user);
        System.out.println(userInfoDto);
        response.put("data", new ResponseMyPageDto(userInfoDto, userAssetsDto));
        return response;
    }

}
