import { axiosServer, axiosServerWithoutToken } from "../axiosInstance";

// 전체 댓글 조회
export const seeAllComments = async (articleId) => {
  const res = await axiosServerWithoutToken().get(`/comment/${articleId}`);
  return res.data;
};

// 댓글 작성
export const writeComment = async (requestData) => {
  const res = await axiosServer().post("/comment", requestData);
  return res.data;
};

// 댓글 수정
export const reviseComment = async (requestData) => {
  const res = await axiosServerWithoutToken().patch(`/comment`, requestData);
  return res.data;
};

// 댓글 삭제
export const deleteComment = async (commentId) => {
  const res = await axiosServerWithoutToken().delete(`/comment/${commentId}`);
  return res.data;
};

// 대댓글 작성
export const writeReComment = async () => {
  const res = await axiosServer().post("/comment/re");
  return res.data;
};

// 대댓글 수정
export const reviseReComment = async () => {
  const res = await axiosServer().patch("/comment/re");
  return res.data;
};

// 대댓글 삭제
export const deleteReComment = async (reCommentId) => {
  const res = await axiosServer().delete(`/comment/re/${reCommentId}`);
  return res.data;
};
