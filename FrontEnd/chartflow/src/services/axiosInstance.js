import axios from "axios"; // AxiosInstance 타입 추가
import Cookies from "js-cookie";

export const axiosServer = () => {
  const refreshToken = sessionStorage.get("refreshToken");
  const accessToken = sessionStorage.get("accessToken");
  // console.log(accessToken);

  return axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 10000,
    withCredentials: true,
    headers: {
      "Content-Type" : 'application/json',
      "Authorization": `Bearer ${accessToken}`,
    },
  });

};

// refreshToken을 authorization으로 하는 axios 요청
export const axiosServerWithRefresh = () => {

  return axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
      "Content-Type": 'application/json',
      "Authorization": `Bearer ${refreshToken}`,
    }
  });
}
