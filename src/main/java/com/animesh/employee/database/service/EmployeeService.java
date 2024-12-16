package com.animesh.employee.database.service;

import com.animesh.employee.database.entity.EmployeeEntity;
import com.animesh.employee.database.entity.ProjectEntity;
import com.animesh.employee.database.entity.RoleEntity;
import com.animesh.employee.database.exception.BadRequestException;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.repository.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.animesh.employee.database.constants.StringConstants.ENTITY_DELETED_SUCCESSFULLY;
import static com.animesh.employee.database.constants.StringConstants.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final RoleService roleService;

    @Autowired
    private final ProjectService projectService;

    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity) {
        employeeEntity.setId("Employee_" + System.currentTimeMillis());
        try {
            RoleEntity roleEntity = roleService.findRoleById(employeeEntity.getRole().getId());
        }
        catch (EntityNotFoundException ex){
            throw new BadRequestException(ex.getMessage());
        }
        var password = employeeEntity.getPassword();
        password = StringUtils.isEmpty(employeeEntity.getPassword()) ? employeeEntity.getId() : password;
        employeeEntity.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
        return employeeRepository.save(employeeEntity);
    }

    public String deleteEmployee(String employeeId) {
        employeeRepository.delete(findEmployeeById(employeeId));
        return String.format(ENTITY_DELETED_SUCCESSFULLY, "Employee", employeeId);
    }

    public EmployeeEntity findEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "Employee", employeeId)));

    }


    public EmployeeEntity updateEmployee(String employeeId, EmployeeEntity employeeEntity) {
        EmployeeEntity emp = findEmployeeById(employeeId);
        RoleEntity roleEntity = roleService.findRoleById(employeeEntity.getRole().getId());
        employeeEntity.setRole(roleEntity);
        employeeEntity.setId(emp.getId());
        employeeEntity.setPassword(emp.getPassword());
        return employeeRepository.save(employeeEntity);
    }

    public void assignEmployeeToAProject(String employeeId, String projectId) {
        ProjectEntity project = projectService.findById(projectId);
        EmployeeEntity employeeEntity = findEmployeeById(employeeId);
        employeeEntity.getProjects().add(project);
        employeeRepository.save(employeeEntity);
    }


}
