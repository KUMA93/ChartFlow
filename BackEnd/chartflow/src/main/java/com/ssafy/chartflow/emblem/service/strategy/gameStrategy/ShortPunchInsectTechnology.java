package com.ssafy.chartflow.emblem.service.strategy.gameStrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.service.strategy.turnstrategy.TurnStrategy;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShortPunchInsectTechnology implements GameStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        //한 판에 매수 - 매도 3일 이하 3회 이상 , 0일은 노인정

        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "단타충".equals(emblem.getEmblem().getName()));
        if (hasEmblem) return false;

        int preBehavior = -1;
        int preTurn = 0;
        int gapCnt = 0;
        int wholeCnt = 0;

        for (int i = 1; i<gameTurns.size(); i++){
            GameTurns currentTurn = gameTurns.get(i);
            int behavior = currentTurn.getBehavior();
            int curTurn = currentTurn.getTurn();
            if (preBehavior == 0 && behavior == 1) {
                int gap = curTurn - preTurn;
                if (gap <= 3 && gap != 0){
                    ++gapCnt;
                }
            }

            if (gapCnt == 3) ++wholeCnt;
            preBehavior = behavior;

            if (behavior == 0){
                preTurn = curTurn;
            }

            if (wholeCnt == 3)
                return true;
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "단타충";
    }

}
