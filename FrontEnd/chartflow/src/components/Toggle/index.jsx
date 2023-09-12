import { useTheme } from "../../styles/themeProvider";
import ThemeToggle from "../../styles/ThemeToggle";

const Toggle = () => {
  const [ThemeMode, toggleTheme] = useTheme();
  return <ThemeToggle toggle={toggleTheme} mode={ThemeMode}></ThemeToggle>;
};

export default Toggle;
