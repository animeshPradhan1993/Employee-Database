package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.EmployeeEntity;
import com.animesh.employee.database.entity.RoleEntity;
import com.animesh.employee.database.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeResourceToEntityMapper {
    EmployeeResourceToEntityMapper INSTANCE = Mappers.getMapper(EmployeeResourceToEntityMapper.class);

    @Mapping(source = "roleId", target = "role", qualifiedByName = "mapRole")
    @Mapping(source = "name", target = "firstName", qualifiedByName = "mapFirstName")
    @Mapping(source = "name", target = "surname", qualifiedByName = "mapSurname")
    @Mapping(source = "password", target = "password")
    EmployeeEntity map(Employee resource);

    @Named("mapRole")
    default RoleEntity mapRole(String roleId) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleId);
        return roleEntity;
    }

    @Named("mapFirstName")
    default String mapFirstName(String name) {
        return name.split(" ")[0];
    }

    @Named("mapSurname")
    default String mapSurname(String name) {
        return name.split(" ")[1];
    }
}
