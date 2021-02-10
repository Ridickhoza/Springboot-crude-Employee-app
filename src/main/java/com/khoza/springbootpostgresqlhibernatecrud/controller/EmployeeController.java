package com.khoza.springbootpostgresqlhibernatecrud.controller;

import com.khoza.springbootpostgresqlhibernatecrud.exception.ResourceNotFoundException;
import com.khoza.springbootpostgresqlhibernatecrud.model.Employee;
import com.khoza.springbootpostgresqlhibernatecrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees rest api
    @GetMapping("employees")
    public List<Employee> getAllEmployees (){

        return this.employeeRepository.findAll();
    }


    //get employee by id rest api
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable(value="id")Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        return ResponseEntity.ok().body(employee);
    }

    //save or add employee rest api
     @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee){

        return this.employeeRepository.save(employee);
    }

    //update employee rest api
     @PutMapping("employees/{id}")
    public ResponseEntity<Employee>updateEmployee(@PathVariable(value="id")Long employeeId,
                                                  @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
         Employee employee = this.employeeRepository.findById(employeeId)
                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

         employee.setFirstName(employeeDetails.getFirstName());
         employee.setLastName(employeeDetails.getLastName());
         employee.setEmail(employeeDetails.getEmail());


       return ResponseEntity.ok(this.employeeRepository.save(employee));
    }

    //delete employee rest api
    @DeleteMapping("employees/{id}")
    public Map<String,Boolean>deleteEmployee(@PathVariable(value = "id")Long employeeId) throws ResourceNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        this.employeeRepository.delete(employee);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }


    //Create a method get all ids

}
