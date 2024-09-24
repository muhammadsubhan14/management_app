package com.management.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.management.models.entities.EmployeeEntity;

public interface employeeRepo extends CrudRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByNameContains(String name);
}
