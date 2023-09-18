import { useNavigate } from "react-router-dom";
import styles from "./Header.module.css";
import { useState } from "react";
import main_logo from "./../../assets/images/main_logo.png";
import Login from "../Login";

function Header() {
  const navigate = useNavigate();

  const MainLogo = () => (
    <a href="/">
      <img src={main_logo} alt="main_logo" className={styles.mainLogo} />
    </a>
  );
  const MenuItems = () => (
    <ul className={styles.menu}>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/game");
          }}
        >
          차트게임
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/board");
          }}
        >
          커뮤니티
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/quiz");
          }}
        >
          주식퀴즈
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/hist");
          }}
        >
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
        <Start />
      </div>
      <Line />
      {modalShow && <Login modalShow={modalShow} handleClose={handleClose} />}
    </>
  );
}

export default Header;
