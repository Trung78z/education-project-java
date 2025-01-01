package com.hcmuss.__admin.utils;

import java.util.List;

public class JsonObjectResponse<T> {
    private boolean success;
    private int status;
    private T message;
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

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
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