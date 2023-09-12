import { useNavigate } from "react-router-dom";
import main_logo from "./../../assets/images/main_logo.png";
import styles from "./Header.module.css";
import { useState } from "react";
import closeBtn from "./../../assets/images/icons8-x-50.png";
import modal_logo from "./../../assets/images/free-icon-growth-chart.png";

function Header() {
  const navigate = useNavigate();
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
            navigate("/boards");
          }}
        >
          커뮤니티
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
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/mypage");
          }}
        >
          마이페이지
        </button>
      </li>
    </ul>
  );

  const handleClose = () => {
    setModalShow(false);
  };

  const handleLogin = () => {
    console.log("로그인 버튼 누름");
  };
  const Login = () => (
    <>
      <div className={styles.shade}>shade</div>
      <div className={styles.login}>
        <img
          src={closeBtn}
          alt="closeBtn"
          className={styles.closeBtn}
          onClick={handleClose}
        />
        <div className={styles.title}>
          <img src={modal_logo} alt="modal_logo" className={styles.modalLogo} />
          <div className={styles.texts}>
            <div className={styles.text}>놀면서 배우는 차트,</div>
            <div className={styles.text}>
              <div className={styles.strong}>차트플로우</div>를 시작해볼까요?
            </div>
          </div>
        </div>
        <div className={styles.forms}>
          <div className={styles.form}>
            <input type="text" required></input>
            <label>이메일 주소</label>
            <span></span>
          </div>
          <div className={styles.form}>
            <input type="password" required></input>
            <label>비밀번호</label>
            <span></span>
          </div>
          <button className={styles.loginBtn} onClick={handleLogin}>
            로그인
          </button>
          <div className={styles.end}>
            <div className={styles.text2}>차트플로우가 처음이신가요?</div>
            <div
              className={styles.signup}
              onClick={() => {
                window.location.href = "/signup";
              }}
            >
              회원가입
            </div>
          </div>
        </div>
      </div>
    </>
  );

  const [modalShow, setModalShow] = useState(false);
  const handleModal = () => {
    modalShow ? setModalShow(false) : setModalShow(true);
  };

  return (
    <>
      <div className={styles.container}>
        <div className={styles.section1}>
          <a href="/">
            <img
              src={main_logo}
              alt="main_logo"
              width={200}
              style={{ alignSelf: "center" }}
            />
          </a>
        </div>
        <div className={styles.section2}>
          <MenuItems />
        </div>
        <div className={styles.section3}>
          <button
            className={styles.btn}
            onClick={() => {
              handleModal();
            }}
          >
            지금 시작
          </button>
        </div>
      </div>
      <div className={styles.line}></div>
      {modalShow && <Login />}
    </>
  );
}

export default Header;
