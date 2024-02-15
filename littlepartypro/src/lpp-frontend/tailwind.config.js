/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./public/index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        "lpp-primary": "#fffcf9",
        "lpp-text-primary": "#f2a9b6",
        "lpp-secondary": "#b3dcd7",
        "lpp-third": "#9d87a9",
        "lpp-four": "#fcdf81"
      },
      backgroundImage: {
        'hero-pattern': "url('/src/assets/background.png')",
      }
    },
    fontFamily: {
      Lato: ["Lato, sans-serif"],
      Arimo: ["Arimo, sans-serif"],
      Bellaboo: ["Bellaboo, serif"],
    },
    container: {
      padding: "2rem",
      center: true,
    },
    screens: {
      sm: "640px",
      md: "768px"
    }
  },
  plugins: [

  ],
}

