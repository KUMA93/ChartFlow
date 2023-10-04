import Header from "../../components/Header";
import styles from "./Quiz.module.css"
import lightimg from "../../assets/images/light.png"
import { useEffect } from "react";
import { getQuiz, sendAnswer } from "../../services/apis/quiz";
import { useState } from "react";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function Quiz() {
  const [quiz, setQuiz] = useState("");
  const [userAnswer, setUserAnswer] = useState(0);

  const { handleQuizCorrectNavigate, handleQuizWrongNavigate } = useCustomNavigate();
  
  useEffect(() => {
    getQuiz()
      .then((res) => {
        // console.log(res);
        setQuiz(res.quiz);
        // console.log(quiz);
      })
      .catch((err) => {
        console.error(err);
      })
  }, []);

  const handleQuiz = (answer) => {
    let requestQuizDto = {
      "quizId" : quiz.quizId,
      "choice" : answer
    };

    sendAnswer(requestQuizDto)
      .then((res) => {
        console.log(res);
        if (res.result === true) {
          handleQuizCorrectNavigate();
        }else {
          handleQuizWrongNavigate();
        }
      })
      .catch((err) => {
        console.error(err);
      })
  };

  return (
    <>
      <Header />
      <div className={styles.fullBox}>
        <div className={styles.title}>오늘의 퀴즈
        <img src={lightimg} alt="" className={styles.imgSize}/>
        </div>
        <div className={styles.quizFont}>
        {quiz.question}
        </div>
      <div className={styles.ansContainer}>
        <div className={styles.ansBtn1} onClick={() => handleQuiz(quiz.choices[0])}>1. {quiz.choices && quiz.choices[0]}</div>
        <div className={styles.ansBtn2} onClick={() => handleQuiz(quiz.choices[1])}>2. {quiz.choices && quiz.choices[1]}</div>
        <div className={styles.ansBtn3} onClick={() => handleQuiz(quiz.choices[2])}>3. {quiz.choices && quiz.choices[2]}</div>
        <div className={styles.ansBtn4} onClick={() => handleQuiz(quiz.choices[3])}>4. {quiz.choices && quiz.choices[3]}</div>
      </div>
    </div>    
    </>
  );
}

export default Quiz;
