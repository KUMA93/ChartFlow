import axiosInstance from "./axiosInstance";

// const authService = axiosInstance();

// const emailVerify = async (email) => {
//   try {
//     const response = await authService.post("api/user/auth", {
//       email: email.toString(), // email을 문자열(string)로 변환
//     });
//     console.log(response);
//     return response;
//   } catch (error) {
//     throw error;
//   }
// };

// const checkNicknameAvailability = async (nickname) => {
//   try {
//     const response = await authService.get(`/api/user/auth`);
//     console.log(response);
//     return response.data.body.result;
//   } catch (error) {
//     throw error;
//   }
// };

// const updateUserInfo = async (userInfo) => {
//   try {
//     const response = await authService.put(`/api/v1/users`, userInfo);
//     return response.data;
//   } catch (error) {
//     throw error;
//   }
// };

export { emailVerify };

export default authService;
