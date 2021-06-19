/*
    Based on https://www.javaguides.net/
 */

package com.jcepeda.lean.demo.controllers;

import com.jcepeda.lean.demo.models.Employee;
import com.jcepeda.lean.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees( @RequestParam(required = false) String name, @RequestParam(required = false) String position )
    {
        if( name == null && position == null )
        {
            return employeeRepository.findAll();
        }
        return employeeRepository.findByPerson_NameOrPosition_Name( name, position );
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws Exception {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee newEmployee)
    {
    return employeeRepository.save(newEmployee);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                 @RequestBody Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        employee.setPerson( employeeDetails.getPerson() );
        employee.setPosition( employeeDetails.getPosition() );
        employee.setSalary( employeeDetails.getSalary() );

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws Exception {
        Employee person = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
    }
}
