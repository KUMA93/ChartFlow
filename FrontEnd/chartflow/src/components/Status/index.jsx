import styles from "./Status.module.css";
import { useContext, useEffect } from "react";
import GameContext from "../../context/GameContext";
import { loadGameHistory } from "../../services/apis/chartgame";

function Title() {
  return <div className={styles.title}>게임현황</div>;
}

function Line() {
  return <div className={styles.line}></div>;
}

function GameStatus() {
  const {
    assetPer,
    initNum,
    cashNum,
    setCashNum,
    stocksNum,
    setStocksNum,
    avgPriceNum,
    setAvgPriceNum,
    curPriceNum,
    setCurPriceNum,
    flag,
  } = useContext(GameContext);

  useEffect(() => {
    loadGameHistory().then((res) => {
      setCashNum(res.gameHistory.cashBudget);
      setAvgPriceNum(res.gameHistory.price);
      setStocksNum(res.gameHistory.quantity);
    });
  }, [flag]);

  return (
    <>
      <div className={styles.myAsset}>
        <div className={styles.assetText}>총 평가자산</div>
        <div className={styles.assetNum}>
          {typeof stocksNum == "string" || typeof curPriceNum == "string"
            ? "-"
            : `${(cashNum + stocksNum * curPriceNum).toLocaleString()}원`}
        </div>
        <div className={styles.assetPer}>
          {typeof assetPer == "string" ? null : `${assetPer}%`}
        </div>
      </div>
      <div className={styles.container3}>
        <div className={styles.init}>초기자산</div>
        <div className={styles.initNum}>
          {typeof assetPer == "string" ? "-" : `${initNum.toLocaleString()}원`}
        </div>
        <div className={styles.cash}>보유현금</div>
        <div className={styles.cashNum}>{cashNum.toLocaleString()}</div>
        <div className={styles.buystock}>주식매입금</div>
        <div className={styles.buystockNum}>
          {typeof stocksNum == "string" || typeof avgPriceNum == "string"
            ? "-"
            : (stocksNum * avgPriceNum).toLocaleString()}
        </div>
        <div className={styles.stockValue}>주식평가금</div>
        <div className={styles.stockValueNum}>
          {typeof stocksNum == "string" || typeof curPriceNum == "string"
            ? "-"
            : (stocksNum * curPriceNum).toLocaleString()}
        </div>
        <div className={styles.stocks}>주식수</div>
        <div className={styles.stocksNum}>{stocksNum.toLocaleString()}</div>
        <div className={styles.avgPrice}>평단가</div>
        <div className={styles.avgPriceNum}>{avgPriceNum.toLocaleString()}</div>
        <div className={styles.curPrice}>현재가</div>
        <div className={styles.curPriceNum}>{curPriceNum.toLocaleString()}</div>
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
