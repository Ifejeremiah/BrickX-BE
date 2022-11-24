package com.example.brickx.repository;

import com.example.brickx.entities.Job;
import com.example.brickx.entities.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findJobsByJobType(JobType jobType);

    Job findJobById(Long id);
}
