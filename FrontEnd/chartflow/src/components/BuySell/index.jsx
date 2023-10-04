import styles from "./BuySell.module.css";
import Order from "../Order";
import { useState, useContext, useEffect } from "react";
import GameContext from "../../context/GameContext";
import { loadGameHistory, progressGame } from "../../services/apis/chartgame";

function BuySell() {
  const {
    gameId,
    cashNum,
    curPriceNum,
    stocksAmt,
    thisTurn,
    setThisTurn,
    flag,
    setFlag,
  } = useContext(GameContext);
  const originBuyStocks = Math.floor(cashNum / curPriceNum);
  const originSellStocks = stocksAmt ? Math.floor(stocksAmt / curPriceNum) : 0;
  const originStocks = Math.max(originBuyStocks, originSellStocks);
  const [orderedStocks, setOrderedStocks] = useState(originStocks);

  useEffect(() => {
    loadGameHistory().then((res) => {
      setThisTurn(res.gameHistory.turn);
    });
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [flag]);

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn === 51 ? "-" : thisTurn}</div>
      <div className={styles.all}>/50턴</div>
    </div>
  );

  const createRequestData = (mode) => {
    return {
      gameHistoryId: gameId,
      mode: mode,
      quantity: orderedStocks,
    };
  };

  const handleBuy = () => {
    const requestData = createRequestData(0);
    progressGame(requestData, {
      headers: { "Content-Type": "application/json" },
    })
      .then((res) => {
        setFlag(!flag);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleSell = () => {
    const requestData = createRequestData(1);
    progressGame(requestData, {
      headers: { "Content-Type": "application/json" },
    })
      .then((res) => {
        setFlag(!flag);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleSkip = () => {
    const requestData = createRequestData(2);
    progressGame(requestData, {
      headers: { "Content-Type": "application/json" },
    })
      .then((res) => {
        setFlag(!flag);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const [isBuyBtnDisabled, setIsBuyBtnDisabled] = useState(false);
  const [isSellBtnDisabled, setIsSellBtnDisabled] = useState(false);

  useEffect(() => {
    setIsBuyBtnDisabled(
      orderedStocks > originBuyStocks || originBuyStocks <= 0
    );
    // setIsBuyBtnDisabled(originBuyStocks <= 0);
    setIsSellBtnDisabled(
      orderedStocks > originSellStocks || originSellStocks <= 0
    );
    // setIsSellBtnDisabled(originSellStocks <= 0);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [orderedStocks, handleSell, handleBuy]);

  let buyDisabled;
  let sellDisabled;
  if (orderedStocks > originBuyStocks || originBuyStocks <= 0) {
    buyDisabled = styles.disabled;
  } else if (orderedStocks > originSellStocks || originSellStocks <= 0) {
    sellDisabled = styles.disabled;
  }

  return (
    <>
      <div className={styles.container1}>
        <div className={styles.turn}>
          <Turn />
        </div>
        <div className={styles.order}>
          <Order
            originBuyStocks={originBuyStocks}
            originSellStocks={originSellStocks}
            originStocks={originStocks}
            orderedStocks={orderedStocks}
            setOrderedStocks={setOrderedStocks}
          />
        </div>
        <button
          className={`${styles.buybtn} ${buyDisabled}`}
          onClick={handleBuy}
          disabled={isBuyBtnDisabled}
        >
          매수
        </button>
        <button
          className={`${styles.sellbtn} ${sellDisabled}`}
          onClick={handleSell}
          disabled={isSellBtnDisabled}
        >
          매도
        </button>
        <button className={styles.skip} onClick={handleSkip}>
          다음
        </button>
      </div>
    </>
  );
}

export default BuySell;
