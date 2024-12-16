package com.animesh.employee.database;

import com.animesh.employee.database.controller.EmployeeController;
import com.animesh.employee.database.controller.ProjectController;
import com.animesh.employee.database.exception.BadRequestException;
import com.animesh.employee.database.model.Employee;
import com.animesh.employee.database.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void createProjectAndAssignEmployee() {
        Project project = new Project();
        project.setName("Test Project");
        Project createdproject = projectController.createProject(project);
        assertNotNull(createdproject.getId());
        Employee employee = new Employee();
        employee.setRoleId("1");
        employee.setName("Test User");
        Employee createdEmployee = employeeController.createNewEmployee(employee).getBody();
        projectController.assignProjectToEmployee(createdEmployee.getId(), createdproject.getId());
        Employee retrievedEmployee = employeeController.findEmployeeById(createdEmployee.getId());
        assert !CollectionUtils.isEmpty(retrievedEmployee.getProjectIds());
        assertTrue(retrievedEmployee.getProjectIds().contains(createdproject.getId()));
    }

    @Test
    public void createProjectAndAssignInvalidEmployeesToTheProject() {
        Project project = new Project();
        project.setName("Test Project");
        Project createdproject = projectController.createProject(project);
        assertThrowsExactly(BadRequestException.class, () -> projectController.assignProjectToEmployee("2", createdproject.getId()));
    }

    @Test
    public void createEmployeeAndAssignInvalidProject() {

        Employee employee = new Employee();
        employee.setRoleId("1");
        employee.setName("Test User");
        Employee createdEmployee = employeeController.createNewEmployee(employee).getBody();
        assertThrowsExactly(BadRequestException.class, () -> projectController.assignProjectToEmployee(createdEmployee.getId(), "223"));
    }

}
