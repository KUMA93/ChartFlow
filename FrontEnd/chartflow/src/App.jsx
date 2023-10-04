import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import GlobalStyle from "./styles/GlobalStyle";
import { ThemeProvider } from "./styles/themeProvider";
import UserContext from "./context/UserContext";
import GameContext from "./context/GameContext";
import { useState } from "react";
import MainPage from "./pages/MainPage";
import ChartGame from "./pages/ChartGame";
import Board from "./pages/Board";
import Write from "./pages/Write";
import BoardOne from "./pages/BoardOne";
import Quiz from "./pages/Quiz";
import History from "./pages/History";
import MyPage from "./pages/MyPage";
import Join from "./pages/Join";
import NotFound from "./pages/NotFound";
import QuizCorrect from "./pages/QuizCorrect";
import QuizWrong from "./pages/QuizWrong";
import JoinComplete from "./pages/JoinComplete";
import Forget from "./pages/Forget";

function App() {
  const [isLogin, setIsLogin] = useState(
    () => localStorage.getItem("access-token") !== null
  );

  const [gameId, setGameId] = useState(() => {
    const storedGameId = localStorage.getItem("gameId");
    return storedGameId;
  });

  const [thisTurn, setThisTurn] = useState(() => {
    const storedThisTurn = localStorage.getItem("thisTurn");
    return storedThisTurn;
  });

  const [isSaved, setIsSaved] = useState(() => {
    const storedIsSaved = localStorage.getItem("isSaved");
    return storedIsSaved;
  });

  const [assetNum, setAssetNum] = useState("-");
  const [assetPer, setAssetPer] = useState("-");
  const [assetGap, setAssetGap] = useState("-");
  const [initNum, setInitNum] = useState("-");
  const [cashNum, setCashNum] = useState("-");
  const [stocksAmt, setStocksAmt] = useState("-");
  const [stocksNum, setStocksNum] = useState("-");
  const [avgPriceNum, setAvgPriceNum] = useState("-");
  const [curPriceNum, setCurPriceNum] = useState("-");
  const [coin, setCoin] = useState("-");
  const [flag, setFlag] = useState(true);

  return (
    <Router>
      <ThemeProvider>
        <UserContext.Provider
          value={{
            isLogin,
            setIsLogin,
          }}
        >
          <GameContext.Provider
            value={{
              gameId,
              setGameId,
              thisTurn,
              setThisTurn,
              assetNum,
              setAssetNum,
              assetPer,
              setAssetPer,
              assetGap,
              setAssetGap,
              initNum,
              setInitNum,
              cashNum,
              setCashNum,
              stocksAmt,
              setStocksAmt,
              stocksNum,
              setStocksNum,
              avgPriceNum,
              setAvgPriceNum,
              curPriceNum,
              setCurPriceNum,
              flag,
              setFlag,
              isSaved,
              setIsSaved,
              coin,
              setCoin,
            }}
          >
            <GlobalStyle />
            <Routes>
              <Route path="/" element={<MainPage />} />
              <Route path="/game" element={<ChartGame />} />
              <Route path="/board" element={<Board />} />
              <Route path="/board/new" element={<Write />} />
              <Route path="/board/:articleId" element={<BoardOne />} />
              <Route path="/quiz/*" element={<Quiz />} />
              <Route path="/hist/*" element={<History />} />
              <Route path="/mypage" element={<MyPage />} />
              <Route path="/join" element={<Join />} />
              <Route path="/complete" element={<JoinComplete />} />
              <Route path="/forget" element={<Forget />} />
              <Route path="/*" element={<NotFound />} />
              <Route path="/quiz/correct" element={<QuizCorrect />} />
              <Route path="/quiz/wrong" element={<QuizWrong />} />

            </Routes>
          </GameContext.Provider>
        </UserContext.Provider>
      </ThemeProvider>
    </Router>
  );
}

export default App;
