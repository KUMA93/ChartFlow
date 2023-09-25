import { axiosServerWithoutToken } from "../axiosInstance";

// 로그인
export const login = async (RequestLoginDto) => {
  const res = await axiosServerWithoutToken().post(
    `auth/login`,
    RequestLoginDto
  );
  return res.data;
};

// 회원가입
export const join = async (RequestJoinDto) => {
  const res = await axiosServerWithoutToken().post(
    `user/regist`,
    RequestJoinDto
  );
  return res.data;
};
