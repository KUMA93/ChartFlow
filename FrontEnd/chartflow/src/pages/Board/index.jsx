import Header from "../../components/Header";
import HotUpdate from "../../components/HotUpdate";
import Articles from "../../components/Articles";
import styles from "./Board.module.css";
import write from "../../assets/images/write.png";
import align from "../../assets/images/align.png";
import search from "../../assets/images/search.png";
import { useState, useContext } from "react";
import UserContext from "../../context/UserContext";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function Board() {
  const { handleBoardWriteNavigate } = useCustomNavigate();
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const [alignMode, setAlignMode] = useState(0);
  const { isLogin } = useContext(UserContext);
  const [selectedTag, setSelectedTag] = useState(null);
  const [inputKeyword, setInputKeyword] = useState("");
  const [clicked, setClicked] = useState(true);
  const handleTagClick = (tag) => {
    setSelectedTag(tag);
  };
  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  const handleSearch = () => {
    setClicked(!clicked);
    const keyword = inputKeyword.trim();
    setInputKeyword(keyword);
  };

  const handleAlign = (event, mode) => {
    event.preventDefault();
    setAlignMode(mode);
    toggleDropdown();
  };

  return (
    <>
      <Header />
      <div className={styles.container}>
        <div className={styles.hotUpdate}>
          <HotUpdate />
        </div>
        <div className={styles.inner1}>
          <div className={styles.tags}>
            <button className={styles.tag} onClick={() => handleTagClick(null)}>
              전체
            </button>
            <button
              className={styles.tag}
              onClick={() => handleTagClick("FREE_DAILY")}
            >
              자유일상
            </button>
            <button
              className={styles.tag}
              onClick={() => handleTagClick("HUMOR_GOODWRITING")}
            >
              유머/좋은글
            </button>
            <button
              className={styles.tag}
              onClick={() => handleTagClick("TRAVEL")}
            >
              여행
            </button>
            <button
              className={styles.tag}
              onClick={() => handleTagClick("HOTDEAL")}
            >
              뽐뿌/핫딜
            </button>
            <button
              className={styles.tag}
              onClick={() => handleTagClick("FINANCE")}
            >
              재테크
            </button>
          </div>
          {isLogin && (
            <button className={styles.write} onClick={handleBoardWriteNavigate}>
              글쓰기
              <img src={write} alt="" className={styles.writeImg} />
            </button>
          )}
        </div>
        <div className={styles.inner2}>
          <form className={styles.search} onSubmit={handleSearch}>
            <img
              src={search}
              alt=""
              className={styles.searchImg}
              onClick={handleSearch}
            />
            <input
              className={styles.inputSearch}
              value={inputKeyword}
              onChange={(e) => setInputKeyword(e.target.value)}
              placeholder="게시글 검색"
              required
              autoComplete="on"
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  handleSearch();
                }
              }}
            ></input>
          </form>
          <img
            src={align}
            alt=""
            className={styles.alignImg}
            onClick={toggleDropdown}
          />
          {dropdownVisible && (
            <div className={styles.dropdown}>
              <button
                className={styles.dropdownButton}
                onClick={(event) => handleAlign(event, 0)}
              >
                최신순
              </button>
              <button
                className={styles.dropdownButton}
                onClick={(event) => handleAlign(event, 1)}
              >
                인기순
              </button>
              <button
                className={styles.dropdownButton}
                onClick={(event) => handleAlign(event, 2)}
              >
                댓글 많은 순
              </button>
            </div>
          )}
        </div>
        <div className={styles.articles}>
          <Articles
            alignMode={alignMode}
            selectedTag={selectedTag}
            keyword={inputKeyword}
            clicked={clicked}
          />
        </div>
      </div>
    </>
  );
}

export default Board;
