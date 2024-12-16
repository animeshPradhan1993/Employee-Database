package com.animesh.employee.database;

import com.animesh.employee.database.controller.EmployeeController;
import com.animesh.employee.database.exception.BadRequestException;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController controller;
    private static Employee created = null;


    @Test
    @Order(1)
    public void testSuccessfulCreationAndRetrievalandIdisOverridden() {
        Employee employee = new Employee();
        employee.setName("Animesh Test");
        employee.setRoleId("1");
        created = controller.createNewEmployee(employee).getBody();
        assertEquals(created.getRoleId(), controller.findEmployeeById(created.getId()).getRoleId());
        assertNotEquals(created.getId(), employee.getId());
        assertEquals(controller.retrieveEmployeeIdAndPassword(created.getId()).getPassword(), Base64.getEncoder().encodeToString(created.getId().getBytes()));
    }

    @Test
    @Order(2)
    public void testUnsuccessfulCreationWithInvalidRole() {
        Employee employee = new Employee();
        employee.setName("Animesh Test");
        employee.setRoleId("5");
        assertThrowsExactly(BadRequestException.class, () -> controller.createNewEmployee(employee));
    }


    @Test
    @Order(3)
    public void testSuccessfulUpdate() {


        created.setName("Animesh changedName");
        created.setRoleId("2");
        Employee updatedEmployee = controller.updateEmployee(created.getId(), created);
        assertNameAndRole(updatedEmployee.getName(), "Animesh changedName", created.getRoleId(), "2");

    }

    @Test
    @Order(4)
    public void testDeleteEmployee() {

        controller.deleteEmployee(created.getId());
        assertThrowsExactly(EntityNotFoundException.class, () -> controller.findEmployeeById(created.getId()));

    }

    public static void assertNameAndRole(String expectedName, String retrievedName, String expectedRole, String retrievedRole) {
        assertEquals(expectedName, retrievedName);
        assertEquals(expectedRole, retrievedRole);
    }
}
