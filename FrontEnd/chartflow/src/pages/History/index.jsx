import Header from "../../components/Header";
import styles from "./History.module.css"
import { useState } from "react";
import rank1 from "../../assets/images/rank1.png"
import rank2 from "../../assets/images/rank2.png"

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
          <div className={styles.mainFont}>전체 순위</div>
          <div className={styles.con2}>
            <div>
              <div className={styles.rank1}>
                <div className={styles.rank1Font}>1</div>
                <div>
                  <div className={styles.rankUser}>1등 유저</div>
                  <div className={styles.price1}>50,000,000원</div>
                </div>
                <img src={rank1} alt="" className={styles.imgSize}/>
              </div>
              <div className={styles.rank1}>
                <div className={styles.rank1Font}>2</div>
                <div>
                  <div className={styles.rankUser}>2등 유저</div>
                  <div className={styles.price1}>40,000,000원</div>
                </div>
                <img src={rank2} alt="" className={styles.imgSize}/>
              </div>
            </div>
            <div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>3</div>
              <div className={styles.user2}>유저이름</div>
              <div className={styles.price3}>5,000,000 원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>4</div>
              <div className={styles.user2}>유저이름</div>
              <div className={styles.price3}>5,000,000 원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>5</div>
              <div className={styles.user2}>유저이름</div>
              <div className={styles.price3}>5,000,000 원</div>
            </div>
            </div>
          </div>
          <div className={styles.mainFont}>이번주 급상승한 유저</div>
          <div className={styles.con2}>
            <div className={styles.userBox}>
              <div className={styles.userName}>유저1</div>
              <div className={styles.price2}>8900000000 원</div>
              <div className={styles.perFont}>56%</div>
            </div>
            <div className={styles.userBox}>
              <div className={styles.userName}>유저1</div>
              <div className={styles.price2}>8900000000 원</div>
              <div className={styles.perFont}>56%</div>
            </div>
            <div className={styles.userBox}>
              <div className={styles.userName}>유저1</div>
              <div className={styles.price2}>8900000000 원</div>
              <div className={styles.perFont}>56%</div>
            </div>
            <div className={styles.userBox}>
              <div className={styles.userName}>유저1</div>
              <div className={styles.price2}>8900000000 원</div>
              <div className={styles.perFont}>56%</div>
            </div>
            <div className={styles.userBox}>
              <div className={styles.userName}>유저1</div>
              <div className={styles.price2}>8900000000 원</div>
              <div className={styles.perFont}>56%</div>
            </div>
            
          </div>

        </div>
      </div>:
    selectedOption === 1 ?
      <div className={styles.back}>    
          <div className={styles.con3}>
          <div className={styles.dash}>
            <div className={styles.hisFont}>나의 잔고</div>
            <div className={styles.perFont}>1,000,000,000 won</div>
          </div>
          <div className={styles.dash}>
            <div className={styles.hisFont}>남은 코인 수</div>
            <div className={styles.perFont}>11 / 20</div>
            <div className={styles.price2}>다음 코인까지 남은 시간 04:58</div>
          </div>
        </div>
        <div className={styles.con3}>
          <div className={styles.dash}>
            <div className={styles.hisFont}>나의 승률</div>
            <div className={styles.gameFont}>전체 100 게임 중 50 승 50 패</div>
            <div className={styles.perFont}>50 %</div>
          </div>
          <div className={styles.dash}>
            <div className={styles.hisFont}>나의 전적</div>
            <div>
              승 패 승 승 승 패
            </div>
          </div>
        </div>
        <div className={styles.con3}>
          <div className={styles.dash}>
            <div className={styles.hisFont}>나의 순위</div>
            <div>67위</div>
            <div>저번 주보다 10위 상승했어요.</div>
          </div>
          <div className={styles.dash}>
            <div className={styles.hisFont}>차트플로우 전체 순위</div>
            <div>유저 랭킹 목록</div>
          </div>
        </div>
      </div>:
      <div className={styles.back}>
        <div className={styles.con2}>
          <div>
            <div className={styles.titleFont}>내가 보유한 칭호</div>
            <div className={styles.nick1}>한강물</div>
            <div className={styles.nick1}>야수의 심장</div>
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
