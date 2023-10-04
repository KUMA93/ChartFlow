import { useNavigate } from "react-router-dom";

const useCustomNavigate = () => {
  const navigate = useNavigate();

  const handleGoBack = () => {
    navigate(-1);
  };

  const handleMainNavigate = () => {
    navigate("/");
  };

  /* User */
  const handleLoginNavigate = () => {
    navigate("/login");
  };

  const handleForgetNavigate = () => {
    navigate("/forget");
  };

  const handleJoinNavigate = () => {
    navigate("/join");
  };

  const handleJoinCompleteNavigate = () => {
    navigate("/complete");
  };

  const handleMyPageNavigate = () => {
    navigate("/mypage");
  };

  /* 커뮤니티 */
  const handleBoardNavigate = () => {
    navigate("/board");
  };

  // const handleBoardMyPostNavigate = () => {
  //   navigate("/board/my-post");
  // };

  // const handleBoardMyCommentedPostNavigate = () => {
  //   navigate("/board/my-commented-post");
  // };

  // const handleBoardMyScrapNavigate = () => {
  //   navigate("/board/my-scrap");
  // };

  const handleBoardWriteNavigate = () => {
    navigate("/board/new");
  };

  const handleBoardViewNavigate = (articleId) => {
    navigate(`/board/${articleId}`);
  };

  const handleBoardEditNavigate = (postId) => {
    navigate("/board/edit/" + postId);
  };

  const handleBoardSearchNavigate = (keyword) => {
    navigate("/board/search/" + keyword);
  };

  /* 차트게임 */
  const handleGameNavigate = () => {
    navigate("/game");
  };

  /* 주식퀴즈 */
  const handleQuizNavigate = () => {
    navigate("/quiz");
  };

  /* 히스토리 */
  const handleRankingNavigate = () => {
    navigate("/hist/ranking");
  };
  const handleRecordNavigate = () => {
    navigate("/hist/record");
  };
  const handleEmblemNavigate = () => {
    navigate("/hist/emblem");
  };

  return {
    handleGoBack,
    handleMainNavigate,
    handleLoginNavigate,
    handleJoinNavigate,
    handleMyPageNavigate,
    handleBoardNavigate,
    handleBoardSearchNavigate,
    handleForgetNavigate,
    // handleBoardMyPostNavigate,
    // handleBoardMyCommentedPostNavigate,
    // handleBoardMyScrapNavigate,
    handleJoinCompleteNavigate,
    handleBoardWriteNavigate,
    handleBoardViewNavigate,
    handleBoardEditNavigate,
    handleGameNavigate,
    handleQuizNavigate,
    handleRankingNavigate,
    handleRecordNavigate,
    handleEmblemNavigate,
  };
};

export default useCustomNavigate;
