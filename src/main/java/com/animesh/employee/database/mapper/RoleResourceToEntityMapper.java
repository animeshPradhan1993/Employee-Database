package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.RoleEntity;
import com.animesh.employee.database.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleResourceToEntityMapper {
    RoleResourceToEntityMapper INSTANCE = Mappers.getMapper(RoleResourceToEntityMapper.class);

    @Mapping(source = "resource.id", target = "id")
    @Mapping(source = "resource.name", target = "name")
    RoleEntity map(Role resource);
}
