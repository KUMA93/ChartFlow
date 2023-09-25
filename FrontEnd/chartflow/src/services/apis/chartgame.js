import { axiosServer } from "../axiosInstance";

// 게임 시작하기
export const startGame = async () => {
    const res = await axiosServer().post(`/game`);
    return res.data;
}

// 최초 게임 불러오기
export const loadGame = async () => {
    const res = await axiosServer().get(`/game`);
    console.log("loadgame response: " + res);
    return res.data;
}

// 행동 후 게임 히스토리 불러오기
export const loadGameHistory = async () => {
    const res = await axiosServer().get(`/game/history`);
    return res.data;
}

// 매수, 매도, 스킵, 종료 - 0, 1, 2, 3 
export const progressGame = async (requestData) => {
    const res = await axiosServer().put(`/game`, requestData);
    return res.data;
}