/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            fontFamily: {
                sans: ['FiraCode', 'ui-sans-serif', 'system-ui'],
                fira: ['FiraCode', 'sans-serif'],
                orbitron: ['Orbitron', 'sans-serif'],
                fredoka: ['Fredoka', 'sans-serif'],
            },
            colors: {
                primary: '#ffffff',
                secondary: '#bd0000',
            },
        },
    },
    plugins: [],
}