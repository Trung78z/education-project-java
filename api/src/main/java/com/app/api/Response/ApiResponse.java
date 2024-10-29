package com.app.api.Response;

public class ApiResponse<T> {
    private int status;
    private T message;
    private String error;

    public ApiResponse() {
    }

    public ApiResponse(int status, T message) {
        this.status = status;
        this.message = message;

    }

    public ApiResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }

    // Getters và Setters
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
