import { axiosServer, axiosServerWithoutToken } from "../axiosInstance";

// 로그인
export const login = async (requestLogin) => {
  const res = await axiosServerWithoutToken().post(`/auth/login`, requestLogin);
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

// 이메일 인증
export const emailAuthentication = async (email) => {
  const request = {
    email: email,
  };

  const res = await axiosServerWithoutToken().post(`/user/auth`, request);

  return res.data;
};

// 임시비밀번호 발급
export const temporaryPassword = async (email) => {
  const request = {
    email: email,
  };

  const res = await axiosServerWithoutToken().post(`/user/pass`, request);

  return res.data;
};

// 닉네임 중복 확인
export const verifyNickname = async (nickname) => {
  const res = await axiosServerWithoutToken().get(`/user/${nickname}`);

  return res.data;
};

// 마이페이지
export const getMypage = async () => {
  const res = await axiosServer().get(`/user`);

  return res.data;
};
