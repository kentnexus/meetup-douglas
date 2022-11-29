package com.example.csis3275project.repositories;

import com.example.csis3275project.entities.TrendingGroups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrendingGroupsRepository extends JpaRepository<TrendingGroups,Long> {
    List<TrendingGroups> findTrendingGroupsById (long id);
}