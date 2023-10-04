import styles from "./Header.module.css";
import { useState, useContext } from "react";
import UserContext from "../../context/UserContext";
import main_logo from "./../../assets/images/main_logo.png";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import Login from "../Login";
import UserIcon from "./UserIcon";

function Header() {
  const {
    handleMainNavigate,
    handleGameNavigate,
    handleBoardNavigate,
    handleQuizNavigate,
    handleRankingNavigate,
  } = useCustomNavigate();
  const { isLogin } = useContext(UserContext);

  const MainLogo = () => (
    <img
      src={main_logo}
      alt="main_logo"
      className={styles.mainLogo}
      onClick={handleMainNavigate}
    />
  );
  const MenuItems = () => (
    <ul className={styles.menu}>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            isLogin
              ? handleGameNavigate()
              : alert("로그인이 필요한 기능입니다.");
          }}
        >
          차트게임
        </button>
      </li>
      <li>
        <button className={styles.tab} onClick={handleBoardNavigate}>
          커뮤니티
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            isLogin
              ? handleQuizNavigate()
              : alert("로그인이 필요한 기능입니다.");
          }}
        >
          주식퀴즈
        </button>
      </li>
      <li>
        <button className={styles.tab} onClick={handleRankingNavigate}>
          히스토리
        </button>
      </li>
    </ul>
  );

  const Start = () => (
    <button
      className={styles.btn}
      onClick={() => {
        handleModal();
      }}
    >
      지금 시작
    </button>
  );

  const Line = () => <div className={styles.line}></div>;
  const [modalShow, setModalShow] = useState(false);
  const handleModal = () => {
    modalShow ? setModalShow(false) : setModalShow(true);
  };

  const handleClose = () => {
    setModalShow(false);
  };

  return (
    <>
      <div className={styles.container}>
        <MainLogo />
        <MenuItems />
        {isLogin ? <UserIcon /> : <Start />}
      </div>
      <Line />
      {modalShow && <Login modalShow={modalShow} handleClose={handleClose} />}
    </>
  );
}

export default Header;
