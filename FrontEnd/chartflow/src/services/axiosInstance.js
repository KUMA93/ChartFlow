import axios from "axios"; // AxiosInstance 타입 추가
import { API_URL } from "../constants/url";

export const axiosServer = () => {
  const accessToken = localStorage.getItem("access-token");

// https://cors-anywhere.herokuapp.com/

  return axios.create({
    baseURL: `${API_URL}`,
    withCredentials: true,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "Authorization": `Bearer ${accessToken}`,
    },
  });
};

// refreshToken을 authorization으로 하는 axios 요청
export const axiosServerWithRefresh = () => {
  const refreshToken = localStorage.getItem("refresh-token");
  return axios.create({
    baseURL: `${API_URL}`,
    withCredentials: true,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "Authorization": `Bearer ${refreshToken}`,
    },
  });
};

// Token 없이 axios 요청
export const axiosServerWithoutToken = () => {
  return axios.create({
    baseURL: `${API_URL}`,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  });
};
