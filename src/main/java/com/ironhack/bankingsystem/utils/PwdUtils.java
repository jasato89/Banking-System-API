package com.ironhack.bankingsystem.utils;

import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

import java.time.*;
import java.time.temporal.*;

public class PwdUtils {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("1234"));
        int months = Period.between(LocalDate.of(2021, 02, 01), LocalDate.now()).getMonths();
        long years = ChronoUnit.YEARS.between(LocalDate.of(1989, 12, 01), LocalDate.now());
        System.out.println(months);
        System.out.println(years);

    }
}
