package com.example.brickx.repository;

import com.example.brickx.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long id);

    boolean existsProjectByContractor_IdAndTitle(Long contractor_id, String title);

    List<Project> findProjectsByContractor_Id(Long contractor_id);

    Project findProjectByIdAndContractor_Id(Long id, Long contractor_id);

    Project findProjectById(Long id);



}
