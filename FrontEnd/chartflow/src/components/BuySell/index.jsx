import styles from "./BuySell.module.css";
import Order from "../Order";
import data from "../Chart/data";
import { useRef, useState, useContext } from "react";
import TurnContext from "../../context/TurnContext";
import { progressGame, loadGameHistory } from "../../services/apis/chartgame";

function BuySell() {
  const { thisTurn, setThisTurn } = useContext(TurnContext);
  const time = useRef(-1);
  // const [lastData, setLastData] = useState(data[time.current]);
  const [lastData, setLastData] = useState()

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn}</div>
      <div className={styles.all}>/50턴</div>
    </div>
  );
  
  

  const handleBuy = () => {
    progressGame()
      .then((res) => {
        loadGameHistory()
          .then((res) => {
            setThisTurn(res.turn);
          })
          .catch((err) => {
            console.err(err);
          })
      })
      .catch((err) => {
        console.err(err);
      })
  };

  const handleSell = () => {
    progressGame()
      .then((res) => {
        loadGameHistory()
          .then((res) => {
            setThisTurn(res.turn);
          })
          .catch((err) => {
            console.err(err);
          })
      })
      .catch((err) => {
        console.err(err);
      })
  };

  const handleSkip = () => {
    progressGame()
      .then((res) => {
        loadGameHistory()
          .then((res) => {
            setThisTurn(res.turn);
          })
          .catch((err) => {
            console.err(err);
          })
      })
      .catch((err) => {
        console.err(err);
      })
  };

  return (
    <>
      <div className={styles.container1}>
        <div className={styles.turn}>
          <Turn />
        </div>
        <div className={styles.order}>
          <Order />
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
