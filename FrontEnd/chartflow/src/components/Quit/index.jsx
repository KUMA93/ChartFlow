import styles from "./Quit.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext } from "react";
import GameContext from "../../context/GameContext";
import { progressGame } from "../../services/apis/chartgame.js";
import closeBtn from "./../../assets/images/icons8-x-50.png";

function Quit({ handleClose }) {
  const { handleMainNavigate } = useCustomNavigate();
  const { gameId, setIsSaved } = useContext(GameContext);

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

  const gotoSave = () => {
    const storedIsSaved = localStorage.setItem("isSaved", true);
    setIsSaved(storedIsSaved);
    handleMainNavigate();
  };

  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        <img
          src={closeBtn}
          alt="closeBtn"
          className={styles.closeBtn}
          onClick={handleClose}
        />
        <div className={styles.title}>게임을 종료할까요?</div>
        <div className={styles.buttons}>
          <button className={styles.goBack} onClick={gotoMain}>
            그냥 종료
          </button>
          <button className={styles.goSave} onClick={gotoSave}>
            저장하고 종료
          </button>
        </div>
      </div>
    </div>
  );
}
export default Quit;
