package com.example.brickx.dtos;

import com.example.brickx.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String password;

    public WorkerDto(String firstName, String lastName, String email, Gender gender, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }
}
