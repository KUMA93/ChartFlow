import styles from "./End.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import GameContext from "../../context/GameContext";
import { useContext } from "react";
import { progressGame } from "../../services/apis/chartgame.js";

function End() {
  const { handleRecordNavigate, handleMainNavigate } = useCustomNavigate();
  const { coin, setThisTurn, gameId } = useContext(GameContext);

  const gotoMain = () => {
    localStorage.removeItem("isSaved");
    const requestData = JSON.stringify({
      gameHistoryId: gameId,
      mode: 3,
    });
    progressGame(requestData, {
      headers: { "Content-Type": "application/json" },
    })
      .then(handleMainNavigate())
      .catch((err) => console.log(err));
  };

  const handleRecord = () => {
    localStorage.setItem("thisTurn", "1");
    setThisTurn(1);
    handleRecordNavigate();
  };

  return (
    <>
      <div className={styles.shade}></div>
      <div className={styles.modal}>
        <div className={styles.title}>
          최대 일일 턴수인 50턴을 초과하여 게임이 자동종료됩니다.
        </div>

        {coin <= 0 ? (
          <>
            <button className={styles.goResult2} onClick={handleRecord}>
              기록 보기
            </button>
          </>
        ) : (
          <div className={styles.buttons}>
            <button className={styles.goNew} onClick={gotoMain}>
              메인으로
            </button>
            <button className={styles.goResult} onClick={handleRecord}>
              기록 보기
            </button>
          </div>
        )}
        <div className={styles.coinLeft}>
          <div>남은 코인 수: </div>
          <div className={styles.coinNum}>{coin}</div>
          <div>개</div>
        </div>
      </div>
    </>
  );
}
export default End;
