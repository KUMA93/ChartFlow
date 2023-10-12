import { useState } from "react";
import styles from "./Carousel.module.css";
import CarouselItem from "../CarouselItem";
import right_arrow from "../../assets/images/right_arrow.png";
import left_arrow from "../../assets/images/left_arrow.png";
import dot_active from "../../assets/images/dot_active.png";
import dot_inactive from "../../assets/images/dot_inactive.png";

function Carousel() {
  const [activeIndex, setActiveIndex] = useState(0);

  const items = [
    {
      key: 0,
      title: "game",
      icon: require("../../assets/images/carousel-chart.png"),
    },
    {
      key: 1,
      title: "board",
      icon: require("../../assets/images/carousel-community.png"),
    },
    {
      key: 2,
      title: "quiz",
      icon: require("../../assets/images/carousel-quiz.png"),
    },
    {
      key: 3,
      title: "hist",
      icon: require("../../assets/images/carousel-history.png"),
    },
  ];

  const updateIndex = (newIndex) => {
    if (newIndex < 0) {
      newIndex = 3;
    } else if (newIndex >= items.length) {
      newIndex = 0;
    }
    setActiveIndex(newIndex);
  };

  return (
    <div className={styles.carousel}>
      <div
        className={styles.inner}
        style={{ transform: `translate(-${activeIndex * 100}%)` }}
      >
        {items.map((item, key) => {
          return <CarouselItem item={item} key={key} />;
        })}
      </div>
      <div className={styles.button_position}>
        <button
          className={styles.button_arrow}
          onClick={() => {
            updateIndex(activeIndex - 1);
          }}
        >
          <img
            src={left_arrow}
            alt={left_arrow}
            className={styles.left_arrow}
          />
        </button>
        <div className={styles.indicators}>
          {items.map((item, index) => {
            return (
              <button
                className={styles.dot}
                onClick={() => {
                  updateIndex(index);
                }}
                key={index}
              >
                {index === activeIndex ? (
                  <img
                    src={dot_active}
                    alt={dot_active}
                    className={styles.dot}
                  />
                ) : (
                  <img
                    src={dot_inactive}
                    alt={dot_inactive}
                    className={styles.dot}
                  />
                )}
              </button>
            );
          })}
        </div>
        <button
          className={styles.button_arrow}
          onClick={() => {
            updateIndex(activeIndex + 1);
          }}
        >
          <img
            src={right_arrow}
            alt={right_arrow}
            className={styles.right_arrow}
          />
        </button>
      </div>
    </div>
  );
}

export default Carousel;
