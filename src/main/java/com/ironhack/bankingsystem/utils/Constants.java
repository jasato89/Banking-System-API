package com.ironhack.bankingsystem.utils;

import java.math.*;
import java.util.*;

public class Constants {

    public static final Money PENALTY_FEE = new Money(new BigDecimal(40.0), Currency.getInstance("USD"));
    public static final BigDecimal CCARD_DEFAULT_INTEREST_RATE = new BigDecimal("0.2");
    public static final BigDecimal SAVINGS_ACC_DEFAULT_INTEREST_RATE = new BigDecimal("0.0025");
    public static final BigDecimal SAVINGS_ACC_DEFAULT_MIN_BALANCE = new BigDecimal("1000");
    public static final Money CHECKING_ACC_DEFFAULT_MONTHLY_FEE = new Money(new BigDecimal(12.0), Currency.getInstance("USD"));
    public static final Money CHECKING_ACC_MIN_BALANCE = new Money(new BigDecimal(250.0), Currency.getInstance("USD"));
}
