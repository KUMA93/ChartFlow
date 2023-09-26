import styles from "./Start.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext, useEffect } from "react";
import GameContext from "../../context/GameContext";
import { loadGameHistory } from "../../services/apis/chartgame";

function Start({ setModalStartShow, handleStartClose }) {
  const { handleMainNavigate } = useCustomNavigate();
  const { gameId, setGameId } = useContext(GameContext);

  useEffect(() => {
    loadGameHistory().then((res) => {
      setGameId(res.gameHistory.gameHistoryId);
    });
  }, [setGameId]);

  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        {gameId !== 0 ? (
          <>
            <div className={styles.title}>
              <div>끝내지 않은 게임이 있습니다</div>
              <div>게임을 이어할까요?</div>
            </div>
            <div className={styles.buttons}>
              <button className={styles.goOn} onClick={handleMainNavigate}>
                돌아가기
              </button>
              <button className={styles.goNew} onClick={handleStartClose}>
                이어서 하기
              </button>
            </div>
          </>
        ) : (
          <>
            <div className={styles.title}>게임을 시작할까요?</div>
            <div className={styles.buttons}>
              <button className={styles.goOn} onClick={handleMainNavigate}>
                돌아가기
              </button>
              <button className={styles.goNew} onClick={handleStartClose}>
                새로 시작
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
export default Start;
