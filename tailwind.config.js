/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        coffee: {
          200: "#eee1c4",
          300: "#FFE0B5",
          500: "#dcb386",
          700: "#AF8260", //hover
        },
      },
      fontFamily: {
        sans: ["Inter", "sans"],
        serif: ["serif"],
      },
      keyframes: {
        fadeIn: {
          "0%": { opacity: "0" },
          "100%": { opacity: "1" },
        },
        moveInDown10: {
          "0%": { transform: "translateY(-10%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInDownFull: {
          "0%": { transform: "translateY(-100%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInUp10: {
          "0%": { transform: "translateY(10%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInUpFull: {
          "0%": { transform: "translateY(100%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInLeft10: {
          "0%": { transform: "translateX(10%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInLeftFull: {
          "0%": { transform: "translateX(100%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInRight10: {
          "0%": { transform: "translateX(-10%)" },
          "100%": { transform: "translateX(0)" },
        },
        moveInRightFull: {
          "0%": { transform: "translateX(-100%)" },
          "100%": { transform: "translateX(0)" },
        },
      },
      animation: {
        showDown: "fadeIn .25s ease-out, moveInDown10 .25s ease-out",
        showUp: "fadeIn .25s ease-out, moveInUp10 .25s ease-out",
        moveInDownFull: "fadeIn .5s ease-out, moveInDownFull .5s ease-out",
        moveInUpFull: "fadeIn .5s ease-out, moveInUpFull .5s ease-out",
        moveInLeftFull: "fadeIn .5s ease-out, moveInLeftFull .5s ease-out",
        moveInRightFull: "fadeIn .5s ease-out, moveInRightFull .5s ease-out",
      },
    },
  },
  plugins: [],
};
