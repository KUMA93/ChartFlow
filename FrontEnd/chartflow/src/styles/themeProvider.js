import { createContext, useState, useContext, useCallback } from "react";
import { lightTheme, darkTheme } from "./theme";
import { ThemeProvider as StyledProvider } from "styled-components";

const ThemeContext = createContext({
  ThemeMode: "light",
  setThemeMode: () => {},
});

const ThemeProvider = ({ children }) => {
  const LocalTheme = window.localStorage.getItem("theme") || "light";
  const [ThemeMode, setThemeMode] = useState(LocalTheme);
  const themeObject = ThemeMode === "light" ? lightTheme : darkTheme;

  return (
    <ThemeContext.Provider value={{ ThemeMode, setThemeMode }}>
      <StyledProvider theme={themeObject}>{children}</StyledProvider>
    </ThemeContext.Provider>
  );
};

function useTheme() {
  const context = useContext(ThemeContext);
  const { ThemeMode, setThemeMode } = context;

  const toggleTheme = useCallback(() => {
    if (ThemeMode === "light") {
      setThemeMode("dark");
      window.localStorage.setItem("theme", "dark");
    } else {
      window.localStorage.setItem("theme", "light");
      setThemeMode("light");
    }
  }, [ThemeMode]);

  return [ThemeMode, toggleTheme];
}

export { ThemeProvider, useTheme };
export default ThemeProvider;
