package com.example.brickx.dtos;

import com.example.brickx.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String password;
    private String userType;
    private String jobType;

    public SignUpDto(String firstName, String lastName, String email, Gender gender, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.userType = userType;
    }

    public SignUpDto(String firstName, String lastName, String email, Gender gender, String password, String userType, String jobType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.userType = userType;
        this.jobType = jobType;
    }
}
