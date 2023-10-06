import s from "./Chatbot.module.css";
import { useState, useEffect } from 'react';
import { chatbot } from "../../services/apis/chatbot";

function Chatbot({ isOpen, closeModal }) {
  const [inputText, setInputText] = useState('');
  const [chatHistory, setChatHistory] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const handleInputChange = (event) => {
    setInputText(event.target.value);
  };

  const handleEnterPress = async (event) => {
    if (event.key === 'Enter') {
      const newChatHistory = [...chatHistory, { type: 'user', text: inputText }];
      setChatHistory(newChatHistory);
      setInputText('');
      setIsLoading(true);

      // API 호출
      const response = await chatbot({ text: inputText });
      setIsLoading(false);

      setChatHistory([...newChatHistory, { type: 'bot', text: response }]);
    }
  };

  return (
    <div style={{ display: isOpen ? "block": "none" }}>
      <div className={s.shade}>
        <div className={s.modalChat}>
          {isLoading && <div className={s.loading}>Loading...</div>}
          <div className={s.chatCon}>
            {chatHistory.map((chat, index) => (
              <div key={index} className={chat.type === 'user' ? s.userText : s.botText}>
                {chat.text}
              </div>
            ))}
          </div>
          <input 
            type="text"
            className={s.chat}
            value={inputText}
            onChange={handleInputChange}
            onKeyDown={handleEnterPress}
          />
          <button className={s.closeBtn} onClick={closeModal}>닫기</button>
        </div>
      </div>
    </div>
  );
}

export default Chatbot;