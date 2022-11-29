package com.example.csis3275project.entities;

import com.example.csis3275project.repositories.AccountRepository;
import com.example.csis3275project.web.token.ConfirmationTokenService;
import com.example.csis3275project.web.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found.";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Account account) {
        boolean accountExists = accountRepository.findByEmail(account.getEmail()).isPresent();

        if(accountExists){
            throw new IllegalStateException("email already taken.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);

        accountRepository.save(account);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                account
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: send email

        return token;
    }

    public int enableAccount(String email) {
        return accountRepository.enableAppUser(email);
    }

    public boolean isAccountExisted(String email){
        return accountRepository.findByEmail(email).isPresent();
    }
}
