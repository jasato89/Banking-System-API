package com.ironhack.bankingsystem.utils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.ironhack.bankingsystem.controllers.dtos.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

import java.math.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class PwdUtils {
    public static void main(String[] args) throws JsonProcessingException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ObjectMapper objectMapper = new ObjectMapper();
        CheckingAccountDTO checkingAccountDTO = new CheckingAccountDTO(new BigDecimal("200"), Currency.getInstance("USD"), "jasato", 1l, null);

        System.out.println(objectMapper.writeValueAsString(checkingAccountDTO));

        System.out.println(passwordEncoder.encode("1234"));
        int months = Period.between(LocalDate.of(2021, 02, 01), LocalDate.now()).getMonths();
        long years = ChronoUnit.YEARS.between(LocalDate.of(1989, 12, 01), LocalDate.now());
        System.out.println(months);
        System.out.println(years);

    }
}
