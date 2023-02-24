import { defineConfig, createLogger } from 'vite'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 3000,
    hmr: {
      clientPort: parseInt(process.env.WEBSOCKET_PORT!),
    },
  },
  customLogger: createLogger('info', { prefix: '[coderpad]' }),
})