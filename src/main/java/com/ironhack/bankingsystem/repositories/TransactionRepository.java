package com.ironhack.bankingsystem.repositories;

import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.accounts.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTransactionBySenderAndTimeStampBetween(Account sender, LocalDateTime secondAgo, LocalDateTime now);
}
