package com.example.csis3275project.repositories;

import com.example.csis3275project.entities.TopEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopEventsRepository extends JpaRepository<TopEvents,Long> {
    List<TopEvents> findTrendingGroupsById (long id);
}