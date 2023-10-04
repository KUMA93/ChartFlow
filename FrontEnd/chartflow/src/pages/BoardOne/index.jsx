import styles from "./BoardOne.module.css";
import { useParams } from "react-router-dom";
import Header from "../../components/Header";
import NewArticle from "../../components/NewArticle";
import NewComments from "../../components/NewComments";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { seeOneBoard } from "../../services/apis/board";
import { useState, useEffect } from "react";

function BoardOne() {
  const { handleBoardNavigate } = useCustomNavigate();
  const { articleId } = useParams();
  const [article, setArticle] = useState();

  useEffect(() => {
    seeOneBoard(articleId)
      .then((res) => setArticle(res.article))
      .catch((err) => console.error(err));
  }, [articleId]);
  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.board}>
          <div className={styles.title}>{article?.title}</div>
          <div className={styles.line}></div>
          <div className={styles.content}>{article?.content}</div>
        </div>
        <div className={styles.newArticle}>
          <NewArticle />
        </div>
        <div className={styles.newComments}>
          <NewComments />
        </div>
        <div className={styles.submit}>
          {/* <button className={styles.done} onClick={handleSubmit}>
          등록
        </button> */}
          <button className={styles.back} onClick={handleBoardNavigate}>
            목록으로
          </button>
        </div>
      </div>
    </>
  );
}

export default BoardOne;
