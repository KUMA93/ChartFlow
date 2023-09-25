import Header from "../../components/Header";
import styles from "./MyPage.module.css"
import pencil from "../../assets/images/pencil.png"
import line from "../../assets/images/Line.png"


function MyPage() {
  return (
    <>
      <Header />
        <div className={styles.nickname}>한강물</div>
      <div className={styles.box1}>
        <div className={styles.profile}>
          <img src={pencil} alt="" className={styles.img}/>
        </div>
        <div className={styles.box2}>
          <div className={styles.inputBox}>이름</div>
          <div className={styles.inputBox}>이메일</div>
        </div>
      </div>
      <div className={styles.bottomBox}>
        <img src={line} alt="" className={styles.line}/>
      </div>
      <div>
        <div className={styles.btn}>수정</div>
        <div className={styles.btn}>취소</div>
      </div>
    </>
  );
}

export default MyPage;
