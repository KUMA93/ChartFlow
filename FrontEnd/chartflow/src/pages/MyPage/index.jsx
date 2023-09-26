import Header from "../../components/Header";
import styles from "./MyPage.module.css"
import pencil from "../../assets/images/pencil.png"
import { useState } from 'react'

function MyPage() {
  const [left, setLeft] = useState(true)
  const handleLeftClick = () => {
    setLeft(true);
  }

  const handleRightClick = () => {
    setLeft(false);
  }   

  return (
    <>
    <Header />
      <div className={styles.box1}>
        <div className={styles.tag} onClick={handleLeftClick}>내 정보</div>
        <div className={styles.tag} onClick={handleRightClick}>내 활동</div>
      </div>
    {left? 
      <div className={styles.back}>
        <div className={styles.nickname}>내 칭호</div>
        <div className={styles.container1}>
          <div className={styles.profile}>
            <img src={pencil} alt="" className={styles.pencil} />
          </div>
          <div>
            <div className={styles.inputBox}>
              <div className={styles.inputFont}>이름</div><div className={styles.info}>김싸피</div>  
            </div>
            <div className={styles.inputBox}>
              <div className={styles.inputFont}>Email</div><div className={styles.info}>chartflow@naver.com</div>  
            </div>
            <div className={styles.inputBox}>
              <div className={styles.inputFont}>닉네임</div><div className={styles.info}>닉네임</div>  
            </div>
            {/* <div className={styles.warning}>사용할 수 있는 닉네임 입니다.</div> */}
            <div className={styles.inputBox}>
              <div className={styles.inputFont}>비밀번호</div>
            </div>
            <div className={styles.inputBox}>
              <div className={styles.inputFont}>비밀번호 확인</div>
            </div>
            <div className={styles.container2}>
              <div className={styles.btn1}>완료</div>
              <div className={styles.btn2}>취소</div>
            </div>
          </div>
        </div>
      </div>
    
    :
      <div className={styles.back}>
        <div className={styles.container3}>
          <div>
            <div className={styles.titleFont}>내가 쓴 글</div>
            <div className={styles.line}><div className={styles.font3}>3천만원 날렸어요.</div></div>
            <div className={styles.line}><div className={styles.font3}>배고프다. 저녁 먹고싶다.</div></div>            
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
          </div>
          <div>
            <div className={styles.titleFont}>내가 좋아요한 글</div>
            <div className={styles.line}>
              <div className={styles.font3}>한 달만에 1억 벌기</div>
            </div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
            <div className={styles.line}></div>
          </div>
          
        </div>
      </div>}
    </>
  );
}

export default MyPage;
