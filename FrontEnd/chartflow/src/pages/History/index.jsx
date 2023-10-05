import Header from "../../components/Header";
import styles from "./History.module.css";
import { useState } from "react";
import rank1 from "../../assets/images/rank1.png";
import rank2 from "../../assets/images/rank2.png";
import s from "./Record.module.css"
import GameRecord from "../../components/GameRecord";
import Acquire from "../../components/Acquire";

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

  const [isModalOpen, setIsModalOpen] = useState(false)
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const [isTitleOpen, setIsTitleOpen] = useState(false)
  const openTitle = () => setIsTitleOpen(true);
  const closeTitle = () => setIsTitleOpen(false);

  return (
    <>
      <Header />
      <div className={styles.con1}>
        <button className={styles.head1} onClick={handleLeftClick}>랭킹</button>
        <button className={styles.head1} onClick={handleMiddleClick}>게임 기록</button>
        <button className={styles.head1} onClick={handleRightClick}>칭호</button>
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
                  <div className={styles.rankUser}>최규헌</div>
                  <div className={styles.price1}>5억 7천만원</div>
                </div>
                <img src={rank1} alt="" className={styles.imgSize}/>
              </div>
              <div className={styles.rank1}>
                <div className={styles.rank1Font}>2</div>
                <div>
                  <div className={styles.rankUser}>최우석</div>
                  <div className={styles.price1}>5억 3천만원</div>
                </div>
                <img src={rank2} alt="" className={styles.imgSize}/>
              </div>
            </div>
            <div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>3</div>
              <div className={styles.user2}>손석구</div>
              <div className={styles.price3}>5억 1천 3백만원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>4</div>
              <div className={styles.user2}>이제훈</div>
              <div className={styles.price3}>4억 9천 50만원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>5</div>
              <div className={styles.user2}>카리나</div>
              <div className={styles.price3}>4억 8천 3만원</div>
            </div>
            </div>
          </div>
          <div className={styles.mainFont}>이번주 급상승한 유저</div>
          <div className={styles.con2}>
            <div className={styles.gContainer}>
              <div className={styles.gItem}>
                <div className={styles.userName}>이연주</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 9천만원</div>
                  <div className={styles.upFont}>51%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>류나연</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 4천만원</div>
                  <div className={styles.upFont}>47%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>김싸피</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 1천만원</div>
                  <div className={styles.upFont}>40%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>김태현</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 9천만원</div>
                  <div className={styles.upFont}>33%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>김현식</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>3억 1천만원</div>
                  <div className={styles.upFont}>12%</div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>:
    selectedOption === 1 ?
      <div>
        <div className={s.back}>
          <div className={s.gridCon}>
            <div className={s.item}>
              <div className={s.tFont}>내 잔고</div>
              <div className={s.div1}>
                <div className={s.money}>1억 3천만원</div>
              </div>
             
            </div>
            <div className={s.item}>
              <div className={s.tFont}>남은 코인</div>
              <div className={s.div1}>
                <div className={s.coin}>5/20개</div>
                <div className={s.exFont}>다음 코인까지 3분 30초 남았어요.</div>
              </div>
            </div>
            <div className={s.item}>
              <div className={s.tFont}>내 전적</div>
              <div className={s.div1}>
                <div className={s.coin}>10승 10패</div>
                <button className={s.btn} onClick={openModal}>지난 게임 보기</button>
                <GameRecord isOpen={isModalOpen} closeModal={closeModal} />
              </div>
            </div>
            <div className={s.item}>
              <div className={s.tFont}>내 승률</div>
              <div className={s.div1}>
                <div className={s.coin}>50%</div>
              </div>
            </div>
            <div className={s.item}>
              <div className={s.tFont}>평균 수익률</div>
              <div className={s.div1}>
                <div className={s.coin}>12.7%</div>
              </div>
            </div>
            <div className={s.item}>
              <div className={s.tFont}>내 순위</div>
              <div className={s.div1}>
                <div className={s.coin}>57위</div>
                <div className={s.exFont}>지난 주 보다 3위 상승했어요.</div>
              </div>
            </div>
          </div>
        </div>
      </div>:
      <div className={styles.back}>
        <div className={styles.con2}>
          <div>
            <div className={styles.titleFont}>내가 보유한 칭호</div>
            <button className={styles.nick1}>한강물</button>
            <button className={styles.nick1}>야수의 심장</button>
          </div>
          <div>
            <div className={styles.titleFont}>획득을 기다리는 칭호</div>
            <button className={styles.nick2} onClick={openTitle}>한강물</button>
            <Acquire isOpen={isTitleOpen} closeModal={closeTitle}/>
            <button className={styles.nick2}>야수의 심장</button>
            <button className={styles.nick2}>칭호3</button>
            <button className={styles.nick2}>칭호4</button>
            <button className={styles.nick2}>칭호5</button>
          </div>
        </div>
      </div>
    }
    </>
  );
}

export default History;
