import Header from "../../components/Header";
import styles from "./History.module.css"
import { useState } from "react";

function History() {
  const [selectedOption, setSelectedOption] = useState(0);

  const handleLeftClick = () => {
    setSelectedOption(0);
  }

  const handleMiddleClick = () => {
    setSelectedOption(1);
  }

  const handleRightClick = () => {
    setSelectedOption(2);
  } 

  return (
    <>
      <Header />
      <div className={styles.con1}>
        <div className={styles.head1} onClick={handleLeftClick}>랭킹</div>
        <div className={styles.head1} onClick={handleMiddleClick}>게임 기록</div>
        <div className={styles.head1} onClick={handleRightClick}>칭호</div>
      </div>
    {selectedOption === 0 ?
      <div className={styles.back}>
        <div>
          <div>전체 순위</div>
          <div className={styles.con1}>
            <div>
              <div className={styles.rank1}>1위</div>
              <div className={styles.rank1}>2위</div>
            </div>
            <div>
            <div className={styles.rank2}>3위</div>
            <div className={styles.rank2}>4위</div>
            <div className={styles.rank2}>5위</div>
            </div>
          </div>
          <div>이번주 급상승한 유저</div>
          <div className={styles.con1}>
            1
          </div>

        </div>
      </div>:
    selectedOption === 1 ?
      <div className={styles.back}>
        <div>2</div>
      </div>:
      <div className={styles.back}>
        <div className={styles.con1}>
          <div>
            <div className={styles.titleFont}>내가 보유한 칭호</div>
            <div className={styles.nick1}>한강물</div>
            <div className={styles.nick1}>야수의 심장</div>
            <div className={styles.nick1}>칭호3</div>
            <div className={styles.nick1}>칭호4</div>
            <div className={styles.nick1}>칭호5</div>
          </div>
          <div>
            <div className={styles.titleFont}>획득을 기다리는 칭호</div>
            <div className={styles.nick2}>한강물</div>
            <div className={styles.nick2}>야수의 심장</div>
            <div className={styles.nick2}>칭호3</div>
            <div className={styles.nick2}>칭호4</div>
            <div className={styles.nick2}>칭호5</div>
          </div>
        </div>
      </div>
    }
    
    </>
  );
}

export default History;
