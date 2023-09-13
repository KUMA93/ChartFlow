package com.ssafy.chartflow.game.service;

import com.ssafy.chartflow.game.dto.request.RequestGameProgressDto;
import com.ssafy.chartflow.game.dto.response.ResponseGameHistoryDto;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameHistoryStocks;
import com.ssafy.chartflow.game.entity.GameTurns;
import com.ssafy.chartflow.game.repository.GameHistoryStocksRepository;
import com.ssafy.chartflow.game.repository.GameRepository;
import com.ssafy.chartflow.game.repository.GameTurnsRepository;
import com.ssafy.chartflow.stocks.entity.Stocks;
import com.ssafy.chartflow.stocks.repository.StocksRepository;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class GameService {

    private final int BUY = 0;
    private final int SELL = 1;
    private final int SKIP = 2;
    private final int QUIT = 3;

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameTurnsRepository gameTurnsRepository;
    private final GameHistoryStocksRepository gameHistoryStocksRepository;
    private final StocksRepository stocksRepository;

    public ResponseGameHistoryDto getGameHistory(long userId) {

        User user = userRepository.findUserById(userId);
        List<GameHistory> gameHistories = user.getGameHistories();
        ResponseGameHistoryDto responseData = new ResponseGameHistoryDto();

        for (GameHistory gameHistory : gameHistories) {
            if (gameHistory.getEndTime() == null) {
                responseData.setGameHistoryId(gameHistory.getId());
                responseData.setRate(gameHistory.getRate());
                responseData.setPrice(gameHistory.getPrice());
                responseData.setTurn(gameHistory.getTurn());
                responseData.setQuantity(gameHistory.getQuantity());
                responseData.setChartDate(gameHistory.getChartDate());
                responseData.setInitialBudget(gameHistory.getInitialBudget());
                responseData.setCashBudget(gameHistory.getCashBudget());

            }
        }

        return responseData;
    }


    public Map<String, Object> getGameData(long userId) {
        Map<String,Object> response = new HashMap<>();

        User user = userRepository.findUserById(userId);
        List<GameHistory> gameHistories = user.getGameHistories();
        ResponseGameHistoryDto gameHistoryData = new ResponseGameHistoryDto();

        String companyCode = "";

        // 현재 진행중인 game history 조회
        for (GameHistory gameHistory : gameHistories) {
            if (gameHistory.getEndTime() == null) {
                gameHistoryData.setGameHistoryId(gameHistory.getId());
                gameHistoryData.setRate(gameHistory.getRate());
                gameHistoryData.setPrice(gameHistory.getPrice());
                gameHistoryData.setTurn(gameHistory.getTurn());
                gameHistoryData.setQuantity(gameHistory.getQuantity());
                gameHistoryData.setChartDate(gameHistory.getChartDate());
                gameHistoryData.setInitialBudget(gameHistory.getInitialBudget());
                gameHistoryData.setCashBudget(gameHistory.getCashBudget());

                companyCode = gameHistory.getCompanyCode();
                break;
            }
        }

        response.put("gameHistory", gameHistoryData);

        // companyCode를 통해 전체 차트데이터 조회
        List<Stocks> stocksList = stocksRepository.findAllByTicker(companyCode);
        response.put("chartData", stocksList);

        return response;
    }

    public void startGame(long userId) {

        User user = userRepository.findUserById(userId);

        // 랜덤 주식 정보에 access, PK는 1 ~ 390094 범위
        Random random = new Random();
        // 13만 ~ 26만 범위 난수 생성
        long stockId = random.nextInt(130000) + 130000;

        Stocks stock = stocksRepository.findStocksById(stockId);

        GameHistory gameHistory = GameHistory.builder()
                .companyCode(stock.getTicker())
                .chartDate(stock.getDate())
                .startTime(LocalDateTime.now())
                .initialBudget(user.getBudget())
                .cashBudget(user.getBudget())
                .turn(0)
                .rate(0)
                .price(0)
                .quantity(0)
                .build();


        // 51개 만큼 주식 데이터 저장
        List<Stocks> stocks = stocksRepository.findAllByTicker(stock.getTicker());

        int cnt = 0;
        for (Stocks cur : stocks) {
            if (cnt >= 51) break;
            if (cur.getDate().isAfter(stock.getDate())) {
                cnt++;
                GameHistoryStocks gameHistoryStocks = new GameHistoryStocks();
                gameHistoryStocks.setGameHistory(gameHistory);
                gameHistoryStocks.setStocks(cur);

                gameHistoryStocksRepository.save(gameHistoryStocks);
            }
        }

        gameHistory.setUser(user);

        userRepository.save(user);
        gameRepository.save(gameHistory);

        log.info("등록된 game history : " + gameHistory);
    }

    public void buyStocks(long gameHistoryId, int quantity, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);
        int turn = gameHistory.getTurn();

        // 금일 가격 -> 최고가 + 최저가 평균
        List<GameHistoryStocks> gameHistoryStocks = gameHistory.getGameHistoryStocks();
        GameHistoryStocks today = gameHistoryStocks.get(turn);
        long todayStockId = today.getStocks().getId();
        Stocks todayStock = stocksRepository.findStocksById(todayStockId);

        int price = (todayStock.getHighestPrice() + todayStock.getLowestPrice()) / 2;

        // 현금 자산 차감 / 평단가, 수량 설정
        // cashBudget + price * quantity = 총 자산(user.budget)

        gameHistory.setCashBudget(gameHistory.getCashBudget() - price * quantity);

        int quant = gameHistory.getQuantity() + quantity;
        int avg = (gameHistory.getPrice() * gameHistory.getQuantity() + price * quantity) / quant;

        gameHistory.setPrice(avg);
        gameHistory.setQuantity(quant);

        GameTurns gameTurns = GameTurns.builder()
                .behavior(BUY)
                .turn(turn)
                .price(price)
                .quantity(quantity)
                .build();

        gameTurns.setGameHistory(gameHistory);

        gameTurnsRepository.save(gameTurns);
        gameRepository.save(gameHistory);
    }

    public void sellStocks(long gameHistoryId, int quantity, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);
        int turn = gameHistory.getTurn();

        // 금일 가격 -> 최고가 + 최저가 평균
        List<GameHistoryStocks> gameHistoryStocks = gameHistory.getGameHistoryStocks();
        GameHistoryStocks today = gameHistoryStocks.get(turn);
        long todayStockId = today.getStocks().getId();
        Stocks todayStock = stocksRepository.findStocksById(todayStockId);

        int price = (todayStock.getHighestPrice() + todayStock.getLowestPrice()) / 2;

        // 현금 자산 증가 / 평단가, 수량 설정
        long sum =  (gameHistory.getInitialBudget() - gameHistory.getCashBudget());

        gameHistory.setCashBudget(gameHistory.getCashBudget() + price * quantity);

        int quant = gameHistory.getQuantity() - quantity;
        int avg = 0;
        if (quant != 0){
            avg = (int) ((sum - price * quantity) / quant);
        }

        gameHistory.setPrice(avg);
        gameHistory.setQuantity(quant);

        GameTurns gameTurns = GameTurns.builder()
                .behavior(SELL)
                .turn(turn)
                .price(price)
                .quantity(quantity)
                .build();

        gameTurns.setGameHistory(gameHistory);

        gameTurnsRepository.save(gameTurns);
        gameRepository.save(gameHistory);
    }

    public void skipTurn(long gameHistoryId, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);

        int turn = gameHistory.getTurn();

        // 금일 가격 -> 최고가 + 최저가 평균
        List<GameHistoryStocks> gameHistoryStocks = gameHistory.getGameHistoryStocks();
        GameHistoryStocks today = gameHistoryStocks.get(turn);
        long todayStockId = today.getStocks().getId();
        Stocks todayStock = stocksRepository.findStocksById(todayStockId);

        int price = (todayStock.getHighestPrice() + todayStock.getLowestPrice()) / 2;

        // 내일 차트 정보 받아오기
        GameHistoryStocks tomorrow = gameHistoryStocks.get(turn + 1);
        long tomorrowStockId = tomorrow.getStocks().getId();
        Stocks tomorrowStock = stocksRepository.findStocksById(tomorrowStockId);
        LocalDate date = tomorrowStock.getDate();

        gameHistory.setChartDate(date);

        // 해당 턴 동안의 등락률 계산,
//        long totalAssets = gameHistory.getCashBudget();
//
//        // 전 날 대비 종가가 상승한 경우
//        if (price <= tomorrowStock.getClosingPrice()) {
//            totalAssets += (long) tomorrowStock.getHighestPrice() * quant;
//        }
//        // 전 날 대비 종가가 하락한 경우
//        else {
//            totalAssets -= (long) tomorrowStock.getLowestPrice() * quant;
//        }
//
//        gameHistory.setRate(Math.round((totalAssets - gameHistory.getInitialBudget()) / gameHistory.getInitialBudget() * 100 * 100) / 100.0);

        // 변동 사항 유저 정보에 저장 & 턴 증가

        gameHistory.setTurn(turn + 1);
        gameHistory.setUser(user);

        gameRepository.save(gameHistory);
        userRepository.save(user);
    }

    public void quitGame(long gameHistoryId, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);

    }

}

