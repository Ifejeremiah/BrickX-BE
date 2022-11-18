package com.example.brickx.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "email_id_unique",
                columnNames = "email_address"
        )
)
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractorId;
    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String email;
    private String gender;
    @Column(
            nullable = false
    )
    private String userType;
    private String bio;
    private String phoneNumber;
    private Date dateCreated;
}
