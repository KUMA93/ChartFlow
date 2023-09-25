import Header from "../../components/Header";
import styles from "./QuizCorrect.module.css"
import correctImg from "../../assets/images/correct.png"

function Quiz() {
  return (
    <>
      <Header />
      <div className={styles.fullBox}>
        <div className={styles.font1}>
          정답입니다!
        </div>
        <div className={styles.font2}>코인을 1개 획득하였습니다.</div>
        <img src={correctImg} alt="" className={styles.imgSize}/>
        <div className={styles.nextBtn}>다음 문제 풀기</div>
      </div>
    </>
  );
}

export default Quiz;