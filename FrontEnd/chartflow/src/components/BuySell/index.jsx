import styles from "./BuySell.module.css";
import Order from "../Order";
import TurnContext from "../../context/TurnContext.js";
import { useContext, useEffect } from "react";

function BuySell() {
  const { thisTurn, setThisTurn } = useContext(TurnContext);

  useEffect(() => {
    const storedThisTurn = localStorage.getItem("thisTurn");
    if (storedThisTurn !== null) {
      const parsedThisTurn = parseInt(storedThisTurn, 10);
      setThisTurn(parsedThisTurn);
    }
  }, [setThisTurn]);

  const Turn = () => (
    <div className={styles.texts}>
      <div className={styles.now}>{thisTurn}</div>
      <div className={styles.all}>/50턴</div>
    </div>
  );

  const handleBuy = () => {
    setThisTurn(thisTurn + 1);
    localStorage.setItem("thisTurn", thisTurn.toString());



  };

  const handleSell = () => {
    setThisTurn(thisTurn + 1);
    localStorage.setItem("thisTurn", thisTurn.toString());
  };

  const handleSkip = () => {
    setThisTurn(thisTurn + 1);
    localStorage.setItem("thisTurn", thisTurn.toString());
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
