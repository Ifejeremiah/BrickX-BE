package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.ProjectStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Project extends BaseEntity {

    private String title;
    private String duration;
    private Date startDate;
    private Integer budget;
    private ProjectStatus projectStatus;
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private List<Worker> worker;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private List<Job> jobs;


    public Project(String title, String duration, Date startDate, Integer budget, ProjectStatus projectStatus, Date dateCreated) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.budget = budget;
        this.projectStatus = projectStatus;
        this.dateCreated = dateCreated;
    }

    public Project(String title, String duration, Date startDate, Integer budget, ProjectStatus projectStatus, Date dateCreated, Contractor contractor, List<Job> jobs) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.budget = budget;
        this.projectStatus = projectStatus;
        this.dateCreated = dateCreated;
        this.contractor = contractor;
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
