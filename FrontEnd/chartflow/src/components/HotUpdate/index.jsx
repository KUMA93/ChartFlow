import styles from "./HotUpdate.module.css";
import fire from "../../assets/images/fire.png";

function HotUpdate() {
  return (
    <div className={styles.bg}>
      <div className={styles.innerText}>
        <div className={styles.title}>
          <img src={fire} alt="" className={styles.fireImg} />
          <div className={styles.titleName}>실시간 인기글</div>
        </div>
        <div className={styles.line}></div>
        <div className={styles.list}>
          <div className={styles.article}>
          <div className={styles.title2}>
            평일에는 직장인, 주말에는 창업가로!
          </div>
          </div>
          <div className={styles.article}>
          <div className={styles.title2}>
            주식으로 2억 번 썰 푼다.
          </div>
          </div>
          <div className={styles.article}>
          <div className={styles.title2}>
            기다리면 오르나요?
          </div>
          </div>
          <div className={styles.article}>
          <div className={styles.title2}>
            우울하다.. 손절..
          </div>
          </div>
        </div>
        </div>
      </div>
  );
}

export default HotUpdate;
