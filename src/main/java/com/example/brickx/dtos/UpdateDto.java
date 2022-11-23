package com.example.brickx.dtos;

import com.example.brickx.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String jobType;
}
