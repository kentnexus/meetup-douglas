package com.example.csis3275project.repositories;

import com.example.csis3275project.entities.Account;
import com.example.csis3275project.entities.Group_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<Group_User, Long> {
}

