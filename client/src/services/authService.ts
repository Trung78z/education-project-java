import { AuthChange, AuthLogin, AuthRegister, } from "../types/authPayload";
import { apiClient } from "./apiService";

export const getLogin = async (data: AuthLogin) => {
  return apiClient.post("/auth/login", data, {
    withCredentials: true,
  });
};

export const getAuth = async () => {
  return apiClient.get("/auth/check-token", {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    }
  });
};
export const postAuth = async (data: AuthRegister) => {
  return apiClient.post("/users", data, {
    withCredentials: true,
  });
};

export const postChangeAuth = async (data: AuthChange) => {
  return apiClient.post("/auth/change-password", data, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    }
  });
};
