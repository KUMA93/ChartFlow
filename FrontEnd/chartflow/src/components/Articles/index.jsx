import styles from "./Articles.module.css";
import thumbsup from "../../assets/images/thumbsup.png";
import chatting from "../../assets/images/chatting.png";
import eye from "../../assets/images/eye.png";

function Articles() {
  const Line = () => <div className={styles.line}></div>;

  return (
    <>
      <div className={styles.bg}>
        <div className={styles.list}>
          <div className={styles.article}>
            <div className={styles.tag}>[자유일상]</div>
            <div className={styles.title}>
              평일에는 직장인, 주말에는 창업가로!
            </div>
            <div className={styles.date}>1일 전</div>
            <div className={styles.iconGroup}>
              <img src={eye} alt="" className={styles.eyeImg} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={thumbsup} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={chatting} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
            </div>
          </div>
          <Line />
          <div className={styles.article}>
            <div className={styles.tag}>[자유일상]</div>
            <div className={styles.title}>
              평일에는 직장인, 주말에는 창업가로!
            </div>
            <div className={styles.date}>1일 전</div>
            <div className={styles.iconGroup}>
              <img src={eye} alt="" className={styles.eyeImg} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={thumbsup} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={chatting} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
            </div>
          </div>
          <Line />
          <div className={styles.article}>
            <div className={styles.tag}>[자유일상]</div>
            <div className={styles.title}>
              평일에는 직장인, 주말에는 창업가로!
            </div>
            <div className={styles.date}>1일 전</div>
            <div className={styles.iconGroup}>
              <img src={eye} alt="" className={styles.eyeImg} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={thumbsup} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
              <div className={styles.dot}>⋅</div>
              <img src={chatting} alt="" className={styles.Img} />
              <div className={styles.num}>10</div>
            </div>
          </div>
          <Line />
        </div>
      </div>
      <div className={styles.pagination}>pagination</div>
    </>
  );
}

export default Articles;
