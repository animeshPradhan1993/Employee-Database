package com.animesh.employee.database.repository;

import com.animesh.employee.database.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

}
