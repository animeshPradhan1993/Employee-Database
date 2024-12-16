package com.animesh.employee.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Role")
@NamedStoredProcedureQuery(
        name = "Role.deleteRole",
        procedureName = "DELETE_ROLE",
        parameters = @StoredProcedureParameter(mode = ParameterMode.IN, name = "role", type = String.class)
)

public class RoleEntity {
    @Id
    private String id;
    private String name;
}
