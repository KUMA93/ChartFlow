import styles from "./BuySell.module.css";
import Order from "../Order";
import data from '../Chart/data'
import TurnContext from "../../context/TurnContext.js";
import { useContext, useEffect, useRef, useState } from "react";

function BuySell() {
  const { thisTurn, setThisTurn } = useContext(TurnContext);
  const time = useRef(-1)
  const [ lastData, setLastData] = useState(data[time.current])

  useEffect(() => {
    const storedThisTurn = localStorage.getItem("thisTurn");
    if (storedThisTurn !== null) {
      const parsedThisTurn = parseInt(storedThisTurn, 10);
      setThisTurn(parsedThisTurn);

    }
  }, [setThisTurn]);

  useEffect(() => {
    time.current = time.current+ 1
    console.log(data[time.current].Close)
  }, [thisTurn])

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn}</div>
      <div className={styles.all}>/50턴</div>
    </div>
  );

  const handleBuy = () => {
    const newThisTurn = thisTurn + 1;
    setThisTurn(newThisTurn);
    localStorage.setItem("thisTurn", newThisTurn.toString());
  };

  const handleSell = () => {
    const newThisTurn = thisTurn + 1;
    setThisTurn(newThisTurn);
    localStorage.setItem("thisTurn", newThisTurn.toString());
  };

  const handleSkip = () => {
    const newThisTurn = thisTurn + 1;
    setThisTurn(newThisTurn);
    localStorage.setItem("thisTurn", newThisTurn.toString());
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
