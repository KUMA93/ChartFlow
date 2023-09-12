import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import ChartGame from "./pages/ChartGame";
import GlobalStyle from "./styles/GlobalStyle";
import { ThemeProvider } from "./styles/themeProvider";
import MyPage from "./pages/MyPage";
import History from "./pages/History";
import Board from "./pages/Board";

function App() {
  return (
    <Router>
      <ThemeProvider>
        <GlobalStyle />
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/game" element={<ChartGame />} />
          <Route path="/hist" element={<History />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/boards" element={<Board />} />
        </Routes>
      </ThemeProvider>
    </Router>
  );
}

export default App;
