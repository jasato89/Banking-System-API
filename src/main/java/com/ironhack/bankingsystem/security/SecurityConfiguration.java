package com.ironhack.bankingsystem.security;

import com.ironhack.bankingsystem.services.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().ignoringAntMatchers("/blogpost/*")
                .ignoringAntMatchers("/blogpost/add")
                .ignoringAntMatchers("/author/*")
                .ignoringAntMatchers("/author");

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/author/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/blogpost/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PUT, "/author/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PATCH, "/author/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PUT, "/blogpost/*").hasAnyRole("ADMIN", "CONTRIBUTOR").antMatchers(HttpMethod.PUT, "/author/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PATCH, "/blogpost/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.DELETE, "/author/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/blogpost/*").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}
