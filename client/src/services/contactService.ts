import { ContactPayload } from "../types/contactPayload";
import { apiClient } from "./apiService";


export const postContactService = async (data: ContactPayload) => {
    return await apiClient.post("/contact", data)
}

