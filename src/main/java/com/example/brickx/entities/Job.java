package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Job extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
