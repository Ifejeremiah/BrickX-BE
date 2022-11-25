package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
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

    public User(Long id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
