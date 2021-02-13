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
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    TransactionDTO transactionDTO;

    public static CreditCard applyInterestRate(CreditCard creditCard) {
        Long monthsBetween = ChronoUnit.MONTHS.between(creditCard.getLastInterestApplied(), LocalDateTime.now());
        if (monthsBetween > 0 && creditCard.getBalance().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            creditCard.setBalance(
                    new Money(creditCard.getBalance().getAmount().add(creditCard.getBalance().getAmount()
                            .multiply(new BigDecimal(monthsBetween))
                            .multiply(
                                    creditCard.getInterestRate()
                                            .divide(new BigDecimal("12"))))));
            creditCard.setLastInterestApplied(LocalDateTime.now());

        }

        return creditCard;
    }

    public Money transferMoney(UserDetails userDetails, TransactionDTO transactionDTO) {

        this.transactionDTO = transactionDTO;

        if (accountsArePresent()) {

            Account senderAccount = getSenderAccount();

            if (accountHasPermissions(senderAccount, userDetails)) {
                senderAccount = evaluateAccounts(senderAccount);
                Account recipientAccount = evaluateAccounts(getRecipientAccount());
                makeTransaction(transactionDTO, senderAccount, recipientAccount);
                return transactionDTO.getTransactionAmount();

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

    }

    private void makeTransaction(TransactionDTO transactionDTO, Account senderAccount, Account recipientAccount) {
        senderAccount.setBalance(new Money(senderAccount.getBalance().getAmount().subtract(transactionDTO.getTransactionAmount().getAmount()), senderAccount.getBalance().getCurrency()));
        recipientAccount.setBalance(new Money(recipientAccount.getBalance().getAmount().add(transactionDTO.getTransactionAmount().getAmount()), recipientAccount.getBalance().getCurrency()));
        transactionRepository.save(new Transaction(senderAccount, recipientAccount, transactionDTO.getTransactionAmount()));
    }

    private Account evaluateAccounts(Account account) {

        if (account instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) account;
            checkStatus(checkingAccount);
            if (isSender(checkingAccount)) {
                checkFraud(checkingAccount);
            }
            applyMonthlyFee(checkingAccount);
            checkBalanceAndApplyExtraFees(checkingAccount);
            return checkingAccount;

        } else if (account instanceof StudentCheckingAccount) {
            StudentCheckingAccount studentCheckingAccount = (StudentCheckingAccount) account;
            checkStatus(studentCheckingAccount);
            if (isSender(studentCheckingAccount)) {
                checkFraud(studentCheckingAccount);
                if (!enoughFunds(studentCheckingAccount)) {
                    saveAccount(studentCheckingAccount);
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sorry, but the account you are trying to transfer funds from does not have enough funds to perform this transaction");
                } else {
                    return studentCheckingAccount;

                }
            } else {
                return studentCheckingAccount;
            }

        } else if (account instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) account;
            applyInterestRate(creditCard);
            checkFraud(creditCard);


        } else if (account instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) account;
            checkStatus(savingsAccount);
            checkFraud(savingsAccount);


        }
        return account;
    }

    private boolean isSender(Account account) {
        return account.getAccountId().equals(transactionDTO.getSenderAccountId());

    }

    private void checkFraud(CreditCard creditCard) {
        if (transactionRepository.findTransactionBySenderAndTimeStampBetween((Account) creditCard, LocalDateTime.now().minusSeconds(1), LocalDateTime.now()).isPresent()) {
            saveAccount(creditCard);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Transaction rejected: You cannot transfer money now due to a potential fraud detected");

        }
    }

    private void checkBalanceAndApplyExtraFees(CheckingAccount checkingAccount) {
        if (checkingAccount.getAccountId().equals(transactionDTO.getSenderAccountId())) {
            if (dropsBelowMinimumBalance(checkingAccount)) {
                if (!enoughFunds(checkingAccount)) {
                    saveAccount(checkingAccount);
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sorry, but the account you are trying to transfer funds from does not have enough funds to perform this transaction");
                } else {
                    applyPenaltyFee(checkingAccount);
                }
            }
        }
    }

    private boolean enoughFunds(Account checkingAccount) {
        return checkingAccount.getBalance().getAmount().subtract(transactionDTO.getTransactionAmount().getAmount()).compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean dropsBelowMinimumBalance(CheckingAccount checkingAccount) {
        return checkingAccount.getMinimumBalance().getAmount().compareTo(checkingAccount.getBalance().getAmount().subtract(transactionDTO.getTransactionAmount().getAmount())) > 0;
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
        saveAccount(checkingAccount);


    }

    private void checkStatus(Penalizable savingsAccount) {
        if (savingsAccount.getStatus().equals(Status.FROZEN)) {
            saveAccount((Account) savingsAccount);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account " + savingsAccount.getAccountId() + " is suspended");
        }

    }


    private void checkFraud(Penalizable senderAccount) {
        if (transactionRepository.findTransactionBySenderAndTimeStampBetween((Account) senderAccount, LocalDateTime.now().minusSeconds(1), LocalDateTime.now()).isPresent()) {
            senderAccount.setStatus(Status.FROZEN);
            saveAccount((Account) senderAccount);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Transaction rejected: Your account is now frozen due to a potential fraud detected");
        }

        if (transactionRepository.getMaxByDay(senderAccount.getAccountId()).isPresent() && transactionRepository.getSumLastTransactions(senderAccount.getAccountId()).isPresent()) {

            BigDecimal getMaxByDay = transactionRepository.getMaxByDay(senderAccount.getAccountId()).get().multiply(new BigDecimal("1.5"));
            BigDecimal getLastDay = transactionRepository.getSumLastTransactions(senderAccount.getAccountId()).get();

            if (getMaxByDay.compareTo(getLastDay) < 0) {

                senderAccount.setStatus(Status.FROZEN);
                saveAccount((Account) senderAccount);
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Transaction rejected: Your account is now frozen due to a potential fraud detected");
            }

        }

    }

    private void saveAccount(Account account) {
        if (account instanceof CheckingAccount) {
            checkingAccountRepository.save((CheckingAccount) account);
        } else if (account instanceof StudentCheckingAccount) {
            studentCheckingAccountRepository.save((StudentCheckingAccount) account);
        } else if (account instanceof CreditCard) {
            creditCardRepository.save((CreditCard) account);
        } else if (account instanceof SavingsAccount) {
            savingsAccountRepository.save((SavingsAccount) account);
        }

    }


    private Account getRecipientAccount() {
        return accountRepository.findById(transactionDTO.getRecipientAccountId()).get();
    }

    private Account getSenderAccount() {
        return accountRepository.findById(transactionDTO.getSenderAccountId()).get();
    }

    private boolean accountsArePresent() {
        return accountRepository.findById(transactionDTO.getSenderAccountId()).isPresent()
                && accountRepository.findById(transactionDTO.getRecipientAccountId()).isPresent();
    }

    private boolean accountHasPermissions(Account senderAccount, UserDetails userDetails) {

        return senderAccount.getAccountHolder().getUsername().equals(userDetails.getUsername()) ||
                senderAccount.getSecondaryAccountHolder().getUsername().equals(userDetails.getUsername());

    }
}
