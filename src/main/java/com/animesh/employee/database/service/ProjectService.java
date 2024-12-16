package com.animesh.employee.database.service;

import com.animesh.employee.database.entity.ProjectEntity;
import com.animesh.employee.database.exception.EntityNotFoundException;
import com.animesh.employee.database.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.animesh.employee.database.constants.StringConstants.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class ProjectService {
    @Autowired
    private final ProjectRepository repository;

    public ProjectEntity createProject(ProjectEntity project) {
        project.setId("Project_" + UUID.randomUUID());
        return repository.save(project);
    }

    public ProjectEntity findById(String id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "Project", id)));
    }
}
