package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Job extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "job")
    @ToString.Exclude
    private List<Application> application;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Job(String name) {
        this.name = name;
    }

    public Job(String name, List<Application> application, Project project) {
        this.name = name;
        this.application = application;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Job job = (Job) o;
        return getId() != null && Objects.equals(getId(), job.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
