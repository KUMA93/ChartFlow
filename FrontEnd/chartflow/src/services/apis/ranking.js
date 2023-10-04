import { axiosServer, axiosServerWithoutToken } from "../axiosInstance";

// 로그인
export const topNranking = async (limit) => {
    const res = await axiosServerWithoutToken().get(`/board/${limit}`);
    return res.data;
  };