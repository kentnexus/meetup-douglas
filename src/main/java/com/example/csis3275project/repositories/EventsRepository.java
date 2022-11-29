package com.example.csis3275project.repositories;

import com.example.csis3275project.entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Long> {
    @Query(value = "select * from events e where e.name like %:keyword%", nativeQuery = true)
    List<Events> findByKeyword(@Param("keyword") String keyword);
}
