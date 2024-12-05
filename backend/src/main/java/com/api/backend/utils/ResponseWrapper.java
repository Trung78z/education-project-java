package com.api.backend.utils;

public class ResponseWrapper<T> {
    private boolean success;
    private Integer status;
    private T data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(boolean success, Integer status, T data) {
        this.success = success;
        this.status = status;
        this.data = data;
    }

    public ResponseWrapper(Integer status, T data) {
        this.success = false;
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
