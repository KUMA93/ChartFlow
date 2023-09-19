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

function App() {
  return (
    <Router>
      <ThemeProvider>
        {/* <UserContext.Provider value={{}}></UserContext.Provider> */}
        <GlobalStyle />
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/game" element={<ChartGame />} />
          <Route path="/board/*" element={<Board />} />
          <Route path="/quiz/*" element={<Quiz />} />
          <Route path="/hist/*" element={<History />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/join" element={<Join />} />
          <Route path="/join" element={<Join />} />
        </Routes>
      </ThemeProvider>
    </Router>
  );
}

export default App;
