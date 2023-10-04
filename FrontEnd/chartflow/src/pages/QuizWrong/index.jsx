import Header from "../../components/Header";
import styles from "./QuizCorrect.module.css"
import wrongImg from "../../assets/images/wrong.png"
import useCustomNavigate from "../../hooks/useCustomNavigate";

function Quiz() {

  const { handleQuizNavigate } = useCustomNavigate();

  return (
    <>
      <Header />
      <div className={styles.fullBox}>
        <div className={styles.font1}>
          틀렸습니다.
        </div>
        <img src={wrongImg} alt="" className={styles.imgSize}/>
        <div className={styles.nextBtn} onClick={handleQuizNavigate}>다음 문제 풀기</div>
      </div>
    </>
  );
}

export default Quiz;