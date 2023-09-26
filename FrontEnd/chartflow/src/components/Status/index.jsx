import styles from "./Status.module.css";
import { useState, useEffect, useContext } from "react";
import { loadGameHistory } from "../../services/apis/chartgame";
import GameContext from "../../context/GameContext";

function Title() {
  return <div className={styles.title}>게임현황</div>;
}

function Line() {
  return <div className={styles.line}></div>;
}

function GameStatus() {
  const { gameId, setGameId, thisTurn, setThisTurn } = useContext(GameContext);
  useEffect(() => {
    loadGameHistory().then((res) => {
      setThisTurn(res.gameHistory.turn);
      setGameId(res.gameHistoryId);
      setInitNum(res.gameHistory.initialBudget);
      setCashNum(res.gameHistory.cashBudget);
      setAvgPriceNum(res.gameHistory.price);
      setStocksNum(res.gameHistory.quantity);
      setAssetPer(res.gameHistory.rate);
    });
  }, []);

  const [assetPer, setAssetPer] = useState(1);
  const [initNum, setInitNum] = useState(1);
  const [cashNum, setCashNum] = useState(1);
  const [stocksNum, setStocksNum] = useState(1);
  const [avgPriceNum, setAvgPriceNum] = useState(1);
  const [curPriceNum, setCurPriceNum] = useState(1);

  return (
    <>
      <div className={styles.myAsset}>
        <div className={styles.assetText}>총 평가자산</div>
        <div className={styles.assetNum}>
          {stocksNum
            ? cashNum + stocksNum * curPriceNum
            : cashNum.toLocaleString()}
          원
        </div>
        <div className={styles.assetPer}>{assetPer ? assetPer + "%" : "-"}</div>
      </div>
      <div className={styles.container3}>
        <div className={styles.init}>초기자산</div>
        <div className={styles.initNum}>
          {initNum ? initNum.toLocaleString() : "-"}원
        </div>
        <div className={styles.cash}>보유현금</div>
        <div className={styles.cashNum}>
          {cashNum ? cashNum.toLocaleString() : "-"}
        </div>
        <div className={styles.buystock}>주식매입금</div>
        <div className={styles.buystockNum}>
          {stocksNum ? (stocksNum * avgPriceNum).toLocaleString() : "-"}
        </div>
        <div className={styles.stockValue}>주식평가금</div>
        <div className={styles.stockValueNum}>
          {stocksNum ? (stocksNum * curPriceNum).toLocaleString() : "-"}
        </div>
        <div className={styles.stocks}>주식수</div>
        <div className={styles.stocksNum}>
          {stocksNum ? stocksNum.toLocaleString() : "-"}
        </div>
        <div className={styles.avgPrice}>평단가</div>
        <div className={styles.avgPriceNum}>
          {avgPriceNum ? avgPriceNum.toLocaleString() : "-"}
        </div>
        <div className={styles.curPrice}>현재가</div>
        <div className={styles.curPriceNum}>
          {curPriceNum ? curPriceNum.toLocaleString() : "-"}원
        </div>
      </div>
    </>
  );
}

function Status() {
  return (
    <div className={styles.container2}>
      <Title />
      <Line />
      <GameStatus />
    </div>
  );
}

export default Status;
