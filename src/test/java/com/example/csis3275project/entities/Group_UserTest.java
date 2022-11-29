package com.example.csis3275project.entities;

import com.example.csis3275project.repositories.AccountRepository;
import com.example.csis3275project.repositories.GroupUserRepository;
import com.example.csis3275project.repositories.GroupsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class Group_UserTest {
    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Test
    public void testCreateGroup() {

        List<Account> listAccounts = accountRepository.findAll();
        List<Groups> listGroups = groupsRepository.findAll();

//        Groups savedGroups = groupUserRepository.save(new Group_User());

//        assertThat(savedGroups.getGroup_id()).isGreaterThan(0);
    }
}