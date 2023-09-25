import Header from "../../components/Header";
import styles from "./Join.module.css";
import { useInput } from "../../hooks/useInput";
import { useState } from "react";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import {
  emailAuthentication,
  join,
  verifyNickname,
} from "../../services/apis/user";

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
    if (!isVerified) {
      alert("이메일 인증을 진행해주세요!");
      return;
    } else if (checkPw(inputPw) != null) {
      alert("비밀번호를 " + checkPw(inputPw));
      return;
    } else if (inputPw !== inputPwCheck) {
      alert("비밀번호를 다시 확인 해주세요.");
      return;
    } else if (inputName.length < 1) {
      alert("이름을 입력해주세요.");
      return;
    } else if (!isNicknameValid) {
      alert("닉네임 중복확인을 해주세요!");
      return;
    }
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
  const [isChecking, setIsChecking] = useState(false);
  const [authenticationCode, setAuthenticationCode] = useState("");
  const [isVerified, setIsVerified] = useState(false);
  const [isNicknameValid, setIsNicknameValid] = useState(false);

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

  const handleEmailVerify = (event) => {
    event.preventDefault();
    emailAuthentication(inputEmail)
      .then((res) => {
        console.log("인증번호: " + res);
        setAuthenticationCode(res);
        setIsChecking(true);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleAuthentication = (event) => {
    event.preventDefault();
    if (authenticationCode === inputVerify) {
      setIsVerified(true);
      alert("인증 되었습니다.");
    } else {
      alert("인증 번호가 정확하지 않습니다. 다시 확인해주세요.");
    }
  };

  const handleVerifyNickname = (event) => {
    event.preventDefault();
    verifyNickname(inputNickname)
      .then((res) => {
        console.log(res);
        if (res.isValid === true) {
          setIsNicknameValid(true);
          alert("사용해도 좋은 닉네임 입니다.");
        } else {
          setIsNicknameValid(false);
          alert("이미 존재하는 닉네임입니다.");
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };

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
                <button
                  className={styles.btnForm}
                  disabled={isVerified}
                  onClick={handleAuthentication}
                >
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
              <button className={styles.btnForm} onClick={handleVerifyNickname}>
                중복 확인
              </button>
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
