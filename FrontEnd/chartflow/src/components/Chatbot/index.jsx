import { useTheme } from "../../hooks/useTheme";
import * as S from "./Chatbot.styled.js";
import chatBot from "../../assets/images/chatbot.png"
import styles from "./Chatbot.module.css"

const Chatbot = () => {
  const [ThemeMode, ChatbotTheme] = useTheme();

  function ThemeChatbot({ chatbot, chat }) {
    return (
      <S.ChatbotWrapper onClick={chatbot} mode={chat}>
        <img src={chatBot} alt="" className={styles.img}/>
        <div></div>
      </S.ChatbotWrapper>
    );
  }
  
  return <ThemeChatbot toggle={ChatbotTheme} mode={ThemeMode}></ThemeChatbot>;
};

export default Chatbot;