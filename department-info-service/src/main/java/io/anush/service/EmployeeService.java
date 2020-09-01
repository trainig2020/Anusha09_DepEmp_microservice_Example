package io.anush.service;

import org.springframework.stereotype.Repository;

import io.anush.model.Employee;
import io.anush.model.EmployeeList;

@Repository
public interface EmployeeService {

	public EmployeeList getAllEmployees(int edid);

	public Employee getEmployee(int empid);

	public Employee addEmployee(Employee employee, int edid);

	public void updateEmployee(Employee employee, int edid, int empid);

	public void deleteEmployee(int empid);

	public void deleteSingleEmployee(int edid, int empid);

}
