import ThemeContext from "../context/ThemeContext";
import { useContext, useCallback } from "react";

export function useTheme() {
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
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [ThemeMode]);

  return [ThemeMode, toggleTheme];
}
