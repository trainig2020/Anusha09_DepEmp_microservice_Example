package io.anush.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.anush.model.Employee;
import io.anush.model.EmployeeList;
import io.anush.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/GetAll/{edid}")
	public EmployeeList getAllEmployees(@PathVariable int edid){
		return employeeService.getAllEmployess(edid);
	}
	
	@GetMapping("/{empid}")
	public Optional<Employee> getEmployee(@PathVariable int empid) {
		return employeeService.GetEmployee(empid);
	}
	
	@PostMapping("/{edid}/addEmployee")
	public void addEmployee(@PathVariable int edid,@RequestBody Employee employee) {
		employee.setEdid(edid);
		employeeService.addEmployee(employee);
	}
	
	@PutMapping("/{edid}/updateEmployee/{empid}")
	public void updateEmployee(@PathVariable int edid, @RequestBody Employee employee,@PathVariable int empid) {
		employee.setEdid(edid);
		employee.setEmpid(empid);
		employeeService.updateEmployee(employee);
	}
	@DeleteMapping("/deleteEmployee/{edid}")
	public void deleteEmployee(@PathVariable int edid) {
		employeeService.deleteEmployee(edid);
	}
	@DeleteMapping("/deleteAll/{edid}/{empid}")
	public void deleteEmployeeByEdidAndEmpid(@PathVariable int edid,@PathVariable int empid) {
		employeeService.deleteEmployeeByEdid(edid, empid);
	}
}