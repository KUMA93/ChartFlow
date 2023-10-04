import styles from "./Comments.module.css";
import silence from "../../assets/images/silence.png";
import calculateDaysAgo from "../../assets/calculate.js";
import {
  seeAllComments,
  writeComment,
  deleteComment,
  writeReComment,
  reviseReComment,
  deleteReComment,
} from "../../services/apis/comment";

import { useInput } from "../../hooks/useInput";
import { useState, useEffect } from "react";

function Comments(props) {
  const [comments, setComments] = useState([]);
  const [openRe, setOpenRe] = useState(true);
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedCommentId, setSelectedCommentId] = useState(null);
  const [replyFormVisible, setReplyFormVisible] = useState({});

  const commentsPerPage = 10;

  const handleSubmitComment = (event) => {
    event.preventDefault();
    const requestData = JSON.stringify({
      articleId: props.articleId,
      content: inputComment,
    });
    if (inputComment === "") {
      alert("댓글을 입력해주세요.");
    }
    writeComment(requestData).then(() => {
      seeAllComments(props.articleId).then((res) => setComments(res.reverse()));
      setTotalPages(Math.ceil(comments.length / 10));
      setInputComment("");
    });
  };

  const handleSubmitReComment = (event) => {
    event.preventDefault();
    console.log(inputReComment);
  };

  const [inputComment, handleChangeComment, setInputComment] = useInput(
    "",
    handleSubmitComment
  );
  const [inputReComment, handleChangeReComment] = useInput(
    "",
    handleSubmitReComment
  );

  const getCurrentComments = () => {
    const indexOfLastComment = currentPage * commentsPerPage;
    const indexOfFirstComment = indexOfLastComment - commentsPerPage;
    return comments.slice(indexOfFirstComment, indexOfLastComment);
  };

  useEffect(() => {
    seeAllComments(props.articleId).then((res) => setComments(res.reverse()));
    setTotalPages(Math.ceil(comments.length / 10));
  }, [props.articleId, comments.length]);

  const handleCommentClick = (commentId) => {
    setSelectedCommentId(commentId);
    setReplyFormVisible((prevState) => ({
      ...prevState,
      [commentId]: !prevState[commentId],
    }));
  };

  const renderReplyForm = (commentId) => {
    if (selectedCommentId === commentId && replyFormVisible[commentId]) {
      return (
        <form onSubmit={handleSubmitReComment}>
          <input
            className={styles.inputReComment}
            placeholder="대댓글 쓰기"
            onChange={handleChangeReComment}
            value={inputReComment}
          ></input>
          <button className={styles.ReComBtn}>작성</button>
        </form>
      );
    }
    return null;
  };

  return (
    <div className={styles.bg}>
      <div className={styles.total}>
        전체 댓글 <div className={styles.commentCount}>{comments.length}</div>개
      </div>
      <div className={styles.line2}></div>
      <form onSubmit={handleSubmitComment}>
        <input
          className={styles.inputComment}
          placeholder="댓글 쓰기"
          onChange={handleChangeComment}
          value={inputComment}
        ></input>
        <button className={styles.ComBtn}>작성</button>
      </form>

      {comments.length === 0 ? (
        <div className={styles.comments}>
          <div className={styles.notice}>아직 댓글이 없습니다.</div>
          <div className={styles.notice}>
            첫 번째 댓글의 주인공이 되어보세요!
          </div>
          <img src={silence} alt="silence" className={styles.silence} />
        </div>
      ) : (
        getCurrentComments().map((comment) => (
          <div key={comment.commentId} className={styles.comment}>
            <div className={styles.name}>{comment.userId}번 사용자✔️</div>
            <div className={styles.date}>
              {calculateDaysAgo(comment.registerTime)}
            </div>
            <div
              className={styles.content}
              onClick={() => handleCommentClick(comment.commentId)}
            >
              {comment.content}
            </div>
            {openRe && renderReplyForm(comment.commentId)}
            <div className={styles.line3}></div>
          </div>
        ))
      )}
      <div className={styles.pagination}>
        {totalPages > 0 &&
          Array(totalPages)
            .fill(0)
            .map((_, index) => (
              <div
                key={index}
                onClick={() => setCurrentPage(index + 1)}
                className={
                  currentPage === index + 1 ? styles.currentPage : styles.page
                }
              >
                {index + 1}
              </div>
            ))}
      </div>
    </div>
  );
}

export default Comments;
