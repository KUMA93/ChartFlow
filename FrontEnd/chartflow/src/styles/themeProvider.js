import { useState } from "react";
import { lightTheme, darkTheme } from "./theme";
import { ThemeProvider as StyledProvider } from "styled-components";
import ThemeContext from "../context/ThemeContext";

export const ThemeProvider = ({ children }) => {
  const LocalTheme = window.localStorage.getItem("theme") || "light";
  const [ThemeMode, setThemeMode] = useState(LocalTheme);

  const themeObject = ThemeMode === "light" ? lightTheme : darkTheme;

  return (
    <ThemeContext.Provider value={{ ThemeMode, setThemeMode }}>
      <StyledProvider theme={themeObject}>{children}</StyledProvider>
    </ThemeContext.Provider>
  );
};
