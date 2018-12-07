package com.banking.user.dao;

import com.banking.user.domain.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountDao extends JpaRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(Integer accountNumber);
}
