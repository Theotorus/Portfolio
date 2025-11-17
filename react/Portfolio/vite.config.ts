import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react-swc'
import * as path from "node:path";

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    resolve: {
        alias: {
            '@app': path.resolve(__dirname, 'src/app'),
            '@data': path.resolve(__dirname, 'src/data'),
        }
    }
})