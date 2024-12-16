package com.animesh.employee.database.controller;

import com.animesh.employee.database.exception.ErrorDetails;
import com.animesh.employee.database.mapper.RoleEntityToResourceMapper;
import com.animesh.employee.database.mapper.RoleResourceToEntityMapper;
import com.animesh.employee.database.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.animesh.employee.database.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private final RoleService service;

    @Operation(summary = "Delete Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @Transactional
    @DeleteMapping("{role}")
    public void deleteRole(@PathVariable String role) {
        service.deleteRole(role);
    }


    @Operation(summary = "Create New Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorDetails.class))})})
    @PostMapping()
    public Role createRole(@Valid @RequestBody Role role) {
       return RoleEntityToResourceMapper.INSTANCE.map(service.createRole(RoleResourceToEntityMapper.INSTANCE.map(role)));
    }
}
