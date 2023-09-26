import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import GlobalStyle from "./styles/GlobalStyle";
import { ThemeProvider } from "./styles/themeProvider";
import UserContext from "./context/UserContext";
import { useState } from "react";
import MainPage from "./pages/MainPage";
import ChartGame from "./pages/ChartGame";
import Board from "./pages/Board";
import Quiz from "./pages/Quiz";
import History from "./pages/History";
import MyPage from "./pages/MyPage";
import Join from "./pages/Join";
import NotFound from "./pages/NotFound";
import QuizCorrect from "./pages/QuizCorrect";
import JoinComplete from "./pages/JoinComplete";
import Forget from "./pages/Forget";
import GameContext from "./context/GameContext";

function App() {
  const [accessToken, setAccessToken] = useState(() => {
    return localStorage.getItem("access-token");
  });

  const [refreshToken, setRefreshToken] = useState(() => {
    return localStorage.getItem("refresh-token");
  });

  const [gameId, setGameId] = useState(() => {
    const storedGameId = localStorage.getItem("gameId");
    return storedGameId;
  });

  const [thisTurn, setThisTurn] = useState(() => {
    const storedThisTurn = localStorage.getItem("thisTurn");
    return storedThisTurn;
  });

  return (
    <Router>
      <ThemeProvider>
        <UserContext.Provider
          value={{
            accessToken,
            setAccessToken,
            refreshToken,
            setRefreshToken,
          }}
        >
          <GameContext.Provider
            value={{ gameId, setGameId, thisTurn, setThisTurn }}
          >
            <GlobalStyle />
            <Routes>
              <Route path="/" element={<MainPage />} />
              <Route path="/game" element={<ChartGame />} />
              <Route path="/board/*" element={<Board />} />
              <Route path="/quiz/*" element={<Quiz />} />
              <Route path="/hist/*" element={<History />} />
              <Route path="/mypage" element={<MyPage />} />
              <Route path="/join" element={<Join />} />
              <Route path="/complete" element={<JoinComplete />} />
              <Route path="/forget" element={<Forget />} />
              <Route path="/*" element={<NotFound />} />
              <Route path="/quizcorrect" element={<QuizCorrect />} />
            </Routes>
          </GameContext.Provider>
        </UserContext.Provider>
      </ThemeProvider>
    </Router>
  );
}

export default App;
