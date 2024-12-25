import { apiClient } from "./apiService";


export const getNewsService = async () => {
    return await apiClient.get("/new")
}


export const getNewIDService = async (id: number) => {
    return await apiClient.get(`/new/${id}`)
} 