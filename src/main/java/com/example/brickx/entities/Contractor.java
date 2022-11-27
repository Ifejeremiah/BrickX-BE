package com.example.brickx.entities;

import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.Role;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contractor extends User {

    @OneToMany(mappedBy = "contractor")
    @ToString.Exclude
    private List<Project> project;

    public Contractor(String firstName, String lastName, String email, String password, Gender gender, Role role, String bio, String phoneNumber) {
        super(firstName, lastName, email, password, gender, role, bio, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contractor that = (Contractor) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
