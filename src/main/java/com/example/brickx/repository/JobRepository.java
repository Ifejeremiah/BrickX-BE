package com.example.brickx.repository;

import com.example.brickx.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findJobsByName(String name);

    Job findJobByName(String name);

    Job findJobById(Long id);
}
