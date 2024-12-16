package com.animesh.employee.database;

import com.animesh.employee.database.controller.EmployeeController;
import com.animesh.employee.database.controller.RoleController;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.model.Employee;
import com.animesh.employee.database.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerTest {
    @Autowired
    private RoleController roleController;
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void createRoleAndAssignEmployeesToTheRole() {
        Role role = new Role();
        role.setName("Test Role");
        Role createdRole = roleController.createRole(role);
        assertNotNull(createdRole.getId());
        Employee employee = new Employee();
        employee.setRoleId(createdRole.getId());
        employee.setName("Test User");
        Employee createdEmployee = employeeController.createNewEmployee(employee).getBody();
        assertEquals(createdEmployee.getRoleId(), createdRole.getId());
    }

    @Test
    public void deleteRoleAndCheck() {
        Role role = new Role();
        role.setName("Test Role");
        Role createdRole = roleController.createRole(role);
        assertNotNull(createdRole.getId());
        Employee employee = new Employee();
        employee.setRoleId(createdRole.getId());
        employee.setName("Test User");
        Employee createdEmployee = employeeController.createNewEmployee(employee).getBody();
        roleController.deleteRole(createdRole.getId());
        assertThrowsExactly(EntityNotFoundException.class, () -> employeeController.findEmployeeById(createdEmployee.getId()));
    }

}
