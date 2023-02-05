package com.microservices.employeeservice.kafka;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import com.microservices.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/kafka/employees")
public class KafkaMessageController {

    private EmployeeProducer employeeProducer;

    @Autowired
    private EmployeeService employeeService;

    public KafkaMessageController(EmployeeProducer employeeProducer) {
        this.employeeProducer = employeeProducer;
    }

    @GetMapping("/{empId}/publish")
    public void publish(@PathVariable String empId) {
        Optional<EmployeeEntity> emp = employeeService.getEmployeeById(empId);
//        employeeProducer.sendMessage(emp.orElseThrow());
    }
}
