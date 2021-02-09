package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface StudentCheckingAccountServiceInterface {
    StudentCheckingAccount createStudentCheckingAccount(StudentCheckingAccount studentCheckingAccount);
    StudentCheckingAccount updateStudentCheckingAccount(StudentCheckingAccount studentCheckingAccount);

}
