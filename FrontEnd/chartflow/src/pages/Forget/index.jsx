import Header from "../../components/Header";
import styles from "./forget.module.css";
import { useInput } from "../../hooks/useInput";

function Forget() {
  const handleSubmit = () => {};
  const [inputEmail, handleChangeEmail] = useInput("", handleSubmit);
  return (
    <>
      <Header />
      <div className={styles.inputGroup}>
        <div className={styles.innerText}>
          임시비밀번호를 받을 이메일을 입력해주세요.
        </div>
        <input
          className={styles.inputForm}
          value={inputEmail}
          onChange={handleChangeEmail}
          required
          autoComplete="on"
        ></input>
        <button className={styles.sendBtn} onClick={handleSubmit}>
          임시비밀번호 전송
        </button>
      </div>
    </>
  );
}

export default Forget;
