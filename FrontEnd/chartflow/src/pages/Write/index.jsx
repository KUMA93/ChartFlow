import Header from "../../components/Header";
import styles from "./Write.module.css";
import NewArticle from "../../components/NewArticle";
import NewComments from "../../components/NewComments";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useInput } from "../../hooks/useInput";

function Write() {
  const { handleBoardNavigate } = useCustomNavigate();
  const handleSubmit = (event) => {
    event.preventDefault();
    handleBoardNavigate();
  };
  const [inputTitle, handleChangeTitle] = useInput("", handleSubmit);
  const [inputContent, handleChangeContent] = useInput("", handleSubmit);

  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.board}>
          <div className={styles.title}>
            <select id="tag-select" className={styles.tags}>
              <option value="태그 선택">태그 선택</option>
              <option value="자유일상">자유일상</option>
              <option value="유머/좋은글">유머/좋은글</option>
              <option value="여행">여행</option>
              <option value="뽐뿌/핫딜">뽐뿌/핫딜</option>
              <option value="재테크">재테크</option>
            </select>
            <input
              className={styles.inputTitle}
              value={inputTitle}
              onChange={handleChangeTitle}
              required
            ></input>
          </div>
          <textarea
            className={styles.content}
            value={inputContent}
            onChange={handleChangeContent}
            required
          ></textarea>
        </div>
        <div className={styles.newArticle}>
          <NewArticle />
        </div>
        <div className={styles.newComments}>
          <NewComments />
        </div>
        <div className={styles.submit}>
          <button className={styles.done} onClick={handleSubmit}>
            등록
          </button>
          <button className={styles.back} onClick={handleBoardNavigate}>
            취소
          </button>
        </div>
      </div>
    </>
  );
}

export default Write;
