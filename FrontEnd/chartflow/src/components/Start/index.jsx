import styles from "./Start.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext } from "react";
import TurnContext from "../../context/TurnContext";
import CoinContext from "../../context/CoinContext";

function Start({ setModalStartShow, handleStartClose }) {
  const { handleGameNavigate, handleQuizNavigate, handleMainNavigate } =
    useCustomNavigate();
  const { setThisTurn } = useContext(TurnContext);
  const { coinNum } = useContext(CoinContext);

  const handleStartGame = () => {
    setThisTurn(1);
    setModalStartShow(false);
    handleGameNavigate();
  };

  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        {coinNum <= 0 ? (
          <>
            <div className={styles.title}>
              <div>보유 코인이 부족합니다.</div>{" "}
              <div>퀴즈 풀고 코인을 얻어볼까요?</div>
            </div>
            <div className={styles.buttons}>
              <button className={styles.goOn} onClick={handleMainNavigate}>
                돌아가기
              </button>
              <button className={styles.goNew} onClick={handleQuizNavigate}>
                퀴즈 풀러가기
              </button>
            </div>
          </>
        ) : (
          <>
            {/* <div className={styles.title}>게임을 시작할까요?</div>
            <div className={styles.buttons}>
              <button className={styles.goOn} onClick={handleStartClose}>
                이어서 시작
              </button>
              <button className={styles.goNew} onClick={handleStartGame}>
                새로 시작
              </button>
            </div> */}
          </>
        )}
      </div>
    </div>
  );
}
export default Start;
