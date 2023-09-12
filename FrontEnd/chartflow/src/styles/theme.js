export const fontSize = {
  xs: "0.5rem",
  sm: "0.75rem",
  base: "1rem",
  md: "1.25rem",
  lg: "1.5rem",
};

const palette = {
  /* blue */
  blue_100: "#F0E5FF",
  blue_200: "#6C00FF",

  /* green */
  green_200: "#8EFFA7",

  /* neutral */
  white: "#ffffff",
  grey_200: "#ECECEC",
  grey_300: "#c6c6c6",
  grey_400: "#a9a9a9",
  grey_500: "#707070",
  grey_600: "#5a5a5a",
  grey_700: "#484848",
  grey_800: "#373737",
  grey_900: "#212121",
  black: "#000000",
};

export const lightTheme = {
  bg: palette.grey_200,
  font: {
    deep: palette.black,
    mid: palette.grey_400,
    light: palette.grey_200,
  },
  point: {
    lightblue: palette.blue_100,
    blue: palette.blue_200,
    green: palette.green_200,
  },
  hover: "#00a0ff50",
};

export const darkTheme = {
  bg: palette.grey_800,
  font: {
    deep: palette.white,
    mid: palette.grey_400,
    light: palette.grey_200,
  },
  point: {
    lightblue: palette.blue_100,
    blue: palette.blue_200,
    green: palette.green_200,
  },
  hover: "#00a0ff50",
};

const theme = {
  fontSize,
  lightTheme,
  darkTheme,
};

export default theme;
