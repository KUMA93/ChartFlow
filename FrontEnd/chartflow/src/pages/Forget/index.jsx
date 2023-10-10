import Header from "../../components/Header";
import styles from "./forget.module.css";
import { useInput } from "../../hooks/useInput";
import { temporaryPassword } from "../../services/apis/user";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function Forget() {
  const { handleMainNavigate } = useCustomNavigate();
  const handleSubmit = () => {
    let emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if (!inputEmail.match(emailReg)){
      alert("올바른 이메일을 입력하세요.");
      return;
    }

    temporaryPassword(inputEmail)
      .then((res) => {
        alert("임시 비밀번호를 이메일로 발급해드렸습니다.");
        handleMainNavigate();
      })
      .catch((err) => {
        console.error(err);
      })
  };
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
