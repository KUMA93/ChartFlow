import styles from "./Articles.module.css";
import thumbsup from "../../assets/images/thumbsup.png";
import chatting from "../../assets/images/chatting.png";
import eye from "../../assets/images/eye.png";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import calculateDaysAgo from "../../assets/calculate.js";
import { useEffect } from "react";
import { seeAllBoard } from "../../services/apis/board";
import { useState } from "react";

function Articles(alignMode) {
  const { handleBoardViewNavigate } = useCustomNavigate();
  const Line = () => <div className={styles.line}></div>;
  const [articles, setArticles] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    seeAllBoard()
      .then((res) => {
        setTotalPages(Math.ceil(res.articles.length / 8));
        seeAllBoard(currentPage, 8).then((res) => setArticles(res.articles));
      })
      .catch((err) => console.error(err));
  }, [currentPage]);

  return (
    <>
      <div className={styles.bg}>
        <div className={styles.list}>
          {articles?.map((article) => (
            <>
              <div className={styles.article} key={article.id}>
                <div
                  onClick={() => handleBoardViewNavigate(article.id)}
                  className={styles.tagTitle}
                >
                  <div className={styles.tag}>[자유일상]</div>
                  <div className={styles.title}>{article.title}</div>
                </div>
                <div className={styles.date}>
                  {calculateDaysAgo(article.registerTime)}
                </div>
                <div className={styles.iconGroup}>
                  <img src={eye} alt="" className={styles.eyeImg} />
                  <div className={styles.num}>{article.views}</div>
                  <div className={styles.dot}>⋅</div>
                  <img src={thumbsup} alt="" className={styles.Img} />
                  <div className={styles.num}>{article.likes}</div>
                  <div className={styles.dot}>⋅</div>
                  <img src={chatting} alt="" className={styles.Img} />
                  <div className={styles.num}>{article.views}</div>
                </div>
              </div>
              <Line />
            </>
          ))}
        </div>
      </div>
      <div className={styles.pagination}>
        {totalPages > 0 &&
          Array(totalPages)
            .fill(0)
            .map((_, index) => (
              <div
                key={index}
                onClick={() => setCurrentPage(index)}
                className={
                  currentPage === index ? styles.currentPage : styles.page
                }
              >
                {index + 1}
              </div>
            ))}
      </div>
    </>
  );
}

export default Articles;
