package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Application extends BaseEntity {

    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "worker_id",referencedColumnName = "id")
    private Worker worker;

}
