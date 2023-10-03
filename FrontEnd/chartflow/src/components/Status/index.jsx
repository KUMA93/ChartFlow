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
    assetNum,
    setAssetNum,
    assetPer,
    setAssetPer,
    assetGap,
    setAssetGap,
    initNum,
    cashNum,
    setCashNum,
    stocksAmt,
    setStocksAmt,
    stocksNum,
    setStocksNum,
    avgPriceNum,
    setAvgPriceNum,
    curPriceNum,
    flag,
  } = useContext(GameContext);

  useEffect(() => {
    loadGameHistory().then((res) => {
      setCashNum(res.gameHistory.cashBudget);
      setAvgPriceNum(res.gameHistory.price);
      setStocksNum(res.gameHistory.quantity);
    });
  }, [flag]);

  setAssetNum(
    typeof stocksNum == "string" || typeof curPriceNum == "string"
      ? "-"
      : cashNum + stocksNum * curPriceNum
  );
  setStocksAmt(
    typeof stocksNum == "string" || typeof avgPriceNum == "string"
      ? "-"
      : stocksNum * avgPriceNum
  );

  setAssetPer(((assetNum / initNum - 1) * 100).toFixed(2));

  setAssetGap(assetNum - initNum);

  let assetColor;
  if (assetGap > 0) {
    assetColor = styles.blueText;
  } else if (assetGap < 0) {
    assetColor = styles.redText;
  } else {
    assetColor = styles.blackText;
  }
  return (
    <>
      <div className={styles.myAsset}>
        <div className={styles.assetText}>총 평가자산</div>
        <div className={styles.assetNum}>
          {typeof assetNum == "string" ? "-" : `${assetNum.toLocaleString()}원`}
        </div>
        <div className={`${styles.assetPer} ${assetColor}`}>
          {isNaN(assetGap) ? "-" : `${assetGap.toLocaleString()}`}(
          {isNaN(assetPer)
            ? "-%"
            : assetPer > 0
            ? `+${assetPer}%`
            : `${assetPer}%`}
          )
        </div>
      </div>
      <div className={styles.container3}>
        <div className={styles.init}>초기자산</div>
        <div className={styles.initNum}>
          {typeof initNum == "string" ? "-" : `${initNum.toLocaleString()}원`}
        </div>
        <div className={styles.cash}>보유현금</div>
        <div className={styles.cashNum}>{cashNum.toLocaleString()}</div>
        <div className={styles.buystock}>주식매입금</div>
        <div className={styles.buystockNum}>{stocksAmt.toLocaleString()}</div>
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
