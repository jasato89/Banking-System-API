package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.math.*;
import java.time.*;
import java.time.temporal.*;

@Service
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    //TODO
    public Money sendMoney(UserDetails userDetails, TransactionDTO transactionDTO) {

        if (accountsArePresent(transactionDTO)) {

            Account senderAccount = evaluateAccounts(getSenderAccount(transactionDTO), transactionDTO);
            Account recipientAccount = evaluateAccounts(getRecipientAccount(transactionDTO), transactionDTO);

            if (accountHasPermissions(senderAccount, userDetails)) {


                if (accountHasEnoughBalance(senderAccount, transactionDTO.getAmount())) {
                    senderAccount.setBalance(new Money(senderAccount.getBalance().getAmount().subtract(transactionDTO.getAmount().getAmount()), senderAccount.getBalance().getCurrency()));
                    recipientAccount.setBalance(new Money(recipientAccount.getBalance().getAmount().add(transactionDTO.getAmount().getAmount()), recipientAccount.getBalance().getCurrency()));
                    transactionRepository.save(new Transaction(senderAccount, recipientAccount, transactionDTO.getAmount()));
                }


            } else {

                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have rights to make transfers from this account");

            }

        } else {
            if (!accountRepository.findById(transactionDTO.getSenderAccountId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, but the account you are trying to transfer funds from does not exist in the database");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, but the account you are trying to transfer funds to does not exist in the database");
            }

        }


        return null;
    }

    private Account evaluateAccounts(Account account, TransactionDTO transactionDTO) {

        if (account instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) account;
            checkStatus(checkingAccount);
            applyMonthlyFee(checkingAccount);
            if (checkingAccount.getAccountId().equals(transactionDTO.getSenderAccountId())) {
                if (checkingAccount.getMinimumBalance().getAmount().compareTo(checkingAccount.getBalance().getAmount().subtract(transactionDTO.getAmount().getAmount())) > 0) {
                    checkingAccount = (CheckingAccount) applyPenaltyFee(checkingAccount);
                }
            }
            return checkingAccountRepository.save(checkingAccount);

        } else if (account instanceof StudentCheckingAccount) {
            StudentCheckingAccount studentCheckingAccount = (StudentCheckingAccount) account;
            checkStatus(studentCheckingAccount);

        } else if (account instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) account;


        } else if (account instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) account;
            checkStatus(savingsAccount);

        }
        return account;
    }



    private Account applyPenaltyFee(Account checkingAccount) {
        checkingAccount.setBalance(new Money(checkingAccount.getBalance().getAmount().subtract(Constants.PENALTY_FEE)));
        return checkingAccount;
    }

    private void applyMonthlyFee(CheckingAccount checkingAccount) {
        Long monthsBetween = ChronoUnit.MONTHS.between(checkingAccount.getMaintenanceFeeLastTimeApplied(), LocalDateTime.now());


        checkingAccount.setBalance(new Money(
                checkingAccount.getBalance().getAmount()
                        .subtract(checkingAccount.getMonthlyMaintenanceFee().getAmount().multiply(new BigDecimal(monthsBetween))),
                checkingAccount.getBalance().getCurrency()));

        checkingAccount.setMaintenanceFeeLastTimeApplied(LocalDateTime.now());
        accountRepository.save(checkingAccount);


    }

    private void checkStatus(SavingsAccount savingsAccount) {
        if (savingsAccount.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account " + savingsAccount.getAccountId() + " is suspended");
        }

    }

    private void checkStatus(StudentCheckingAccount studentCheckingAccount) {
        if (studentCheckingAccount.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account " + studentCheckingAccount.getAccountId() + " is suspended");
        }

    }

    private void checkStatus(CheckingAccount checkingAccount) {
        if (checkingAccount.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account " + checkingAccount.getAccountId() + " is suspended");
        }
    }


    private boolean checkFraud(Account senderAccount) {
        return true;
    }

    private boolean accountHasEnoughBalance(Account senderAccount, Money amount) {

        return senderAccount.getBalance().getAmount().compareTo(amount.getAmount()) > 0;

    }

    private Account getRecipientAccount(TransactionDTO transactionDTO) {
        return accountRepository.findById(transactionDTO.getRecipientAccountId()).get();
    }

    private Account getSenderAccount(TransactionDTO transactionDTO) {
        return accountRepository.findById(transactionDTO.getSenderAccountId()).get();
    }

    private boolean accountsArePresent(TransactionDTO transactionDTO) {
        return accountRepository.findById(transactionDTO.getSenderAccountId()).isPresent()
                && accountRepository.findById(transactionDTO.getRecipientAccountId()).isPresent();
    }

    private boolean accountHasPermissions(Account senderAccount, UserDetails userDetails) {

        return senderAccount.getAccountHolder().getUsername().equals(userDetails.getUsername()) ||
                senderAccount.getSecondaryAccountHolder().getUsername().equals(userDetails.getUsername());

    }
}
