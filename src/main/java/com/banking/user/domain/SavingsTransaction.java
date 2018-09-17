package com.banking.user.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gokhan.gunay on 2/5/2018.
 */
@Entity
public class SavingsTransaction { // Birikim İşlemleri

    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    private String description;
    private String type;
    private String status;
    private Double amount; // Miktar
    private BigDecimal availableBalance; // Kalan Bakiye

    @ManyToOne
    @JoinColumn(name = "SAVINGS_ACCOUNT_ID")
    private SavingsAccount savingsAccount; // Yatırım Hesabı

    public SavingsTransaction(){}

    public SavingsTransaction(Date date, String description, String type, String status, Double amount, BigDecimal availableBalance, SavingsAccount savingsAccount) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.savingsAccount = savingsAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
