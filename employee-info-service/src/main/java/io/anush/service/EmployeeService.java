package io.anush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.anush.model.Employee;
import io.anush.model.EmployeeList;
import io.anush.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeList getAllEmployess(int edid) {
		List<Employee> emplist = employeeRepository.findAllByEdId(edid);
		EmployeeList list = new EmployeeList();
		list.setEmployeeList(emplist);
		return list;
	}

	public Optional<Employee> GetEmployee(int empid) {
		return employeeRepository.findById(empid);
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(int edid) {
		employeeRepository.deleteByEdId(edid);
	}

	public void deleteEmployeeByEdid(int edid, int empid) {
		employeeRepository.deleteByEdidAndEmpid(edid, empid);
	}
}
