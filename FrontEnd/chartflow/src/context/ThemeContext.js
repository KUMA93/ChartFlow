import { createContext } from "react";

const ThemeContext = createContext({
  ThemeMode: "light",
  setThemeMode: () => {},
});

export default ThemeContext;
