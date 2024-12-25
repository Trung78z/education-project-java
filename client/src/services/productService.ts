import { apiClient } from "./apiService";


export const getProductsService = async () => {
    return await apiClient.get("/product")
}


export const getProductIDService = async (id: number) => {
    return await apiClient.get(`/product/${id}`)
} 