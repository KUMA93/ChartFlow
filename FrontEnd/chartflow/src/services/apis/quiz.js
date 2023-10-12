import { axiosServer } from "../axiosInstance";

// 퀴즈 조회
export const getQuiz = async () => {
  const res = await axiosServer().get(`/quiz`);
  return res.data;
};

// 퀴즈 정답 제출
export const sendAnswer = async (request) => {
  const res = await axiosServer().post(`/quiz`, request);
  return res.data;
}