import {defineConfig, UserConfig} from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

const config: UserConfig = {
  plugins: [ react(), tailwindcss() ],
  resolve: {
    alias: {
      '@': '/src',
    },
  },
};

export default defineConfig(env => {
  if (env.mode === 'development') {
    return {
      ...config,
      server: {
        proxy: {
          '/api': {
            target: 'http://localhost:5001',
            changeOrigin: true,
            secure: false,
          },
        },
        port: 3000
      },
    };
  } else return config;
});
