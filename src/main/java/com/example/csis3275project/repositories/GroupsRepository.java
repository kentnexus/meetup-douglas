package com.example.csis3275project.repositories;

import com.example.csis3275project.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    @Query(value = "select * from groups g where g.name like %:keyword%", nativeQuery = true)
    List<Groups> findByKeyword(@Param("keyword") String keyword);
}
