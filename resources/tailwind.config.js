const defaultTheme = require("tailwindcss/defaultTheme");

module.exports = {
  content: ["./src/client/**/*.cljs"],
  daisyui: {
    themes: ["light", "dark", "aqua", "synthwave", "retro"],
  },
  theme: {
    extend: {
      fontFamily: {
        sans: ["Inter var", ...defaultTheme.fontFamily.sans],
      },
    },
  },
  plugins: [require("@tailwindcss/forms"), require("daisyui")],
};
