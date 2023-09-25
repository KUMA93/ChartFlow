import useCustomNavigate from "../../hooks/useCustomNavigate";
import Header from "../../components/Header";
import styles from "./NotFound.module.css"
import errorImage from "../../assets/images/404error.png"

function NotFound() {
  const { handleMainNavigate } = useCustomNavigate();

  return (
    <>
    <Header />
      <div className={styles.center}>
        <img src={errorImage} alt="" className={styles.margin} />
        <div className={styles.font}>페이지를 찾을 수 없습니다.</div>
        <div>
          <button onClick={handleMainNavigate} className={styles.mainBtn}>메인으로 돌아가기</button>
        </div>
      </div>
    </>
  );
}

export default NotFound;
