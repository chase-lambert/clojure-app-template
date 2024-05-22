/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./resources/public/js/cljs-runtime/*.js"],
  daisyui: {
    themes: ["light", "dark", "aqua", "synthwave", "retro"],
  },
  theme: {
    extend: {},
  },
  plugins: [require("@tailwindcss/forms"), require("daisyui")],
};
