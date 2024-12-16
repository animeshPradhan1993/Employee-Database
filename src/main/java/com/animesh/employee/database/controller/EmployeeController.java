package com.animesh.employee.database.controller;

import com.animesh.employee.database.exception.BadRequestException;
import com.animesh.employee.database.exception.ErrorDetails;
import com.animesh.employee.database.mapper.EmployeeEntityToAuthResourceMapper;
import com.animesh.employee.database.mapper.EmployeeEntityToResourceMapper;
import com.animesh.employee.database.mapper.EmployeeResourceToEntityMapper;
import com.animesh.employee.database.model.Employee;
import com.animesh.employee.database.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController()
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @Operation(summary = "Create New Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @PostMapping
    public ResponseEntity<Employee> createNewEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(EmployeeEntityToResourceMapper.INSTANCE.map(service.createEmployee(EmployeeResourceToEntityMapper.INSTANCE.map(employee))), HttpStatus.CREATED);

    }

    @Operation(summary = "Retrieve EmployeeId and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @GetMapping("/auth/{employeeId}")
    public Employee retrieveEmployeeIdAndPassword(@PathVariable String employeeId) {
        return EmployeeEntityToAuthResourceMapper.INSTANCE.map(service.findEmployeeById(employeeId));

    }

    @Operation(summary = "Retrieve Employee By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable String employeeId) {
        return EmployeeEntityToResourceMapper.INSTANCE.map(service.findEmployeeById(employeeId));

    }

    @Operation(summary = "Update Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @Valid @RequestBody Employee employee) {
        return EmployeeEntityToResourceMapper.INSTANCE.map(service.updateEmployee(employeeId, EmployeeResourceToEntityMapper.INSTANCE.map(employee)));

    }

    @Operation(summary = "Delete Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        if (employeeId.equals("1")) {
            throw new BadRequestException("This Employee can not be deleted");
        }
        return new ResponseEntity<>(service.deleteEmployee(employeeId), HttpStatus.OK);

    }


}
