import s from "./GameRecord.module.css";
import arrow from "../../assets/images/arrow.png"


function GameRecord({ isOpen, closeModal }) {

  return (
    <div style={{ display: isOpen ? "block": "none" }}>
      <div className={s.shade}>
        <div className={s.modalRecord}>
          <div>
            <div className={s.dateFont}>2023년 10월 5일</div>
            <div className={s.nemo}>
              <div className={s.gFont}>삼성전자</div>
              <div className={s.con}>
                <div>1억 1천만원</div>
                <img src={arrow} alt="" className={s.arrow}/>
                <div>1억 1천 3백만원</div>
              </div>
              <div className={s.gFont}>+ 3백만원</div>
            </div>
            <div className={s.nemo}>
              <div className={s.gFont}>삼성전자</div>
              <div className={s.con}>
                <div>1억 1천만원</div>
                <img src={arrow} alt="" className={s.arrow}/>
                <div>1억 1천 3백만원</div>
              </div>
              <div className={s.gFont}>+ 3백만원</div>
            </div>
            <div className={s.nemo}>
              <div className={s.gFont}>삼성전자</div>
              <div className={s.con}>
                <div>1억 1천만원</div>
                <img src={arrow} alt="" className={s.arrow}/>
                <div>1억 1천 3백만원</div>
              </div>
              <div className={s.gFont}>+ 3백만원</div>
            </div>
          </div>
          <button onClick={closeModal} className={s.closeBtn}>닫기</button>
        </div>
      </div>
    </div>
  );
}

export default GameRecord;