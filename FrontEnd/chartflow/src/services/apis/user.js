import { axiosServerWithoutToekn } from "../axiosInstance";

// 로그인
export const login = async (RequestLoginDto) => {
  const res = await axiosServerWithoutToekn().post(
    `api/auth/login`,
    RequestLoginDto
  );
  return res.data;
};
