import { apiClient } from "./apiService";


export const getNewsService = async () => {
    return await apiClient.get("/new")
}


export const getNewIDService = async (category: string, name: string) => {
    return await apiClient.get(`/new/${category}/${name}`)
} 