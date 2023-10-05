import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Toggle from "../../components/Toggle";
import Chatbot from "../../components/Chatbot";
import Carousel from "../../components/Carousel";
import HotUpdate from "../../components/HotUpdate";
import RankWeekly from "../../components/RankWeekly";
import { useContext } from "react";
import { useState } from "react";

import UserContext from "../../context/UserContext";

import styles from "./MainPage.module.css";

const MainPage = () => {
  const handleGame = () => {
    window.location.href = "/game";
  };
  const { isLogin } = useContext(UserContext);

  const [isModalOpen, setIsModalOpen] = useState(false)
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

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
        <div
          className={styles.gotoGame}
          onClick={() => {
            isLogin ? handleGame() : alert("로그인이 필요한 기능입니다.");
          }}
        >
          차트게임 하러가기
        </div>
        <div className={styles.rankWeekly}>
          <RankWeekly />
        </div>
      </div>
      <button className={styles.chatbotBtn} onClick={openModal}>궁금해요</button>
      <Chatbot isOpen={isModalOpen} closeModal={closeModal}/>
      <Toggle />
      <Footer />
    </>
  );
};

export default MainPage;
