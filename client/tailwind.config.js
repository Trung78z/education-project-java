/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: ["class"],
  content: [
    "./pages/**/*.{js,tsx}",
    "./components/**/*.{js,tsx}",
    "./app/**/*.{js,tsx}",
    "./src/**/*.{js,tsx}",
  ],
  prefix: "",
  theme: {
    container: {
      center: true,
      padding: "2rem",
      screens: {
        "2xl": "1400px",
      },
    },
    extend: {
      colors: {
        border: "hsl(var(--border))",
        input: "hsl(var(--input))",
        ring: "hsl(var(--ring))",
        background: "hsl(var(--background))",
        foreground: "hsl(var(--foreground))",
        primary: {
          DEFAULT: "#2B346F",
          50: "#EAEBF1",
          100: "#BDC0D2",
          200: "#9DA2BD",
          300: "#71779F",
          400: "#555D8C",
          500: "#2B346F",
          600: "#272F65",
          700: "#1F254F",
          800: "#181D3D",
          900: "#12162F",
        },
        secondary: {
          DEFAULT: "#E8C745",
          50: "#FDF9EC",
          100: "#F8EEC5",
          200: "#F4E5A9",
          300: "#F0D982",
          400: "#EDD26A",
          500: "#E8C745",
          600: "#D3B53F",
          700: "#A58D31",
          800: "#806D26",
          900: "#61541D",
        },
        neutral: {
          DEFAULT: "#FFFFFF",
          CED4DA: "#CED4DA",
          "555F6D": "#555F6D",
          F5F5F5: "#F5F5F5",
          TEXT: "TEXT",
        },
        surface: {
          DEFAULT: "#E05F3E",
          FFF9F1: "FFF9F1",
        },
        infor: {
          DEFAULT: "#5271FF",
          success: "#17C37E",
          warning: "#FFDC3A",
          error: "#FF0000",
        },
      },
      borderRadius: {
        lg: "var(--radius)",
        md: "calc(var(--radius) - 2px)",
        sm: "calc(var(--radius) - 4px)",
      },
      keyframes: {
        "accordion-down": {
          from: { height: "0" },
          to: { height: "var(--radix-accordion-content-height)" },
        },
        "accordion-up": {
          from: { height: "var(--radix-accordion-content-height)" },
          to: { height: "0" },
        },
        marquee: {
          "0%": { transform: "translateX(100%)" },
          "100%": { transform: "translateX(-150%)" },
        },
      },
      animation: {
        "accordion-down": "accordion-down 0.2s ease-out",
        "accordion-up": "accordion-up 0.2s ease-out",
        marquee: "marquee 45s linear infinite",
      },
    },
  },
  plugins: [require("tailwindcss-animate")],
};
