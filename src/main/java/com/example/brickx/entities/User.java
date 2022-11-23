package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false,
            unique = true
    )
    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Role role;
}
