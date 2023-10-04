import styles from "./Articles.module.css";
import thumbsup from "../../assets/images/thumbsup.png";
import chatting from "../../assets/images/chatting.png";
import eye from "../../assets/images/eye.png";
import { useEffect } from "react";
import { seeAllBoard } from "../../services/apis/board";
import { useState } from "react";

function Articles(alignMode) {
  const Line = () => <div className={styles.line}></div>;
  const [articles, setArticles] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    seeAllBoard(currentPage, 8)
      .then((res) => {
        setArticles(res.articles);
        setTotalPages(Math.ceil(res.articles.length / 8) + 1);
      })
      .catch((err) => console.error(err));
  }, [currentPage]);

  function calculateDaysAgo(registerTime) {
    const currentDate = new Date();
    const registerDate = new Date(registerTime);
    const timeDifference = currentDate - registerDate;
    const daysAgo = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
    return `${daysAgo}일 전`;
  }

  return (
    <>
      <div className={styles.bg}>
        <div className={styles.list}>
          {articles?.map((article) => (
            <>
              <div className={styles.article} key={article.id}>
                <div className={styles.tag}>[자유일상]</div>
                <div className={styles.title}>{article.title}</div>
                <div className={styles.date}>
                  {calculateDaysAgo(article.registerTime)}
                </div>
                <div className={styles.iconGroup}>
                  <img src={eye} alt="" className={styles.eyeImg} />
                  <div className={styles.num}>{article.views}</div>
                  <div className={styles.dot}>⋅</div>
                  <img src={thumbsup} alt="" className={styles.Img} />
                  <div className={styles.num}>10</div>
                  <div className={styles.dot}>⋅</div>
                  <img src={chatting} alt="" className={styles.Img} />
                  <div className={styles.num}>10</div>
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
