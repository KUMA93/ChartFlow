import styles from "./Start.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext } from "react";
import TurnContext from "../../context/TurnContext";

function Start({ setModalStartShow, handleClose }) {
  const { handleGameNavigate } = useCustomNavigate();
  const { setThisTurn } = useContext(TurnContext);

  const handleStartGame = () => {
    setThisTurn(1);
    setModalStartShow(false);
    handleGameNavigate();
  };

  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        <div className={styles.title}>게임을 시작할까요?</div>
        <div className={styles.buttons}>
          <button className={styles.goOn} onClick={handleClose}>
            이어서 시작
          </button>
          <button className={styles.goNew} onClick={handleStartGame}>
            새로 시작
          </button>
        </div>
      </div>
    </div>
  );
}
export default Start;
