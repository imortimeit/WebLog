//TailwindCSS 的配置文件，用于定制化 Tailwind 的默认设置。通过这个文件，你可以自定义框架的颜色、字体、断点、间距等，或者扩展 TailwindCSS 的功能。
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')
  ],
}

