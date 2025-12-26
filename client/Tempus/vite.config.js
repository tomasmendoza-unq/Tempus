import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import process from "process";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": process.env.VITE_API_URL,
    },
    open: true,
  },
});
