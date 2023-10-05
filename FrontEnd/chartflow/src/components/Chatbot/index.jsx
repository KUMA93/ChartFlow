import s from "./Chatbot.module.css"
import { useState } from 'react';

function Chatbot({ isOpen, closeModal}) {

  const [inputText, setInputText] = useState('');
  const [displayText, setDisplayText] = useState('');

  const handleInputChange = (event) => {
    setInputText(event.target.value);
  };

  const handleEnterPress = (event) => {
    if (event.key === 'Enter') {
      setDisplayText(inputText);
    }
  };

  return (
      <div style={{ display: isOpen ? "block": "none" }}>
        <div className={s.shade}>
          <div className={s.modalChat}>
            <div className={s.font}>
              무엇이 궁금한가요?
            </div>
            <div className={s.chatCon}>
              <div className={s.inputText}>
                {displayText}
              </div>
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

export default Chatbot