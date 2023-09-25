import { axiosServerWithoutToekn } from "../axiosInstance";

// 로그인
export const login = async (requestLogin) => {
  const res = await axiosServerWithoutToekn().post(
    `/auth/login`,
    requestLogin,
  );
  return res.data;
};
