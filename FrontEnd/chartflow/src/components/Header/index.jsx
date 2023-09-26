import { useNavigate } from "react-router-dom";
import styles from "./Header.module.css";
import { useState, useContext, useEffect } from "react";
import UserContext from "../../context/UserContext";
import main_logo from "./../../assets/images/main_logo.png";
import Login from "../Login";
import UserIcon from "./UserIcon";

function Header() {
  const navigate = useNavigate();
  const { accessToken } = useContext(UserContext);

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
            accessToken
              ? navigate("/game")
              : alert("로그인이 필요한 기능입니다.");
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
            accessToken
              ? navigate("/quiz")
              : alert("로그인이 필요한 기능입니다.");
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
  const [isLogin, setIsLogin] = useState(false);

  const handleClose = () => {
    setModalShow(false);
  };

  const handleIsLogin = () => {
    setIsLogin(false);
  };

  useEffect(() => {
    if(localStorage.getItem("access-token") !== null){
      setIsLogin(true);
    }else {
      setIsLogin(false);
    }
  }, [localStorage.getItem("access-token")]);

  return (
    <>
      <div className={styles.container}>
        <MainLogo />
        <MenuItems />
        {isLogin ? <UserIcon isLogin={isLogin} handleIsLogin={handleIsLogin} /> : <Start />}
      </div>
      <Line />
      {modalShow && <Login modalShow={modalShow} handleClose={handleClose} />}
    </>
  );
}

export default Header;
