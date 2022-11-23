package com.example.brickx.dtos;

import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private int rating;
    private String body;
    private String dateCreated;
    private String contractorId;
    private String workerId;
}
