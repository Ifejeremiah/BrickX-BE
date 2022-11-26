package com.example.brickx.repository;

import com.example.brickx.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {

//    List<Application> findApplicationsByProject_Id(Long id);

    Application findApplicationById(Long id);
    List<Application> findApplicationsByWorker_Id(Long worker_id);

    List<Application> findApplicationsByJob_Name(String name);

    Application findApplicationByIdAndJob_IdAndWorker_Id(Long id, Long job_id, Long worker_id);
}
