package com.example.brickx.repository;

import com.example.brickx.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsWorkerByEmail(String email);

    List<Worker> findWorkersByProject_Id(Long project_id);

    Optional<Worker> findWorkerById(Long id);
}
