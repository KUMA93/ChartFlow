import Header from "../../components/Header";
import styles from "./Quiz.module.css"

function Quiz() {
  return (
    <>
      <Header />
      <div className={styles.fullBox}>
        <div className={styles.title}>오늘의 퀴즈</div>
        <div className={styles.quizFont}>
        Q1. 주식 시장에서 주가가 급등하거나 급락할 때 주식 매매를 일시 정지하는 제도는?
        </div>
      <div className={styles.ansContainer}>
        <div className={styles.ansBtn}>1</div>
        <div className={styles.ansBtn}>2</div>
        <div className={styles.ansBtn}>3</div>
        <div className={styles.ansBtn}>4</div>
      </div>
    </div>    
    </>
  );
}

export default Quiz;