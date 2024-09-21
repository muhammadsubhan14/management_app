package com.management.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.management.models.entities.Employee;

public interface employeeRepo extends CrudRepository<Employee, Long> {

    List<Employee> findByNameContains(String name);
}
