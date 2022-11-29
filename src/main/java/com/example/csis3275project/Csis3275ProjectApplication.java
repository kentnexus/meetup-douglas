package com.example.csis3275project;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.csis3275project.entities.Account;
import com.example.csis3275project.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
@EnableEncryptableProperties
public class Csis3275ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Csis3275ProjectApplication.class, args);
    }


    /*
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository){
        return args -> {
            //accountRepository.save(new Account(null, "abc@abc.ca", "abcabc"));
            //accountRepository.save(new Account(null, "123@123.ca", "123123"));
            //accountRepository.save(new Account(null, "def@def.ca","defdef"));
            //accountRepository.save(new Account(null, "456@456.ca","456456"));
            List<Account> accountList = accountRepository.findAll();
            String[] inputs = {"abcabc", "123123", "deFdef", "456763"};
            Account a = new Account();
            for(int i=0; i<accountList.size(); i++) {
                System.out.println("Email: " + accountList.get(i).getEmail() + " / Password Check: " + a.CheckPassword(inputs[i], accountList.get(i).getPassword()));
            }
        };
    }
    */

}
