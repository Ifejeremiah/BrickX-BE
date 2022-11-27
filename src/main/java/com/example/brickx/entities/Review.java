package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Review review = (Review) o;
        return getId() != null && Objects.equals(getId(), review.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
