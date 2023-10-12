package com.ssafy.chartflow.info.controller;

import com.ssafy.chartflow.emblem.service.UserEmblemService;
import com.ssafy.chartflow.info.dto.ResponseAssetsDto;
import com.ssafy.chartflow.info.service.RankingService;
import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "info", description = "유저 히스토리")
@RequestMapping("/info")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class InfoController {
    private final UserService userService;
    private final JwtService jwtService;
    private final RankingService rankingService;
    private final UserEmblemService userEmblemService;

    @Operation(summary = "유저 자산", description = "유저 코인 및 잔액 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "자산 조회 성공"),
            @ApiResponse(responseCode = "500", description = "로그인 실패 - 내부 서버 오류")
    })
    @GetMapping("/assets")
    public ResponseEntity<Map<String, Object>> userAssets(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token) {
        Map<String,Object> response = new HashMap<>();
        token = token.split(" ")[1];

        try {
            Long userId = jwtService.extractUserId(token);
            ResponseAssetsDto dto = userService.getAssets(userId);
            response.put("assets" , dto);
            response.put("message", "success");
        } catch (Exception e) {
            response.put("message", "유저자산조회 실패");
            log.error("user Assets 오류 - {}",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "유저 랭킹", description = "유저 랭킹 상위 10명 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "랭킹 조회 성공"),
            @ApiResponse(responseCode = "500", description = "랭킹 조회 실패 - 내부 서버 오류")
    })
    @GetMapping("/rankings/{limit}")
    public ResponseEntity<Map<String, Object>> userRankings(@PathVariable int limit) {
        Map<String,Object> response = new HashMap<>();

        try {
            response.put("rankings",rankingService.getRankers(limit));
        } catch (Exception e) {
            response.put("message", "랭킹 조회 실패");
            log.error("랭킹 조회 오류 - {}",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/emblems")
//    public ResponseEntity<Map<String, Object>> userEmblems(
//            @Parameter(hidden = true)
//            @RequestHeader("Authorization") String token){
//        Map<String,Object> response = new HashMap<>();
//        try {
//            Long userId = jwtService.extractUserId(token);
//            List<String> emblems = userEmblemService
//            response.put("assets" , dto);
//            response.put("message", "success");
//        } catch (Exception e) {
//            response.put("message", "유저자산조회 실패");
//            log.error("user Assets 오류 - {}",e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
