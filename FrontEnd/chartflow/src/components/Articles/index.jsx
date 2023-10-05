import styles from "./Articles.module.css";
import thumbsup from "../../assets/images/thumbsup.png";
import chatting from "../../assets/images/chatting.png";
import eye from "../../assets/images/eye.png";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import calculateDaysAgo from "../../assets/calculate.js";
import { useEffect, useState } from "react";
import { seeAllBoard } from "../../services/apis/board";

function Articles({ alignMode, selectedTag, keyword, clicked }) {
  // console.log(keyword);

  useEffect(() => {
    console.log(keyword);
  }, [clicked]);

  const { handleBoardViewNavigate } = useCustomNavigate();
  const Line = () => <div className={styles.line}></div>;
  const [articles, setArticles] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const tagMappings = {
    FREE_DAILY: "자유일상",
    HUMOR_GOODWRITING: "유머/좋은글",
    TRAVEL: "여행",
    HOTDEAL: "뽐뿌/핫딜",
    FINANCE: "재태크",
  };

  useEffect(() => {
    seeAllBoard(currentPage, 8)
      .then((res) => {
        const filteredArticles = selectedTag
          ? res.articles.filter(
              (article) =>
                article.tag === selectedTag && article.deleted === false
            )
          : res.articles.filter((article) => article.deleted === false);

        if (alignMode === 0) {
          filteredArticles.sort((a, b) => {
            const dateA = new Date(a.registerTime);
            const dateB = new Date(b.registerTime);
            return dateB - dateA;
          });
        } else if (alignMode === 1) {
          filteredArticles.sort((a, b) => b.likes - a.likes);
        } else if (alignMode === 2) {
          filteredArticles.sort((a, b) => b.views - a.views);
        }

        setTotalPages(Math.ceil(filteredArticles.length / 8));
        setArticles(filteredArticles);
      })
      .catch((err) => console.error(err));
  }, [currentPage, alignMode, selectedTag]);

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
                  <div className={styles.tag}>
                    {" "}
                    [{tagMappings[article.tag]}]
                  </div>
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
