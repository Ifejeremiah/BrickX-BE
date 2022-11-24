package com.example.brickx.dtos;

import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private int id;
    private String status;
    private String jobId;
    private String workerId;
}
