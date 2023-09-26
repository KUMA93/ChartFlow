import styles from "./End.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function End() {
  const { handleRecordNavigate } = useCustomNavigate();

  const handleEnd = () => {
    localStorage.setItem("thisTurn", "1");
    window.location.reload("/game");
  };

  const handleRecord = () => {
    localStorage.setItem("thisTurn", "1");
    handleRecordNavigate();
  };

  return (
    <>
      <div className={styles.shade}></div>
      <div className={styles.modalLogin}>
        {/* <div className={styles.title}>
          최대 일일 턴수인 5턴을 초과하여 게임이 자동종료됩니다.
        </div>

        {coinNum <= 0 ? (
          <>
            <button className={styles.goResult2} onClick={handleRecordNavigate}>
              기록 보기
            </button>
          </>
        ) : (
          <div className={styles.buttons}>
            <button className={styles.goNew} onClick={handleEnd}>
              새 게임 시작
            </button>
            <button className={styles.goResult} onClick={handleRecord}>
              기록 보기
            </button>
          </div>
        )}
        <div className={styles.coinLeft}>
          <div>남은 코인 수: </div>
          <div className={styles.coinNum}>{coinNum}</div>
          <div>개</div>
        </div> */}
      </div>
    </>
  );
}
export default End;
