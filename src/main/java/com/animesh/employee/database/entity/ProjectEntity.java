package com.animesh.employee.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "PROJECT")

public class ProjectEntity {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @ManyToMany(mappedBy = "projects")
    private Set<EmployeeEntity> employeeEntities;
}
