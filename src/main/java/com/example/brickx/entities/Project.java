package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Project extends BaseEntity {

    private String title;
    private String duration;
    private LocalDateTime startDate;
    private Integer budget;
    private ProjectStatus projectStatus;
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    @OneToMany(mappedBy = "project")
    private List<Worker> worker;

    @OneToMany(mappedBy = "project")
    private List<Job> jobs;

    public Project(String title, String duration, LocalDateTime startDate, Integer budget, ProjectStatus projectStatus, Date dateCreated, Contractor contractor, List<Job> jobs) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.budget = budget;
        this.projectStatus = projectStatus;
        this.dateCreated = dateCreated;
        this.contractor = contractor;
        this.jobs = jobs;
    }
}
