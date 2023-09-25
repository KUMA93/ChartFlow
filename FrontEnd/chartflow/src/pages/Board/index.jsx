import Header from "../../components/Header";
import HotUpdate from "../../components/HotUpdate";
import Articles from "../../components/Articles";
import styles from "./Board.module.css";
import write from "../../assets/images/write.png";

function Board() {
  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.hotUpdate}>
          <HotUpdate />
        </div>
        <div className={styles.inner1}>
          <div className={styles.tags}>
            <button className={styles.write}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
            <button className={styles.write}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
            <button className={styles.write}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
            <button className={styles.write}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
            <button className={styles.write}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
          </div>
          <button className={styles.write}>
            글쓰기
            <img src={write} alt="" className={styles.writeImg} />
          </button>
        </div>
        <div className={styles.inner2}>
          <div>inner2</div>
        </div>
        <div className={styles.articles}>
          <Articles />
        </div>
      </div>
    </>
  );
}

export default Board;
