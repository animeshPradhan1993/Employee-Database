package com.animesh.employee.database.controller;

import com.animesh.employee.database.exception.BadRequestException;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.exception.ErrorDetails;
import com.animesh.employee.database.mapper.ProjectEntityToResourceMapper;
import com.animesh.employee.database.mapper.ProjectResourceToEntityMapper;
import com.animesh.employee.database.model.Project;
import com.animesh.employee.database.service.EmployeeService;
import com.animesh.employee.database.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@AllArgsConstructor
public class ProjectController {
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final ProjectService projectService;

    @Operation(summary = "Assign project To Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found/ Project Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @PutMapping("/api/{employeeId}/projects/{projectId}")
    public ResponseEntity<String> assignProjectToEmployee(@PathVariable String employeeId, @PathVariable String projectId) {
        try {
            employeeService.assignEmployeeToAProject(employeeId, projectId);
        } catch (EntityNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        return new ResponseEntity<>("Project assigned to employee successfully", HttpStatus.OK);
    }

    @Operation(summary = "Create New Project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Project.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @PostMapping("api/projects")
    public Project createProject(@RequestBody Project project) {
        return ProjectEntityToResourceMapper.INSTANCE.map(projectService.createProject(
                ProjectResourceToEntityMapper.INSTANCE.map(project)));
    }
}

