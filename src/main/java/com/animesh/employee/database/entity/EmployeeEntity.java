package com.animesh.employee.database.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Employee")
public class EmployeeEntity {

    @Id
    private String id;
    @Column(name = "FIRST_NAME")
    @NotEmpty
    private String firstName;
    @NotEmpty
    @Column(name = "SURNAME")
    private String surname;
    @NotEmpty
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne()
    @NotNull
    @JoinColumn(name = "ROLE")
    private RoleEntity role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMPLOYEE_PROJECT",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID")
            )
    private Set<ProjectEntity> projects = new HashSet<>();
}
