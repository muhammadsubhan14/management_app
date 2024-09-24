package com.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.models.entities.EmployeeEntity;
import com.management.models.repos.employeeRepo;

@Service
public class EmployeeService {
    
    @Autowired
    private employeeRepo employeeRepo;

    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeRepo.save(employee);
    }

    public EmployeeEntity findOne(Long id) {
      Optional<EmployeeEntity> employee = employeeRepo.findById(id);
      if (!employee.isPresent()) {
          return null;
      }
      return employee.get();
    }

    public Iterable<EmployeeEntity> findAll() {
        return employeeRepo.findAll();
    }

    public List<EmployeeEntity> findByName(String name) {
        return employeeRepo.findByNameContains(name);
    }
    
}
