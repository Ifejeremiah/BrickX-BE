package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "Worker")
public class Worker extends User {

    private String jobName;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "worker")
    private List<Application> application;

    @OneToMany(mappedBy = "worker")
    private List<Review> reviewList;

    public Worker(String firstName, String lastName, String email, String password, Gender gender, Role role, String bio, String phoneNumber, LocalDateTime dateCreated, String jobName) {
        super(firstName, lastName, email, password, gender, role, bio, phoneNumber, dateCreated);
        this.jobName = jobName;
    }
}
