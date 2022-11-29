package com.example.csis3275project.entities;

import com.example.csis3275project.repositories.GroupsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class GroupsTest {

    @Autowired
    private GroupsRepository groupsRepository;

    @Test
    public void testCreateGroup() {
        Groups savedGroups = groupsRepository.save(new Groups("New Group","group description"));

        assertThat(savedGroups.getGroup_id()).isGreaterThan(0);
    }
}