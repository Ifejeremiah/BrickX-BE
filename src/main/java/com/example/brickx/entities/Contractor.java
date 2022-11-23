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
@Data
public class Contractor extends User {

    private String bio;
    private String phoneNumber;
    private Date dateCreated;
    @OneToMany(mappedBy = "contractor")
    private List<Project> project;


}
