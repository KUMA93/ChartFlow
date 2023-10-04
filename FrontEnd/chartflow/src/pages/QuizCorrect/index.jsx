import Header from "../../components/Header";
import styles from "./QuizCorrect.module.css";
import correctImg from "../../assets/images/correct.png";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function QuizCorrect() {
  const { handleQuizNavigate } = useCustomNavigate();

  return (
    <>
      <Header />
      <div className={styles.fullBox}>
        <div className={styles.font1}>정답입니다!</div>
        <div className={styles.font2}>코인을 1개 획득하였습니다.</div>
        <img src={correctImg} alt="" className={styles.imgSize} />
        <div className={styles.nextBtn} onClick={handleQuizNavigate}>
          다음 문제 풀기
        </div>
      </div>
    </>
  );
}

export default QuizCorrect;
