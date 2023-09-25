import Header from "../../components/Header";
import styles from "./Join.module.css";
import { useInput } from "../../hooks/useInput";
import { useState, useContext } from "react";
import UserContext from "../../context/UserContext";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { join } from "../../services/apis/user";

function Join() {
  const { handleJoinCompleteNavigate } = useCustomNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const requestJoin = {
      email: inputEmail,
      password: inputPw,
      name: inputName,
      nickname: inputNickname,
    };
    join(requestJoin)
      .then((res) => {
        handleJoinCompleteNavigate();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const [inputEmail, handleChangeEmail] = useInput("", handleSubmit);
  const [inputVerify, handleChangeVerify] = useInput("", handleSubmit);
  const [inputPw, handleChangePw] = useInput("", handleSubmit);
  const [inputPwCheck, handleChangePwCheck] = useInput("", handleSubmit);
  const [inputName, handleChangeName] = useInput("", handleSubmit);
  const [inputNickname, handleChangeNickname] = useInput("", handleSubmit);
  // const [alertMessage, setAlertMessage] = useState("")
  const [isChecking, setIsChecking] = useState(false);
  const [isVerified, setIsVerified] = useState(false);

  function checkPw(inputPw) {
    let pw = inputPw;
    let num = pw.search(/[0-9]/g);
    let eng = pw.search(/[a-z]/gi);
    let spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (pw.length < 8 || pw.length > 20) {
      return "8자리 ~ 20자리 이내로 입력해주세요.";
    }
    if (pw.search(/\s/) !== -1) {
      return "비밀번호는 공백없이 입력해주세요.";
    }
    if (num < 0 || eng < 0 || spe < 0) {
      return "영문,숫자, 특수문자를 혼합하여 입력해주세요.";
    }
    return null;
  }

  // const handleEmailVerify = async (inputEmail) => {
  //   setIsChecking(true);
  //   try {
  //     const result = await emailVerify(inputEmail);
  //     console.log("result:", result);
  //     // setIsVerified(true);
  //   } catch (error) {
  //     console.error("이메일 인증 에러:", error);
  //   }
  // };

  const handleEmailVerify = () => {};
  return (
    <>
      <Header />
      <div>
        <div className={styles.title}>회원가입</div>
        <div className={styles.forms}>
          <form className={styles.form}>
            <label className={styles.labelForm}>이메일 주소</label>
            <div className={styles.inputGroup}>
              <input
                className={styles.inputForm}
                value={inputEmail}
                onChange={handleChangeEmail}
                required
                autoComplete="on"
              ></input>
              <button className={styles.btnForm} onClick={handleEmailVerify}>
                이메일 인증
              </button>
            </div>
          </form>
          {isChecking ? (
            <form className={styles.form}>
              <label className={styles.labelForm}>인증번호 확인</label>
              <div className={styles.inputGroup}>
                <input
                  className={styles.inputForm}
                  value={inputVerify}
                  onChange={handleChangeVerify}
                  required
                  autoComplete="on"
                ></input>
                <button className={styles.btnForm} disabled={isVerified}>
                  인증 완료
                </button>
              </div>
            </form>
          ) : null}

          <form className={styles.form}>
            <label className={styles.labelForm}>비밀번호</label>
            <div className={styles.inputGroup}>
              <input
                className={styles.inputFormFull}
                type="password"
                value={inputPw}
                onChange={handleChangePw}
                required
                autoComplete="on"
              ></input>
            </div>
          </form>

          {checkPw(inputPw) ? (
            <form className={styles.form}>
              <div className={styles.pwAlarm}>{checkPw(inputPw)}</div>
            </form>
          ) : null}

          <form className={styles.form}>
            <label className={styles.labelForm}>비밀번호 확인</label>
            <div className={styles.inputGroup}>
              <input
                className={styles.inputFormFull}
                type="password"
                value={inputPwCheck}
                onChange={handleChangePwCheck}
                required
                autoComplete="on"
              ></input>
            </div>
            <div className={styles.pwCheck}>
              {inputPw.length !== 0
                ? inputPw === inputPwCheck
                  ? null
                  : "비밀번호가 일치하지 않습니다."
                : ""}
            </div>
          </form>
          <form className={styles.form}>
            <label className={styles.labelForm}>이름</label>
            <div className={styles.inputGroup}>
              <input
                className={styles.inputFormFull}
                value={inputName}
                onChange={handleChangeName}
                required
                autoComplete="on"
              ></input>
            </div>
          </form>
          <form className={styles.form}>
            <label className={styles.labelForm}>닉네임</label>
            <div className={styles.inputGroup}>
              <input
                className={styles.inputForm}
                value={inputNickname}
                onChange={handleChangeNickname}
                required
                autoComplete="on"
              ></input>
              <button className={styles.btnForm}>중복 확인</button>
            </div>
          </form>
          <form className={styles.form}>
            <button className={styles.btnJoin} onClick={handleSubmit}>
              가입하기
            </button>
          </form>
        </div>
      </div>
    </>
  );
}

export default Join;
