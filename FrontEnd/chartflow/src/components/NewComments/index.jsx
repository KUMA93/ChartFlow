import styles from "./NewComments.module.css";

function NewComments() {
  return (
    <div className={styles.bg}>
      <div className={styles.innerText}>
        <div className={styles.title}>
          <div className={styles.titleName}>최근 댓글</div>
        </div>
        <div className={styles.line}></div>
        <div className={styles.list}>
          {[1, 2, 3, 4, 5, 6, 7].map((item, index) => (
            <div key={index} className={styles.article}>
              <div className={styles.title2}>
                평일에는 직장인, 주말에는 창업가로!
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default NewComments;
