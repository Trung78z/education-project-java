package com.api.backend.dto.transaction;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Integer productId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime transactionDate;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer productId, int quantity, double totalPrice, LocalDateTime transactionDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionDate = transactionDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}