import React, { createContext, useContext, useState, useEffect } from "react";
import { startGame, loadGame, loadGameHistory, progressGame } from "../../services/apis/chartgame";

const GameContext = createContext();

export const useGameContext = () => {
  return useContext(GameContext);
};

export const GameProvider = ({ children }) => {
  const [gameData, setGameData] = useState(null);

  // API 호출 및 데이터 업데이트 로직

  const startNewGame = async () => {
    try {
      const data = await startGame();
      setGameData(data);
    } catch (error) {
      console.error("Error starting a new game", error);
    }
  };

  // 다른 API 호출 함수들도 비슷한 방식으로 구현

  return (
    <GameContext.Provider value={{ gameData, startNewGame }}>
      {children}
    </GameContext.Provider>
  );
};
