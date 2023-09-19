import styles from "./BuySell.module.css";
import Order from "../Order";
import data from "../Chart/data";
import { useRef, useState, useContext } from "react";
import TurnContext from "../../context/TurnContext";

function BuySell() {
  const { thisTurn, setThisTurn } = useContext(TurnContext);
  const time = useRef(-1);
  const [lastData, setLastData] = useState(data[time.current]);

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn}</div>
      <div className={styles.all}>/50턴</div>
    </div>
  );

  const handleBuy = () => {
    setThisTurn(thisTurn + 1);
  };

  const handleSell = () => {
    setThisTurn(thisTurn + 1);
  };

  const handleSkip = () => {
    setThisTurn(thisTurn + 1);
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
