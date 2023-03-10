/** @type {import('tailwindcss').Config} */
const defaultTheme = require("tailwindcss/defaultTheme");
module.exports = {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    screens: {
      xs: "360px",
      ...defaultTheme.screens,
    },
    extend: {
      colors: {
        "blue-primary": "#275ECD",
        "blue-primary-80": "#275ECD",
        "green-secondary": "#1E9E69",
        "green-secondary-30": "#1E9E69",
        "green-secondary-70": "#2BB77D",
        "green-secondary-80": "#1E9E69",
        "neutral-30": "#5F6060",
        "neutral-60": "#B6B8B8",
        "neutral-80": "#DEDFDF",
      },
      fontFamily: {
        roboto: ["Roboto", "sans-serif"],
      },
    },
  },
  plugins: [],
};
