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
      <div className={styles.font}>
        <div className={styles.title}>회원가입</div>
        <div className={styles.center}>
          이메일 주소
          <div className={styles.inputForm}>
            <input value={inputValue} onChange={handleChange} required></input>
          </div>
        </div>
      </div>
      <button className={styles.joinBtn} onClick={handleSubmit}>
        가입하기
      </button>
    </>
  );
}

export default SignUp;
