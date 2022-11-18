package com.example.brickx.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "email_id_unique",
                columnNames = "email_address"
        )
)
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workerId;
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

    @ManyToOne(
            cascade = CascadeType.ALL
    )

    @JoinColumn(
            name = "job_id",
            referencedColumnName = "jobId"
    )
    private Job job;
}
