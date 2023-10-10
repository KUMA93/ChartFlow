import Header from "../../components/Header";
import styles from "./JoinComplete.module.css";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import checkMark from "../../assets/images/checkmark.png";

function JoinComplete() {
  const { handleMainNavigate } = useCustomNavigate();

  return (
    <>
      <Header />
      <div className={styles.center}>
        <img src={checkMark} alt="" className={styles.margin} />
        <div className={styles.font}>회원가입이 완료되었습니다.</div>
        <div className={styles.innerText}>지금 바로 즐기면서 배우는</div>
        <div className={styles.innerText}>차트게임을 시작해보세요!</div>
        <div>
          <button onClick={handleMainNavigate} className={styles.mainBtn}>
            메인으로
          </button>
        </div>
      </div>
    </>
  );
}

export default JoinComplete;
