package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.EmployeeEntity;
import com.animesh.employee.database.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeEntityToAuthResourceMapper {
    EmployeeEntityToAuthResourceMapper INSTANCE = Mappers.getMapper(EmployeeEntityToAuthResourceMapper.class);


    @Mapping(source = "entity.role.name", target = "roleName")
    @Mapping(source = "entity.role.id", target = "roleId")
    @Mapping(expression = "java(entity.getFirstName() + \" \" + entity.getSurname())", target = "name")
    @Mapping(source = "entity.password", target = "password")
    Employee map(EmployeeEntity entity);

}
