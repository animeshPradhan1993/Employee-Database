package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.EmployeeEntity;
import com.animesh.employee.database.entity.ProjectEntity;
import com.animesh.employee.database.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;


@Mapper
public interface EmployeeEntityToResourceMapper {

    EmployeeEntityToResourceMapper INSTANCE = Mappers.getMapper(EmployeeEntityToResourceMapper.class);

    @Mapping(source = "entity.role.id", target = "roleId")
    @Mapping(expression = "java(entity.getFirstName() + \" \" + entity.getSurname())", target = "name")
    @Mapping(source = "entity.projects", target = "projectIds", qualifiedByName = "mapProjects")
    @Mapping(target = "password", constant = "")
    Employee map(EmployeeEntity entity);

    @Named("mapProjects")
    default Set<String> map(Set<ProjectEntity> set) {
        if (!CollectionUtils.isEmpty(set)) {
            Set<String> projectIdSet = new HashSet<>();
            set.forEach(proj -> projectIdSet.add(proj.getId()));
            return projectIdSet;
        }
        return new HashSet<>();

    }
}

