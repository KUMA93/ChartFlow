package com.ssafy.chartflow.game.controller;

import com.ssafy.chartflow.board.service.CommentService;
import com.ssafy.chartflow.game.dto.request.RequestGameProgressDto;
import com.ssafy.chartflow.game.dto.response.ResponseChartDataDto;
import com.ssafy.chartflow.game.dto.response.ResponseGameHistoryDto;
import com.ssafy.chartflow.game.service.GameService;
import com.ssafy.chartflow.security.service.JwtService;
import com.ssafy.chartflow.stocks.entity.Stocks;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "game", description = "차트 게임 API")
@RequestMapping("/game")
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
public class GameController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final JwtService jwtService;
    private final GameService gameService;

    @Operation(summary = "게임 히스토리 조회", description = "해당 유저가 진행중인 게임 중 매 턴마다 endTime이 null인(진행 중인) 게임히스토리 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게임 히스토리 조회 성공"),
            @ApiResponse(responseCode = "500", description = "게임 히스토리 조회 실패 - 내부 서버 오류"),
    })
    @GetMapping("/history")
    public ResponseEntity<Map<String, Object>> getGameHistory(@RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            log.info("Game Controller - 게임 히스토리 조회");
            Long userId = jwtService.extractUserId(token);
            ResponseGameHistoryDto responseGameHistoryDto = gameService.getGameHistory(userId);
            response.put("gameHistory", responseGameHistoryDto);
            response.put("httpStatus", SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Game Controller - 게임 히스토리 조회 실패");
            response.put("httpStatus", FAIL);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "게임 불러오기", description = "해당 유저가 진행중인 게임 중 endTime이 null인(진행 중인) 게임히스토리 정보와 해당 게임의 차트 데이터를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게임 불러오기 성공"),
            @ApiResponse(responseCode = "500", description = "게임 불러오기 실패 - 내부 서버 오류"),
    })
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getGameData(@RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            log.info("Game Controller - 게임 불러오기");
            Long userId = jwtService.extractUserId(token);
            response = gameService.getGameData(userId);

            response.put("httpStatus", SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Game Controller - 게임 불러오기 실패");
            response.put("httpStatus", FAIL);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "게임 시작하기", description = "유저가 게임 시작 버튼을 클릭하면 랜덤 기업의 랜덤 시점으로 게임을 생성하고 gameHistory에 해당 데이터를 삽입한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게임 시작하기 성공"),
            @ApiResponse(responseCode = "500", description = "게임 시작하기 실패 - 내부 서버 오류"),
    })
    @PostMapping()
    public ResponseEntity<Map<String, Object>> startGame(@RequestHeader("Authorization") String token) {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            log.info("Game Controller - 게임 시작하기");
            Long userId = jwtService.extractUserId(token);
            gameService.startGame(userId);
            //List<ResponseChartDataDto> responseStocks = new ArrayList<>();

//            for(Stocks stock : stocks){
//                ResponseChartDataDto responseChartDataDto = ResponseChartDataDto.builder()
//                        .id(stock.getId())
//                        .date(stock.getDate())
//                        .rate(stock.getRate())
//                        .highestPrice(stock.getHighestPrice())
//                        .lowestPrice(stock.getLowestPrice())
//                        .ticker(stock.getTicker())
//                        .volumes(stock.getVolumes())
//                        .closingPrice(stock.getClosingPrice())
//                        .openPrice(stock.getOpenPrice())
//                        .name(stock.getName())
//                        .build();
//                responseStocks.add(responseChartDataDto);
//            }
            response.put("httpStatus", SUCCESS);
//            response.put("previousStocks",responseStocks);
//            response.put("currentDate",responseStocks.get(responseStocks.size()-1).getDate());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Game Controller - 게임 시작하기 실패");
            response.put("httpStatus", FAIL);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "게임 턴 진행", description = "유저가 한 턴에 행동한 내용에 따라 매수, 매도, 스킵, 종료를 수행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게임 턴 진행 성공"),
            @ApiResponse(responseCode = "500", description = "게임 턴 진행 실패 - 내부 서버 오류"),
    })
    @PutMapping()
    public ResponseEntity<Map<String, Object>> progressGame(@RequestHeader("Authorization") String token,
                                                            @RequestBody RequestGameProgressDto requestGameProgressDto) {
        token = token.split(" ")[1];
        Map<String,Object> response = new HashMap<>();

        try {
            log.info("Game Controller - 게임 턴 진행");
            Long userId = jwtService.extractUserId(token);

            int mode = requestGameProgressDto.getMode();
            switch (mode) {
                // 매수
                case 0:
                    log.info("Game Controller - 매수");
                    gameService.buyStocks(requestGameProgressDto.getGameHistoryId(), requestGameProgressDto.getQuantity(), userId);
                    break;

                // 매도
                case 1:
                    log.info("Game Controller - 매도");
                    gameService.sellStocks(requestGameProgressDto.getGameHistoryId(), requestGameProgressDto.getQuantity(), userId);
                    break;

                // 스킵
                case 2:
                    log.info("Game Controller - 스킵");
                    int turn = gameService.skipTurn(requestGameProgressDto.getGameHistoryId(), userId);

                    if (turn < 50) break;

                // 종료
                default:
                    log.info("Game Controller - 종료");
                    gameService.quitGame(requestGameProgressDto.getGameHistoryId(), userId);
                    break;
            }

            response.put("httpStatus", SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Game Controller - 게임 턴 진행 실패");
            response.put("httpStatus", FAIL);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
