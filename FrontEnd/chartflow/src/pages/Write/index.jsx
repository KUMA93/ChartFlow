import Header from "../../components/Header";
import styles from "./Write.module.css";
import NewArticle from "../../components/NewArticle";
import NewComments from "../../components/NewComments";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { useInput } from "../../hooks/useInput";
import { writeBoard } from "../../services/apis/board";

function Write() {
  const location = useLocation();

  const { handleBoardNavigate } = useCustomNavigate();
  const [inputTitle, handleChangeTitle, setInputTitle] = useInput(
    location.state !== null ? location.state.title : ""
  );
  const [inputContent, handleChangeContent, setInputContent] = useInput(
    location.state !== null ? location.state.content : ""
  );
  const [selectedTag, handleChangeTag, setSelectedTag] = useInput("");

  useEffect(() => {
    setSelectedTag(location.state !== null ? location.state.tag : "");
    setInputTitle(location.state !== null ? location.state.title : "");
    setInputContent(location.state !== null ? location.state.content : "");
  }, [location]);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!selectedTag) {
      alert("태그를 선택해주세요");
    } else if (!inputTitle) {
      alert("제목을 입력해주세요");
    } else if (!inputContent) {
      alert("내용을 입력해주세요");
    } else {
      const requestData = JSON.stringify({
        tag: selectedTag,
        title: inputTitle,
        content: inputContent,
      });
      writeBoard(requestData)
        .then(handleBoardNavigate)
        .catch((err) => console.error(err));
    }
  };

  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.board}>
          <div className={styles.title}>
            <select
              id="tag-select"
              className={styles.tags}
              value={selectedTag}
              onChange={handleChangeTag}
            >
              <option value="">태그 선택</option>
              <option value="FREE_DAILY">자유일상</option>
              <option value="HUMOR_GOODWRITING">유머/좋은글</option>
              <option value="TRAVEL">여행</option>
              <option value="HOTDEAL">뽐뿌/핫딜</option>
              <option value="FINANCE">재테크</option>
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
