import styles from "./BuySell.module.css";
import Order from "../Order";
import { useState, useContext } from "react";
import GameContext from "../../context/GameContext";
import { progressGame, loadGameHistory } from "../../services/apis/chartgame";

function BuySell() {
  const originStocks = 100;
  const [orderedStocks, setOrderedStocks] = useState(originStocks);
  const { gameId, setGameId, thisTurn, setThisTurn } = useContext(GameContext);

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn}</div>
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
        loadGameHistory().catch((err) => {
          console.log(err);
        });
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
        loadGameHistory().catch((err) => {
          console.log(err);
        });
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
        loadGameHistory().catch((err) => {
          console.log(err);
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <div className={styles.container1}>
        <div className={styles.turn}>
          <Turn />
        </div>
        <div className={styles.order}>
          <Order
            originStocks={originStocks}
            orderedStocks={orderedStocks}
            setOrderedStocks={setOrderedStocks}
          />
        </div>
        <button className={styles.buybtn} onClick={handleBuy}>
          매수
        </button>
        <button className={styles.sellbtn} onClick={handleSell}>
          매도
        </button>
        <button className={styles.skip} onClick={handleSkip}>
          건너뛰기
        </button>
      </div>
    </>
  );
}

export default BuySell;
