import Header from "../../components/Header";
import styles from './Signup.module.css';

function SignUp() {
  return (
    <>
      <Header />
      <div className={styles.title}>회원가입</div>
      <div className={styles.center}>
        <div className={styles.font}>이메일 주소
          <div className={styles.inputForm}>
          </div>
        </div>
      </div>
    </>
  );
}

export default SignUp;
