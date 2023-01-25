package com.microservices.employeeservice.controller;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import com.microservices.employeeservice.model.Employee;
import com.microservices.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "retrieve-all-employees", fallbackMethod = "retrieveAllEmployeesFallbackResponse")
    public List<EmployeeEntity> retrieveAllEmployees() {
        return employeeService.retrieveAllEmployees();
    }

    @GetMapping("/{empId}")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable("empId") String id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/updateEmployee/{empId}")
    public String updateEmployee(
            @PathVariable("empId") String id,
            @RequestBody EmployeeEntity employee
    ) {
        Optional<EmployeeEntity> emp = employeeService.getEmployeeById(id);
        if (emp.isPresent())
            return employeeService.saveEmployee(employee);
        return "Employee id '" + id + "' couldn't be updated.";
    }

    @DeleteMapping("/removeEmployee/{empId}")
    public void removeEmployee(@PathVariable("empId") String id) {
        employeeService.removeEmployee(id);
    }

    private List<Employee> retrieveAllEmployeesFallbackResponse(Exception e) {
        Employee emp = new Employee
                .EmployeeBuilder()
                .setId("-999")
                .setName("Doctor Strange")
                .setDeptName("Surgeon")
                .setAddress("Some random universe")
                .setJoiningDate(LocalDate.of(1999, 10, 21))
                .setBaseSalary(100000000)
                .build();

        return List.of(emp);
    }
}
