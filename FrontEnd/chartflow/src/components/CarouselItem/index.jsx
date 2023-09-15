import styles from "./CarouselItem.module.css";
// import { useNavigate } from "react-router-dom";

function CarouselItem({ item }) {
  // const navigate = useNavigate();

  return (
    <div className={styles.carouselItem}>
      <div></div>
      <img
        className={styles.carouselImg}
        src={item.icon}
        alt={item.icon.default}
        onClick={() => {
          console.log(item);
          // navigate("/game");
        }}
      />
    </div>
  );
}

export default CarouselItem;
