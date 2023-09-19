import { useTheme } from "../../hooks/useTheme";
import * as S from "./Toggle.styled.js";

const Toggle = () => {
  const [ThemeMode, toggleTheme] = useTheme();

  function ThemeToggle({ toggle, mode }) {
    return (
      <S.ToggleWrapper onClick={toggle} mode={mode}>
        {mode === "dark" ? "🌚" : "🌝"}
      </S.ToggleWrapper>
    );
  }

  return <ThemeToggle toggle={toggleTheme} mode={ThemeMode}></ThemeToggle>;
};

export default Toggle;
