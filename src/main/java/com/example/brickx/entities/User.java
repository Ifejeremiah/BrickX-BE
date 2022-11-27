package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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


    private String bio;
    private String phoneNumber;

//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date dateCreated;

    public User(Long id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password, Gender gender, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String password, Gender gender, Role role, String bio, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.bio = bio;
        this.phoneNumber = phoneNumber;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
