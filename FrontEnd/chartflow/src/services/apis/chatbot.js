import { axiosServer, axiosServerWithoutToken } from "../axiosInstance";

//챗봇
export const chatbot = async (request) => {
    const res = await axiosServerWithoutToken().post(`/chatbot`,request);
    const data = res.data;
    const content = data.choices[0].message.content; // content만 추출
    return content;
  };
  //chatgpt response에 잡다한 데이터 많아 응답한 텍스트만 파싱해서 보냄