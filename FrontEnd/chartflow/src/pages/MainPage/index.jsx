import Header from "../../components/Header";
import Toggle from "../../components/Toggle";
import Carousel from "../../components/Carousel";
import HotUpdate from "../../components/HotUpdate";
import RankWeekly from "../../components/RankWeekly";
import Footer from "../../components/Footer";
import styles from "./MainPage.module.css";

const MainPage = () => {
  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.carousel}>
          <Carousel />
        </div>
        <div className={styles.hotUpdate}>
          <HotUpdate />
        </div>
        <div className={styles.gotoGame}>차트게임 하러가기</div>
        <div className={styles.rankWeekly}>
          <RankWeekly />
        </div>
        <div className={styles.footer}>
          <Footer />
        </div>
      </div>
      <Toggle />
    </>
  );
};

export default MainPage;
