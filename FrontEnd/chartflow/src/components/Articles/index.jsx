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

  useEffect(() => {
    const requestData = JSON.stringify({
      page: 0,
      size: 5,
      sort: ["string"],
    });

    seeAllBoard(requestData)
      .then((res) => setArticles(res.articles))
      .catch((err) => console.error(err));
  }, []);

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
      <div className={styles.pagination}>pagination</div>
    </>
  );
}

export default Articles;
