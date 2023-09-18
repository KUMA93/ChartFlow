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
  // const handleLoginNavigate = () => {
  //   navigate("/login");
  // };

  const handleJoinNavigate = () => {
    navigate("/join");
  };

  const handleMyPageNavigate = () => {
    navigate("/mypage");
  };

  /* 커뮤니티 */
  const handleBoardNavigate = () => {
    navigate("/board");
  };

  const handleBoardHotNavigate = () => {
    navigate("/board/hot");
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
    navigate("/board/write");
  };

  const handleBoardViewNavigate = () => {
    navigate("/board/view");
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
    handleBoardHotNavigate,
    handleBoardSearchNavigate,
    // handleBoardMyPostNavigate,
    // handleBoardMyCommentedPostNavigate,
    // handleBoardMyScrapNavigate,
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
