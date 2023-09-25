import axios from "axios"; // AxiosInstance 타입 추가
import Cookies from "js-cookie";

export const axiosServer = () => {
  const refreshToken = localStorage.getItem("refresh-token");
  const accessToken = localStorage.getItem("access-token");
  // console.log(accessToken);
  console.log(accessToken);
  return axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 10000,
    withCredentials: true,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "Authorization": `Bearer ${accessToken}`
    },
  });
};

// refreshToken을 authorization으로 하는 axios 요청
export const axiosServerWithRefresh = () => {
  return axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      // "Authorization": `Bearer ${refreshToken}`,
    },
  });
};

// refreshToken을 authorization으로 하는 axios 요청
export const axiosServerWithoutToekn = () => {
  return axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  });
};
