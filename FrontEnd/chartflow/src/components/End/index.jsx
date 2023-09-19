import styles from "./End.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function End() {
  const { handleRecordNavigate } = useCustomNavigate();

  const handleEnd = () => {
    localStorage.setItem("thisTurn", "1");
    handleRecordNavigate();
  };

  return (
    <div>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        <div className={styles.title}>
          최대 일일 턴수인 5턴을 초과하여 게임이 자동종료됩니다.
        </div>
        <button className={styles.goResult} onClick={handleEnd}>
          결과보기
        </button>
      </div>
    </div>
  );
}
export default End;
