package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.JobType;
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
@Builder
public class Worker extends BaseEntity {
    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false,
            unique = true
    )
    private String email;
    @Enumerated (value = EnumType.STRING)
    private Gender gender;
    @Column(
            nullable = false
    )
    private String bio;
    private String phoneNumber;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateCreated;

    private JobType jobType;

    private String password;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "worker")
    private List<Application> application;

    @OneToMany(mappedBy = "worker")
    private List<Review> reviewList;

    public Worker(Long id, String firstName, String lastName, String email, JobType jobType) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobType = jobType;
    }
}
