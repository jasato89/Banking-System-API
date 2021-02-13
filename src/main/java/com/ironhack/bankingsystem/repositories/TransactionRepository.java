package com.ironhack.bankingsystem.repositories;

import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.time.*;
import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTransactionBySenderAndTimeStampBetween(Account sender, LocalDateTime secondAgo, LocalDateTime now);
    @Query(value ="SELECT SUM(t.amount) FROM bankingsys.transactions t where sender_account_id = :id group by month(t.time_stamp) order by 1 desc limit 1", nativeQuery = true)
    BigDecimal getMaxByMonth(@Param("id") Long id);
}
