package com.example.brickx.entities;

import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends User {

    private String jobName;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "worker")
    @ToString.Exclude
    private List<Application> application;

    @OneToMany(mappedBy = "worker")
    @ToString.Exclude
    private List<Review> reviewList;

    public Worker(String firstName, String lastName, String email, String password, Gender gender, Role role, String bio, String phoneNumber, String jobName) {
        super(firstName, lastName, email, password, gender, role, bio, phoneNumber);
        this.jobName = jobName;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @JsonBackReference
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @JsonManagedReference
    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }

    @JsonManagedReference
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Worker worker = (Worker) o;
        return getId() != null && Objects.equals(getId(), worker.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
