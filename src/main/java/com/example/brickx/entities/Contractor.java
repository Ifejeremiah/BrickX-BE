package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Contractors")
public class Contractor extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false,
            unique = true
    )
    private String email;
    private String gender;
    @Column(
            nullable = false
    )
    private String userType;
    private String password;
    private String bio;
    private String phoneNumber;
    private Date dateCreated;
    @OneToMany(mappedBy = "contractor")
    private List<Project> project;
}
