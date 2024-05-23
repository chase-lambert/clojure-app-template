const { scanClojure } = require("@multiplyco/tailwind-clj");
module.exports = {
  content: {
    files: ["./src/client/**/*.cljs"],
    extract: {
      cljs: (content) => scanClojure(content),
    },
  },
  daisyui: {
    themes: ["light", "dark", "aqua", "synthwave", "retro"],
  },
  theme: {
    extend: {},
  },
  plugins: [require("@tailwindcss/forms"), require("daisyui")],
};
