package com.example.brickx.dtos;

import com.example.brickx.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String userType;
    private String password;
    private String bio;
    private String phoneNumber;
    private String dateCreated;

    public ContractorDto(String firstName, String lastName, String email, String gender, String userType, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.userType = userType;
        this.password = password;
    }

    public ContractorDto(int id, String firstName, String lastName, String email, String gender, String userType, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.userType = userType;
        this.password = password;
    }
}
