package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Job extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @OneToMany(mappedBy = "job")
    private List<Application> application;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
