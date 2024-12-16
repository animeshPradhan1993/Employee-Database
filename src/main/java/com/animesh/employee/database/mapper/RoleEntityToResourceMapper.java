package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.RoleEntity;
import com.animesh.employee.database.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleEntityToResourceMapper {
    RoleEntityToResourceMapper INSTANCE = Mappers.getMapper(RoleEntityToResourceMapper.class);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    Role map(RoleEntity entity);
}
