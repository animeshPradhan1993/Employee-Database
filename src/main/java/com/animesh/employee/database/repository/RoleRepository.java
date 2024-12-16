package com.animesh.employee.database.repository;

import com.animesh.employee.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    @Procedure(name = "Role.deleteRole")
    void deleteRole(@Param("role") String role);
}
