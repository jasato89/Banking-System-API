package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.models.account.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}