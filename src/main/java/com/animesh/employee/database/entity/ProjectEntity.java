package com.animesh.employee.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
