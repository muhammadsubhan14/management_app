package com.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.models.entities.Employee;
import com.management.models.repos.employeeRepo;

@Service
public class EmployeeService {
    
    @Autowired
    private employeeRepo employeeRepo;

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findOne(Long id) {
      Optional<Employee> employee = employeeRepo.findById(id);
      if (!employee.isPresent()) {
          return null;
      }
      return employee.get();
    }

    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public List<Employee> findByName(String name) {
        return employeeRepo.findByNameContains(name);
    }
    
}
