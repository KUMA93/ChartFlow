import styles from "./Login.module.css";
import modal_logo from "./../../assets/images/free-icon-growth-chart.png";
import closeBtn from "./../../assets/images/icons8-x-50.png";
import { useInput } from "../../hooks/useInput";
import { login } from "../../services/apis/user";
import UserContext from "../../context/UserContext";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useContext } from "react";

function Login({ modalShow, handleClose }) {
  const { handleMainNavigate, handleJoinNavigate, handleForgetNavigate } =
    useCustomNavigate();
  const { setIsLogin } = useContext(UserContext);

  const handleSubmit = (event) => {
    event.preventDefault();
    const requestLogin = {
      email: inputId,
      password: inputPw,
    };
    login(requestLogin)
      .then((res) => {
        localStorage.setItem("access-token", res["access-token"]);
        localStorage.setItem("refresh-token", res["refresh-token"]);
        setIsLogin(true);
        handleClose();

        handleMainNavigate();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const [inputId, handleChangeId] = useInput("", handleSubmit);
  const [inputPw, handleChangePw] = useInput("", handleSubmit);

  return (
    <div style={{ display: modalShow ? "block" : "none" }}>
      <div className={styles.shade} onClick={handleClose}>
        shade
      </div>
      <div className={styles.modalLogin}>
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
          <form className={styles.form} onSubmit={handleSubmit}>
            <input
              className={styles.inputLogin}
              value={inputId}
              onChange={handleChangeId}
              required
              autoComplete="on"
            ></input>
            <label className={styles.labelLogin}>이메일 주소</label>
            <span></span>
          </form>
          <form className={styles.form} onSubmit={handleSubmit}>
            <input
              className={styles.inputLogin}
              type="password"
              value={inputPw}
              onChange={handleChangePw}
              required
              autoComplete="on"
            ></input>
            <label className={styles.labelLogin}>비밀번호</label>
            <span></span>
          </form>
          <button
            className={styles.loginBtn}
            type="submit"
            onClick={handleSubmit}
          >
            로그인
          </button>
          <div className={styles.end}>
            <div className={styles.text2}>비밀번호를 잊으셨나요?</div>
            <div
              className={styles.signup}
              onClick={() => {
                handleForgetNavigate();
              }}
            >
              여기
            </div>
          </div>
          <div className={styles.end}>
            <div className={styles.text2}>차트플로우가 처음이신가요?</div>
            <div
              className={styles.signup}
              onClick={() => {
                handleJoinNavigate();
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
