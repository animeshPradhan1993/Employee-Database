package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.ProjectEntity;
import com.animesh.employee.database.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProjectEntityToResourceMapper {
    ProjectEntityToResourceMapper INSTANCE = Mappers.getMapper(ProjectEntityToResourceMapper.class);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    Project map(ProjectEntity entity);


}
