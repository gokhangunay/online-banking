package com.banking.user.services;

import com.banking.user.domain.PrimaryAccount;
import com.banking.user.domain.SavingsAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
}
