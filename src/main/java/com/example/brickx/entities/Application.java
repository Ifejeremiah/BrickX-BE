package com.example.brickx.entities;

import com.example.brickx.entities.commons.BaseEntity;
import com.example.brickx.entities.enums.ApplicationStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString

public class Application extends BaseEntity {

    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "worker_id",referencedColumnName = "id")
    private Worker worker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Application that = (Application) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
