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
                  <div className={styles.rankUser}>라이온킹</div>
                  <div className={styles.price1}>5억 7천만원</div>
                </div>
                <img src={rank1} alt="" className={styles.imgSize}/>
              </div>
              <div className={styles.rank1}>
                <div className={styles.rank1Font}>2</div>
                <div>
                  <div className={styles.rankUser}>새로운</div>
                  <div className={styles.price1}>5억 3천만원</div>
                </div>
                <img src={rank2} alt="" className={styles.imgSize}/>
              </div>
            </div>
            <div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>3</div>
              <div className={styles.user2}>일빠</div>
              <div className={styles.price3}>5억 1천 3백만원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>4</div>
              <div className={styles.user2}>최키키</div>
              <div className={styles.price3}>4억 9천 50만원</div>
            </div>
            <div className={styles.rank2}>
              <div className={styles.rank2Font}>5</div>
              <div className={styles.user2}>킹현식</div>
              <div className={styles.price3}>4억 8천 3만원</div>
            </div>
            </div>
          </div>
          <div className={styles.mainFont}>이번주 급상승한 유저</div>
          <div className={styles.con2}>
            <div className={styles.gContainer}>
              <div className={styles.gItem}>
                <div className={styles.userName}>오이싫어</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 9천만원</div>
                  <div className={styles.upFont}>51%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>구루구루</div>
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
                <div className={styles.userName}>콩이</div>
                <div className={styles.div1}>
                  <div className={styles.perFont}>2억 9천만원</div>
                  <div className={styles.upFont}>33%</div>
                </div>
              </div>
              <div className={styles.gItem}>
                <div className={styles.userName}>메롱</div>
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
            <button className={styles.nick1}>하남자</button>
            <button className={styles.nick1}>야수의 심장</button>
          </div>
          <div>
            <div className={styles.titleFont}>획득을 기다리는 칭호</div>
            <button className={styles.nick2} onClick={openTitle}>숟가락살인마</button>
            <Acquire isOpen={isTitleOpen} closeModal={closeTitle} emblem={'5% 이내 수익률 20회 이상'}/>
            <button className={styles.nick2}>연금술사</button>
            <button className={styles.nick2}>키보드워리어</button>
            <button className={styles.nick2}>스톤헤드</button>
            <button className={styles.nick2}>각도기</button>
            <button className={styles.nick2}>머니메이커</button>
          </div>
        </div>
      </div>
    }
    </>
  );
}

export default History;
