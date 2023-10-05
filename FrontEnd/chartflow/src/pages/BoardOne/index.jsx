import styles from "./BoardOne.module.css";
import { useParams } from "react-router-dom";
import Header from "../../components/Header";
import Comments from "../../components/Comments";
import NewArticle from "../../components/NewArticle";
import NewComments from "../../components/NewComments";
import { seeOneBoard } from "../../services/apis/board";
import { useState, useEffect } from "react";
import noThumbsup from "../../assets/images/thumbsupWhite.png";
import Thumbsup from "../../assets/images/thumbsupBlack.png";

function BoardOne() {
  const { articleId } = useParams();
  const [article, setArticle] = useState();
  const [thumbsup, setThumbsup] = useState(false);

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
          <div className={styles.article}>
            <div className={styles.title}>
              {article?.title}
              <img
                src={thumbsup ? Thumbsup : noThumbsup}
                alt="thumbsup"
                onClick={() => {
                  setThumbsup(!thumbsup);
                }}
                className={styles.thumbsup}
              ></img>
            </div>
            <div className={styles.line}></div>
            <div className={styles.content}>{article?.content}</div>
          </div>
          <div className={styles.comments}>
            <Comments articleId={articleId} />
          </div>
        </div>

        <div className={styles.new}>
          <NewArticle />
          <NewComments />
        </div>
      </div>
    </>
  );
}

export default BoardOne;
