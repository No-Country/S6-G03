/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {
      colors:{
        "blue-primary":"#275ECD",
        "green-secondary":"#1E9E69"        
      }
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}
