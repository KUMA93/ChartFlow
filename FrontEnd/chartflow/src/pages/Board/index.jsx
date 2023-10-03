import Header from "../../components/Header";
import HotUpdate from "../../components/HotUpdate";
import Articles from "../../components/Articles";
import styles from "./Board.module.css";
import write from "../../assets/images/write.png";
import align from "../../assets/images/align.png";
import search from "../../assets/images/search.png";
import { useInput } from "../../hooks/useInput";

function Board() {
  const handleSubmit = (event) => {
    event.preventDefault();
  };
  const [inputValue, handleChangeValue] = useInput("", handleSubmit);
  const handleAlign = (event) => {
    event.preventDefault();
  };

  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.hotUpdate}>
          <HotUpdate />
        </div>
        <div className={styles.inner1}>
          <div className={styles.tags}>
            <button className={styles.tag}>전체</button>
            <button className={styles.tag}>자유일상</button>
            <button className={styles.tag}>유머/좋은글</button>
            <button className={styles.tag}>여행</button>
            <button className={styles.tag}>뽐뿌/핫딜</button>
            <button className={styles.tag}>재테크</button>
          </div>
          <button className={styles.write}>
            글쓰기
            <img src={write} alt="" className={styles.writeImg} />
          </button>
        </div>
        <div className={styles.inner2}>
          <div className={styles.search}>
            <img
              src={search}
              alt=""
              className={styles.searchImg}
              onClick={handleSubmit}
            />
            <input
              className={styles.inputSearch}
              value={inputValue}
              onChange={handleChangeValue}
              placeholder="게시글 검색"
              required
              autoComplete="on"
            ></input>
          </div>
          <img
            src={align}
            alt=""
            className={styles.alignImg}
            onClick={handleAlign}
          />
        </div>
        <div className={styles.articles}>
          <Articles />
        </div>
      </div>
    </>
  );
}

export default Board;
