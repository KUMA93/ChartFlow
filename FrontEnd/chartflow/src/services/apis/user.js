import { axiosServer } from "../axiosInstance";

// 로그인
export const login = async (RequestLoginDto) => {
  const res = await axiosServer().post(`api/auth/login`, RequestLoginDto);
  return res.data;
};
