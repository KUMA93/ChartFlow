import { axiosServerWithoutToken } from "../axiosInstance";

// 로그인
export const topNranking = async (limit) => {
    const res = await axiosServerWithoutToken().get(`/info/rankings/${limit}`);
    return res.data;
  };