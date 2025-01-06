import { apiClient } from "./apiService";


export const getProductsService = async () => {
    return await apiClient.get("/product")
}


export const getProductIDService = async (brand: string, name: string) => {
    return await apiClient.get(`/product/${brand}/${name}`)
} 