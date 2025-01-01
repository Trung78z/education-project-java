package com.hcmuss.__admin.utils;
import java.util.List;

public class JsonResponse<T> {
    private boolean success;
    private int status;
    private List<T> message;
    private Object error;

    // Getters và Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getMessage() {
        return message;
    }

    public void setMessage(List<T> message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseGeneric{success=" + success + ", status=" + status + ", message=" + message + ", error=" + error + "}";
    }
}