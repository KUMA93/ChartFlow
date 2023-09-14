import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import ChartGame from "./pages/ChartGame";
import GlobalStyle from "./styles/GlobalStyle";
import { ThemeProvider } from "./styles/themeProvider";
import TurnContext from "./context/TurnContext";
import { useState } from "react";
import MyPage from "./pages/MyPage";
import History from "./pages/History";
import Board from "./pages/Board";
import Signup from "./pages/Signup";

function App() {
  const [thisTurn, setThisTurn] = useState(1);
  return (
    <Router>
      <ThemeProvider>
        <TurnContext.Provider value={{ thisTurn, setThisTurn }}>
          <GlobalStyle />
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/game" element={<ChartGame />} />
            <Route path="/hist" element={<History />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/boards" element={<Board />} />
            <Route path="/signup" element={<Signup />} />
          </Routes>
        </TurnContext.Provider>
      </ThemeProvider>
    </Router>
  );
}

export default App;
