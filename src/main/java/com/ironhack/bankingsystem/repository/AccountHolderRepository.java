package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
