package com.ssafy.chartflow.game.service;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.EmblemService;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private final int DRAW = 0;
    private final int WIN = 1;
    private final int LOSE = 2;

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameTurnsRepository gameTurnsRepository;
    private final GameHistoryStocksRepository gameHistoryStocksRepository;
    private final StocksRepository stocksRepository;

    private final EmblemService emblemService;

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

    //특정 주가 , 주식 날짜 정보

    @Transactional(readOnly = true)
    public Map<String, Object> getGameData(long userId) {
        Map<String,Object> response = new HashMap<>();

        User user = userRepository.findUserById(userId);
        List<GameHistory> gameHistories = user.getGameHistories();
        ResponseGameHistoryDto gameHistoryData = new ResponseGameHistoryDto();

        String companyCode = "";

        // 현재 진행중인 game history 조회
        for (GameHistory gameHistory : gameHistories) {
            log.info("======== search gameHistory ========");
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
                log.info("=========== companyCode : " + companyCode);
                break;
            }
        }

        response.put("gameHistory", gameHistoryData);

        // companyCode 전 1년 차트 데이터
        String ticker = gameHistories.get(gameHistories.size()-1).getCompanyCode();
        String date = gameHistories.get(gameHistories.size()-1).getChartDate();
        List<Stocks> stocksList = stocksRepository.findAllPreviousStocks(ticker,date);

        // 앞으로 51개 만큼의 차트 데이터 추가
        List<GameHistoryStocks> after = gameHistories.get(gameHistories.size()-1).getGameHistoryStocks();
        for (GameHistoryStocks cur : after) {
            stocksList.add(cur.getStocks());
        }
        response.put("chartData", stocksList);

        return response;
    }

    public void startGame(long userId) {

        User user = userRepository.findUserById(userId);

        // 랜덤 주식 정보에 access, PK는 1 ~ 3000000 범위
        Random random = new Random();
        // 100만 ~ 200만 범위 난수 생성
        long stockId = random.nextInt(1000000) + 1000000;

        Stocks stock = stocksRepository.findStocksById(stockId);
        log.info("주식 PK: " + stockId);
        GameHistory gameHistory = GameHistory.builder()
                .companyCode(stock.getTicker())
                .chartDate(stock.getDate())
                .startTime(LocalDateTime.now())
                .initialBudget(user.getBudget())
                .cashBudget(user.getBudget())
                .turn(1)
                .rate(0.00)
                .price(0)
                .quantity(0)
                .build();
        log.info("게임 히스토리 생성 완료 - " + gameHistory.toString());

        // 51개 만큼 주식 데이터 저장 게임을 진행해야하는 데이터
        List<Stocks> stocks = stocksRepository.findAllByTicker(stock.getTicker());
//        int dayOfYear = 365;
//        // 앞의 300개 데이터
//        stocks = stocks.subList(dayOfYear + 10 , stocks.size() - dayOfYear );
//        Stocks firstStocks = stocks.get(0);
//        List<Stocks> allPreviousStocks = stocksRepository.findAllPreviousStocks(firstStocks.getTicker(), firstStocks.getDate());

        gameHistory.setUser(user);

        userRepository.save(user);
        gameRepository.save(gameHistory);


        int cnt = 0;
        for (Stocks cur : stocks) {
            if (cnt > 51) break;
            if (Integer.parseInt(cur.getDate()) >= Integer.parseInt(stock.getDate())) {
                cnt++;
                GameHistoryStocks gameHistoryStocks = new GameHistoryStocks();
                gameHistoryStocks.setGameHistory(gameHistory);
                gameHistoryStocks.setStocks(cur);

                gameHistoryStocksRepository.save(gameHistoryStocks);
            }
        }

        return;
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

    public int skipTurn(long gameHistoryId, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);

        int turn = gameHistory.getTurn();

        // 금일 가격 -> 최고가 + 최저가 평균
        List<GameHistoryStocks> gameHistoryStocks = gameHistory.getGameHistoryStocks();
        GameHistoryStocks today = gameHistoryStocks.get(turn);
        long todayStockId = today.getStocks().getId();
        Stocks todayStock = stocksRepository.findStocksById(todayStockId);

        // 내일 차트 정보 받아오기
        GameHistoryStocks tomorrow = gameHistoryStocks.get(turn + 1);
        long tomorrowStockId = tomorrow.getStocks().getId();
        Stocks tomorrowStock = stocksRepository.findStocksById(tomorrowStockId);
        String date = tomorrowStock.getDate();

        int price = (tomorrowStock.getHighestPrice() + tomorrowStock.getLowestPrice()) / 2;
        gameHistory.setChartDate(date);

        // 해당 턴 동안의 등락률 계산
        long cashBudget = gameHistory.getCashBudget();
        long initialBudget = gameHistory.getInitialBudget();
        long currentStocks = initialBudget - cashBudget;
        long totalAssets = cashBudget + price * gameHistory.getQuantity();

        long tomorrowStocks = (long) price * gameHistory.getQuantity();

        long gap = tomorrowStocks - currentStocks;

        user.setBudget(totalAssets);
        gameHistory.setRate(Math.round((float) (totalAssets - initialBudget) / initialBudget * 100 * 100) / 100.0);

        // 유저 자산에 반영
        gameHistory.setUser(user);

        // gameTurns 반영
        GameTurns gameTurns = GameTurns.builder()
                .behavior(SKIP)
                .turn(turn)
                .price(gameHistory.getPrice())
                .quantity(gameHistory.getQuantity())
                .currentStocks(currentStocks)
                .cashBudget(cashBudget)
                .totalAssets(totalAssets)
                .rate(gameHistory.getRate())
                .todayPrice(price)
                .build();


        gameTurns.setGameHistory(gameHistory);

        gameTurnsRepository.save(gameTurns);
        // 매 턴마다 칭호 획득 검사
        UserGameDto userGameDto = UserGameDto.builder()
                        .user(user)
                        .gameTurns(gameTurns)
                        .gameHistory(gameHistory)
                        .build();

        emblemService.notifyObserver(userGameDto, 0);

        // 턴 증가
        gameHistory.setTurn(turn + 1);

        gameRepository.save(gameHistory);
        userRepository.save(user);

        return turn;
    }

    public void quitGame(long gameHistoryId, long userId) {
        User user = userRepository.findUserById(userId);
        GameHistory gameHistory = gameRepository.findGameHistoryById(gameHistoryId);
        int turn = gameHistory.getTurn();

        // 금일 가격 -> 최고가 + 최저가 평균
        List<GameHistoryStocks> gameHistoryStocks = gameHistory.getGameHistoryStocks();
        GameHistoryStocks today = gameHistoryStocks.get(turn);
        long todayStockId = today.getStocks().getId();
        Stocks todayStock = stocksRepository.findStocksById(todayStockId);

        int price = (todayStock.getHighestPrice() + todayStock.getLowestPrice()) / 2;

        // 가지고 있는 주식 전량 매도
        long cashBudget = gameHistory.getCashBudget();
        long initialBudget = gameHistory.getInitialBudget();
        long totalAssets = cashBudget + price * gameHistory.getQuantity();

        double rate = Math.round((float) (totalAssets - initialBudget) / initialBudget * 100 * 100) / 100.0;

        user.setBudget(totalAssets);
        gameHistory.setRate(rate);

        if (rate > 0) gameHistory.setResult(WIN);
        else if (rate == 0) gameHistory.setResult(DRAW);
        else gameHistory.setResult(LOSE);

        gameHistory.setPrice(0);
        gameHistory.setQuantity(0);
        gameHistory.setEndTime(LocalDateTime.now());
        gameHistory.setCashBudget(totalAssets);

        // 유저 자산에 반영
        gameHistory.setUser(user);

        // gameTurns 반영
        GameTurns gameTurns = GameTurns.builder()
                .behavior(QUIT)
                .turn(turn)
                .price(0)
                .quantity(0)
                .currentStocks(0)
                .cashBudget(totalAssets)
                .totalAssets(totalAssets)
                .rate(rate)
                .todayPrice(price)
                .build();

        gameTurns.setGameHistory(gameHistory);

        gameTurnsRepository.save(gameTurns);
        gameRepository.save(gameHistory);
        userRepository.save(user);
    }

}

