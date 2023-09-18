import Header from "../../components/Header";
import styles from "./Join.module.css";
import { useInput } from "../../hooks/useInput";

function Join() {
  const handleSubmit = () => {
    console.log("회원가입 완료");
  };

  const [inputEmail, handleChangeEmail] = useInput("", handleSubmit);
  const [inputVerify, handleChangeVerify] = useInput("", handleSubmit);
  const [inputPw, handleChangePw] = useInput("", handleSubmit);
  const [inputPwCheck, handleChangePwCheck] = useInput("", handleSubmit);
  const [inputName, handleChangeName] = useInput("", handleSubmit);
  const [inputNickname, handleChangeNickname] = useInput("", handleSubmit);

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
              <button className={styles.btnForm}>이메일 인증</button>
            </div>
          </form>
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
              <button className={styles.btnForm}>인증 완료</button>
            </div>
          </form>
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

          {inputPw.length < 6 ? (
            <form className={styles.form}>
              <div className={styles.pwAlarm}>
                비밀번호는 6자리 이상이어야 하며 영문, 숫자, 특수문자를 반드시
                포함해야 합니다.
              </div>
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
                  ? "비밀번호가 일치합니다."
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
