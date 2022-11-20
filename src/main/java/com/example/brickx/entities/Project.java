package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Date startDate;
    private Integer budget;
    private ProjectStatus projectStatus;
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    @OneToMany(mappedBy = "project")
    private List<Worker> worker;

    @OneToMany(mappedBy = "project")
    private List<Application> application;
}
