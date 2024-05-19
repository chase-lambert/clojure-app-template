module.exports = {
  content: ["./resources/public/js/main.js"],
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
