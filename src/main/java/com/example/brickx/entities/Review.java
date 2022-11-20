package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review extends BaseEntity {

    private Integer rating;
    private String body;
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "contractor_id",referencedColumnName = "id")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "worker_id",referencedColumnName = "id")
    private Worker worker;
}
