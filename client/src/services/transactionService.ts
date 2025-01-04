import { TransactionPayload } from "../types/transactionPayload";
import { apiClient } from "./apiService";


export const postTransactionService = async (data: TransactionPayload) => {
    return await apiClient.post("/transaction", data, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    })
}


