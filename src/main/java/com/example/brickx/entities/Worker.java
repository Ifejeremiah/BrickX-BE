package com.example.brickx.entities;

import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
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
@Getter
@Setter
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
