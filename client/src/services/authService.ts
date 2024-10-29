import { apiClient } from "./apiService";

export const getAuth = async () => {
  return apiClient.get("/", {
    withCredentials: true,
  });
};
