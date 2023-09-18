import axiosInstance from "./axiosInstance";

const authService = axiosInstance();

const login = async (providerId) => {
  const redirectUri = encodeURIComponent(
    "http://localhost:3000/oauth/redirect"
  );
  try {
    const response = await authService.get(
      `/oauth2/authorization/${providerId}`,
      {
        params: { redirect_uri: redirectUri },
      }
    );

    return response.data;
  } catch (error) {
    throw error;
  }
};

const checkNicknameAvailability = async (nickname) => {
  try {
    const response = await authService.get(`/api/v1/users/${nickname}`);
    console.log(response);
    return response.data.body.result;
  } catch (error) {
    throw error;
  }
};

const updateUserInfo = async (userInfo) => {
  try {
    const response = await authService.put(`/api/v1/users`, userInfo);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export { login, checkNicknameAvailability, updateUserInfo };

export default authService;
