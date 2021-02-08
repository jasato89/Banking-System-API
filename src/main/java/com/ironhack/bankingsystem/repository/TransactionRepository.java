package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
