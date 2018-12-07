package com.banking.user.dao;

import com.banking.user.domain.PrimaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryAccountDao extends JpaRepository<PrimaryAccount, Long> {
    PrimaryAccount findByAccountNumber(Integer accountNumber);
}
