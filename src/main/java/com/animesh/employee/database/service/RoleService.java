package com.animesh.employee.database.service;

import com.animesh.employee.database.entity.RoleEntity;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.animesh.employee.database.constants.StringConstants.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class RoleService {
    @Autowired
    private final RoleRepository repository;

    public void deleteRole(String roleId) {
        RoleEntity roleEntity = findRoleById(roleId);

        repository.deleteRole(roleId);
    }

    public RoleEntity createRole(RoleEntity roleEntity) {
        roleEntity.setId("Role_" + System.currentTimeMillis());
        return repository.save(roleEntity);
    }

    public RoleEntity findRoleById(String role) {
        return repository.findById(role).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "ROLE", role)));

    }
}
