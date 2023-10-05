import { axiosServer, axiosServerWithoutToken } from "../axiosInstance";

// 전체 게시글 조회
export const seeAllBoard = async (page, size) => {
  const res = await axiosServerWithoutToken().get(
    `/board/list?page=${page}&size=${size}`
  );
  return res.data;
};

// 특정 게시글 조회
export const seeOneBoard = async (articleId) => {
  const res = await axiosServer().get(`/board/${articleId}`);
  return res.data;
};

// 게시글 작성
export const writeBoard = async (requestData) => {
  const res = await axiosServer().post(`/board`, requestData);
  return res.data;
};

// 게시글 수정
export const reviseBoard = async (requestData) => {
  const res = await axiosServerWithoutToken().patch(`/board`, requestData);
  return res.data;
};

// 게시글 삭제
export const deleteBoard = async (requestData) => {
  const res = await axiosServer().delete(`/board`, requestData);
  return res.data;
};

// 게시글 좋아요
export const likeBoard = async (requestData) => {
  const res = await axiosServer().post(`/game`, requestData);
  return res.data;
};

// 게시글 좋아요 취소
export const notlikeBoard = async (requestData) => {
  const res = await axiosServer().delete(`/game`, requestData);
  return res.data;
};

// 게시글 검색
export const searchBoard = async (requestData) => {
  const res = await axiosServer().get("board/list", requestData);
  return res.data;
};
