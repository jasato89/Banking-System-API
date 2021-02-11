package com.ironhack.bankingsystem.controllers.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.security.*;
import com.ironhack.bankingsystem.utils.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.math.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    PasswordEncoder pwdEnconder = new BCryptPasswordEncoder();
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private User admin;


    @BeforeEach
    void setUp() {

        admin = userRepository.save(new Admin("admin1234", pwdEnconder.encode("1234")));
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        //@NotNull(message = "Username required") String username, @NotNull(message = "Password required") String password, @NotBlank(message = "Name required") String name, @NotNull(message = "Date of birth required") LocalDateTime dateOfBirth, @Valid @NotNull(message = "Address required") Address address
        AccountHolder accountHolder1 = new AccountHolder("jasato", pwdEnconder.encode("1234"), "Jaume Sánchez", LocalDate.of(1989, 1, 13).atStartOfDay(), new Address("Spain", "Madrid", "Callle Murillo", 10, "07006"));
        AccountHolder accountHolder2 = new AccountHolder("jasato2", pwdEnconder.encode("12345"), "Jose Pérez", LocalDate.of(2000, 1, 15).atStartOfDay(), new Address("Spain", "Barcelona", "Las Ramblas", 10, "47890"));
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2));
        //Money balance, String secretKey, boolean isPenalized, @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder
        Account account1 = new Account(new Money(new BigDecimal("250"), Currency.getInstance("EUR")), pwdEnconder.encode("1234"), false, accountHolder1, null);
        Account account2 = new Account(new Money(new BigDecimal("10000"), Currency.getInstance("EUR")), pwdEnconder.encode("1234"), false, accountHolder2, accountHolder1);
        Account account3 = new Account(new Money(new BigDecimal("480"), Currency.getInstance("EUR")), pwdEnconder.encode("1234"), false, accountHolder2, null);
        accountRepository.saveAll(List.of(account1, account2, account3));


    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAccountById() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/account/id/" + accountRepository.findAll().get(0).getAccountId()).with(user(new CustomUserDetails(admin)))).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Jaume"));


    }


    @Test
    void createAccount() {
    }

    @Test
    void getBalanceById() {
    }

    @Test
    void getBalanceByName() {
    }

    @Test
    void getAllAccountsFromUser() {
    }

    @Test
    void updateBalance() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void updateDetails() {
    }
}