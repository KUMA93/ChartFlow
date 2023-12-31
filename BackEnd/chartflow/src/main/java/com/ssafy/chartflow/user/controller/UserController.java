package com.ssafy.chartflow.user.controller;

import com.ssafy.chartflow.exception.NotRegisteredException;
import com.ssafy.chartflow.exception.PasswordWrongException;
import com.ssafy.chartflow.game.dto.request.RequestGameProgressDto;
import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.user.dto.*;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.service.EmailService;
import com.ssafy.chartflow.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "user", description = "회원기능 API")
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@Slf4j
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String AUTH = "Authorization";
    private final JwtService jwtService;

    UserService userService;
    EmailService emailService;

    @Operation(summary = "회원가입", description = "User 객체를 이용해 회원가입을 하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "500", description = "회원가입 실패 - 내부 서버 오류"),
    })
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody RequestRegistDto requestRegistDto) {
        try {
            userService.regist(requestRegistDto.getEmail(), requestRegistDto.getPassword(), requestRegistDto.getName(), requestRegistDto.getNickname());
            log.info("회원가입 성공");

            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            log.info("회원가입 실패 - 서버(DB) 오류");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/auth")
    @Operation(summary = "인증코드 이메일 전송", description = "회원가입 시 인증을 위해 인증코드가 담긴 이메일을 보내준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "메일 전송 성공"),
            @ApiResponse(responseCode = "500", description = "메일 전송 실패 - 호스트 연결 실패")
    })
    public ResponseEntity<?> emailAuthenticationCodeSend(@RequestBody RequestEmailDto requestEmailDto) throws Exception {
        log.info("emailController 호출 - 회원가입 인증코드 발급");
        try {
            // email로 인증 코드 발송 후 authenticationCode에 저장

            String authenticationCode = emailService.sendAuthenticationCode(requestEmailDto.getEmail());
            log.info("인증 코드 생성/발송 성공: " + authenticationCode);
            // 인증 코드 리턴
            return new ResponseEntity<String>(authenticationCode, HttpStatus.OK);
        } catch (Exception e) {
            log.info("인증 코드 생성/발송 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pass")
    @Operation(summary = "임시 비밀번호 이메일 전송", description = "비밀번호를 잊은 상황에서 임시 비밀번호가 담긴 이메일 보내기")
    public ResponseEntity<?> sendTemporaryPassword(@RequestBody RequestEmailDto requestEmailDto) throws Exception {
        log.info("emailController 호출 - 임시 비밀번호 발급: " + requestEmailDto.getEmail());

        try {
            // email로 임시 비밀번호 발송 후 temporaryPassword에 저장
            Map<String, Object> returnData = emailService.sendTemporaryPassword(requestEmailDto.getEmail());
            log.info("임시 비밀번호 생성/발송 성공");
            User user = (User) returnData.get("user");
            String temporaryPassword = (String) returnData.get("temporaryPassword");

            // 임시 비밀번호를 유저 DB에 업데이트
            userService.updatePassword(user, temporaryPassword);
            log.info("임시 비밀번호 DB 업데이트 성공");

            // 인증 코드 리턴
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            log.info("임시 비밀번호 생성/발송 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nickname}")
    @Operation(summary = "닉네임 중복 체크", description = "해당 닉네임이 DB에 있는지 검사한다. ")
    public ResponseEntity<?> verifyNickName(@PathVariable String nickname) throws Exception {
        log.info("emailController 호출 - 닉네임 중복 체크: " + nickname);

        try {
            Map<String, Object> returnData = new HashMap<>();
            boolean isValid = userService.checkNickname(nickname);
            returnData.put("isValid", isValid);
            log.info("닉네임 중복 체크 결과: " + isValid);

            // 인증 코드 리턴
            return new ResponseEntity<Map<String, Object>>(returnData, HttpStatus.OK);
        } catch (Exception e) {
            log.info("닉네임 중복 체크 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    @Operation(summary = "마이페이지에 띄울 정보 불러오기", description = "싹다 불러온다.")
    public ResponseEntity<?> loadMyPage(@RequestHeader("Authorization") String token) throws Exception {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            Long userId = jwtService.extractUserId(token);
            response.put("data", userService.getMyPage(userId));
            response.put("httpStatus", SUCCESS);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("마이페이지 불러오기 실패");
            response.put("httpStatus", FAIL);
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("")
//    @Operation(summary = "내 게시판 활동 불러오기", description = "쓴글과 좋아요 누른글을 불러온다.")
//    public ResponseEntity<?> getMyBoard(@RequestHeader("Authorization") String token) throws Exception {
//        token = token.split(" ")[1];
//        Map<String,Object> response = new HashMap<>();
//
//        try {
//            Long userId = jwtService.extractUserId(token);
//            response.put("data", userService.getMyBoard(userId));
//            response.put("httpStatus", SUCCESS);
//            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            log.info("내 게시판 활동 불러오기 실패");
//            response.put("httpStatus", FAIL);
//            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PatchMapping("")
    @Operation(summary = "마이페이지에서 유저정보 수정하기", description = "유저 정보를 수정한다.")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestHeader("Authorization") String token,
                                                            @RequestBody RequestUpdateDto requestUpdateDto) {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            log.info("User Controller - 유저 정보 수정");
            Long userId = jwtService.extractUserId(token);

            User user = userService.getUser(userId);
            userService.updatePassword(user, requestUpdateDto.getPassword());
            userService.updateNickname(user, requestUpdateDto.getNickname());

            response.put("httpStatus", SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("User Controller - 유저 정보 수정 실패");
            response.put("httpStatus", FAIL);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
