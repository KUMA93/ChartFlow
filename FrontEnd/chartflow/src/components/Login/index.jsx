import styles from "./Login.module.css";
import modal_logo from "./../../assets/images/free-icon-growth-chart.png";
import closeBtn from "./../../assets/images/icons8-x-50.png";
import { useInput } from "../../hooks/useInput";

function Login({ modalShow, handleClose }) {
  const handleSubmit = () => {
    console.log("로그인 버튼 누름");
  };

  const [inputValue, handleChange] = useInput("", handleSubmit);
  const [inputValue2, handleChange2] = useInput("", handleSubmit);

  return (
    <div style={{ display: modalShow ? "block" : "none" }}>
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
            <input value={inputValue} onChange={handleChange} required></input>
            <label>이메일 주소</label>
            <span></span>
          </div>
          <div className={styles.form}>
            <input
              type="password"
              value={inputValue2}
              onChange={handleChange2}
              required
            ></input>
            <label>비밀번호</label>
            <span></span>
          </div>
          <button className={styles.loginBtn} onClick={handleSubmit}>
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
    </div>
  );
}
export default Login;
