package com.example.brickx.entities;

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
    @Enumerated (value = EnumType.STRING)
    private Gender gender;
    @Column(
            nullable = false
    )
    private String userType;
    private String bio;
    private String phoneNumber;

//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateCreated;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )

    @JoinColumn(
            name = "job_id",
            referencedColumnName = "jobId"
    )
    private Job job;
}
