package com.ssafy.chartflow.security.controller;

import com.ssafy.chartflow.security.entity.RefreshToken;
import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.user.dto.RequestLoginDto;
import com.ssafy.chartflow.user.dto.ResponseAuthenticationDto;
import com.ssafy.chartflow.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "auth", description = "인증인가 API")
@RequestMapping("/auth")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @Operation(summary = "로그인", description = "email과 password 기반 로그인 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 실패 - 로그인 권한 없음"),
            @ApiResponse(responseCode = "500", description = "로그인 실패 - 내부 서버 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody RequestLoginDto requestLoginDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        try {
            ResponseAuthenticationDto response = userService.login(requestLoginDto);
            resultMap.put("message", "Success");
            resultMap.put("access-token", response.getAccessToken());
            resultMap.put("refresh-token", response.getRefreshToken());
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", "Login failed: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "refresh", description = "토큰 재발급 기능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "재발급 성공"),
            @ApiResponse(responseCode = "500", description = "로그인 실패 - 내부 서버 오류")
    })
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshToken(
            @RequestHeader("Authorization") String accessToken,
            @RequestHeader("Refresh-token") String refreshToken
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        if (refreshToken == null || refreshToken.isBlank()) {
            resultMap.put("message", "Refresh token is required");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        try {
            String newAccessToken = jwtService.refreshToken(accessToken, refreshToken);

            resultMap.put("accessToken", newAccessToken);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        } catch (Exception e) {
            resultMap.put("message", "Invalid refresh token");
            return new ResponseEntity<>(resultMap, HttpStatus.UNAUTHORIZED);
        }
    }

}
