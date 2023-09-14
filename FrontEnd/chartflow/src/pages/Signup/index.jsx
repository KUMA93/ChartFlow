import Header from "../../components/Header";
import styles from "./Signup.module.css";
import { useInput } from "../../hooks/useInput";

function SignUp() {
  const handleSubmit = () => {
    console.log("회원가입 완료");
  };

  const [inputValue, handleChange] = useInput("", handleSubmit);

  return (
    <>
      <Header />
      <div className={styles.title}>회원가입</div>

      <div className={styles.center}>
        <div className={styles.font}>이메일 주소</div>
          <div className={styles.inputForm}>
            <input className={styles.textInput} value={inputValue} onChange={handleChange} required></input>
          </div>
        </div>
      <div className={styles.center}>
        <button className={styles.joinBtn} onClick={handleSubmit}>
          가입하기
        </button>
      </div>
    </>
  );
}

export default SignUp;
