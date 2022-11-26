package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Job extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "job")
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
}
