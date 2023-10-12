import { useState } from "react";

export function useInput(initialValue, submitAction) {
  const [inputValue, setInputValue] = useState(initialValue);

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  return [inputValue, handleChange, setInputValue];
}
