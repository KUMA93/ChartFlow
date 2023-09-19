import styles from "./Quit.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext, useState } from "react";
import TurnContext from "../../context/TurnContext";

function Quit({ handleClose }) {
  const { handleMainNavigate, handleRecordNavigate } = useCustomNavigate();
  const { thisTurn } = useContext(TurnContext);
  const [goSave, setGoSave] = useState(false);

  function handleSave() {
    localStorage.setItem("thisTurn", thisTurn.toString());
    setGoSave(true);
  }
  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        {goSave ? (
          <>
            <div className={styles.title}>게임이 종료되었습니다.</div>
            <div className={styles.buttons}>
              <button className={styles.goBack} onClick={handleMainNavigate}>
                메인으로 가기
              </button>
              <button className={styles.goSave} onClick={handleRecordNavigate}>
                기록 보기
              </button>
            </div>
          </>
        ) : (
          <>
            <div className={styles.title}>게임을 종료할까요?</div>
            <div className={styles.buttons}>
              <button className={styles.goBack} onClick={handleClose}>
                돌아가기
              </button>
              <button className={styles.goSave} onClick={handleSave}>
                저장하고 종료
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
export default Quit;
