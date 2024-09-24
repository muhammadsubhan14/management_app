package com.management.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.management.models.entities.EmployeeEntity;
import com.management.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeEntity create(@RequestBody EmployeeEntity employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public Iterable<EmployeeEntity> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> findOne(@PathVariable("id") Long id) {
        EmployeeEntity employee = employeeService.findOne(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build(); // Mengembalikan 404 jika tidak ditemukan
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id") Long id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("position") String position,
                                                 @RequestParam("reportsTo") String reportsTo,
                                                 @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {

        EmployeeEntity employee = employeeService.findOne(id);
        if (employee != null) {
            employee.setName(name);
            employee.setPosition(position);
            employee.setReportsTo(reportsTo);

            if (picture != null && !picture.isEmpty()) {
                employee.setPicture(picture.getBytes());
            }
            return ResponseEntity.ok(employeeService.save(employee)); // Mengembalikan karyawan yang diperbarui
        }

        return ResponseEntity.notFound().build(); // Mengembalikan 404 jika tidak ditemukan
    }
}
