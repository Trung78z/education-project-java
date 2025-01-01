package com.api.backend.utils;

public class ResponseWrapper<T> {
    private boolean success;
    private Integer status;
    private T message;
    private String error;

    public ResponseWrapper() {
    }

    public ResponseWrapper(boolean success, Integer status, T message) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.error = null;
    }

    // Constructor cho trường hợp thất bại mà chỉ có status
    public ResponseWrapper(Integer status) {
        this.success = false;
        this.status = status;
        this.message = null;
        this.error = null;
    }

    // Constructor cho trường hợp lỗi
    public ResponseWrapper(Integer status, String error) {
        this.success = false;
        this.status = status;
        this.message = null;
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
