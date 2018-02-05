package com.banking.user.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gokhan.gunay on 2/5/2018.
 */
public class PrimaryAccount { // Ana Hesap

    private Long id;
    private Integer accountNumber; // Hesap Numarası
    private BigDecimal accountBalance; // Hesap Bakiyesi

    private List<PrimaryTransaction> primaryTransactionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<PrimaryTransaction> getPrimaryTransactionList() {
        return primaryTransactionList;
    }

    public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
        this.primaryTransactionList = primaryTransactionList;
    }

}
