package com.app.server.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Component
public class Profile{
    @Id
    @SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "profile_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String bankAccountNumber;
    private Long total;
    private String bankAccountType;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String bankAccountNumber, Long total, String bankAccountType,
                   LocalDate createdAt, LocalDate updatedAt, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccountNumber = bankAccountNumber;
        this.total = total;
        this.bankAccountType = bankAccountType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Profile(Long id, String firstName, String lastName, String bankAccountNumber, Long total,
                   String bankAccountType,
                   LocalDate createdAt, LocalDate updatedAt, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccountNumber = bankAccountNumber;
        this.total = total;
        this.bankAccountType = bankAccountType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", total=" + total +
                ", bankAccountType='" + bankAccountType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}
