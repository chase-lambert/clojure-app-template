const defaultTheme = require("tailwindcss/defaultTheme");

module.exports = {
  content:
    process.env.NODE_ENV == "production"
      ? ["./resources/public/js/main.js"]
      : ["./resources/public/js/cljs-runtime/*.js"],
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
