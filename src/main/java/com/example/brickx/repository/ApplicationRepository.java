package com.example.brickx.repository;

import com.example.brickx.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long> {

    List<Application> findApplicationsByProject_Id(Long id);
}
