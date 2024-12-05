import axios from "axios";
export const API_URL = import.meta.env.VITE_BASE_URL;
export const apiClient = axios.create({
  baseURL: `${API_URL}/api`,
  headers: { "Content-Type": "application/json" },
});
