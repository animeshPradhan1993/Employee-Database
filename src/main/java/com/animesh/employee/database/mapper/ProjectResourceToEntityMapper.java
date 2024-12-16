package com.animesh.employee.database.mapper;

import com.animesh.employee.database.entity.ProjectEntity;
import com.animesh.employee.database.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectResourceToEntityMapper {
    ProjectResourceToEntityMapper INSTANCE = Mappers.getMapper(ProjectResourceToEntityMapper.class);

    @Mapping(source = "resource.id", target = "id")
    @Mapping(source = "resource.name", target = "name")
    ProjectEntity map(Project resource);
}
