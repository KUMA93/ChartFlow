import Header from "../../components/Header";
import styles from "./MyPage.module.css"

function MyPage() {
  return (
    <>
      <Header />
        <div className={styles.nickname}>한강물</div>
      <div>
          <div className={styles.profile}></div>
          <div className={styles.inputBox}>이름</div>
          <div className={styles.inputBox}>이메일</div>
      </div>
        <div className={styles.bottomBox}></div>
        <div className={styles.btn}>수정</div>
    </>
  );
}

export default MyPage;
